package com.my.sell.controller;

import com.my.sell.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * created by hzheng on 2017/8/24.
 */
@RestController
@RequestMapping(value = "/seckill")
@Slf4j
public class SecKillController {

    @Autowired
    SeckillService seckillService;

    /**
     * 查询秒杀详情
     * @param productId
     * @return
     */
    @GetMapping(value = "/query/{productId}")
    public String querySeckill(@PathVariable String productId){
        return seckillService.queryProductAndStock(productId);
    }

    /**
     * 秒杀 返回秒杀信息
     * @param productId
     * @return
     */
    @GetMapping(value = "/seckill/{productId}")
    public String seckill(@PathVariable String productId){
        seckillService.secKillSuccess(productId);
        log.info("【秒杀的商品id】:",productId);
        return seckillService.queryProductAndStock(productId);
    }
}
