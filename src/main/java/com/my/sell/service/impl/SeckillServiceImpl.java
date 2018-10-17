package com.my.sell.service.impl;

import com.my.sell.enums.ExceptionEnum;
import com.my.sell.exception.SellException;
import com.my.sell.service.RedisLock;
import com.my.sell.service.SeckillService;
import com.my.sell.utils.OrderRandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * created by hzheng on 2017/8/21.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    // 超时时间 10s
    private static final Integer TIME_OUT = 10 * 1000;

    // 商品信息
    private static Map<String, Integer> productList;
    // 库存信息
    private static Map<String, Integer> stockList;
    // 订单信息
    private static Map<String, String> orderList;

    static {
        productList = new HashMap<>();
        stockList = new HashMap<>();
        orderList = new HashMap<>();
        productList.put("hu", 10000);
        stockList.put("hu", 10000);
    }

    @Autowired
    RedisLock redisLock;

    @Override
    public void secKillSuccess(String productId) {
        Long value = System.currentTimeMillis() + TIME_OUT;
        // 加锁
        if (!redisLock.lock(productId, String.valueOf(value))) throw new SellException(ExceptionEnum.SECKILL_FAIL);
        // 如果库存为0 则活动结束
        Integer stock = stockList.get(productId);
        if (0 == stock) {
            throw new SellException(ExceptionEnum.STOCK_IS_NULL);
        } else {
            // 库存不为0 将订单添加至map 模拟随机数为用户
            orderList.put(OrderRandomUtil.getOrderRandomNum(), productId);
            stock -= 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stockList.put(productId, stock);
        }
        // 解锁
        redisLock.unlock(productId, String.valueOf(value));
    }


    @Override
    public String queryProductAndStock(String productId) {
        return "抢购总数量:" + productList.get(productId) + ",秒杀剩余量:" + stockList.get(productId) + ",抢购人数" +
                orderList.size();
    }


}
