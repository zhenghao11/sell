package com.my.sell.utils;

import java.util.Random;

/**
 * created by hzheng on 2017/7/23.
 */
public class OrderRandomUtil {

    /**
     * 获取订单号和订单详情号
     *
     * @return
     */
    public static synchronized String getOrderRandomNum() {
        Random random = new Random();
        long randomNum = random.nextInt(900) + 100 + System.currentTimeMillis();
        return randomNum + "";
    }
}
