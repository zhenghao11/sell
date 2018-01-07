package com.my.sell.service;

/**
 * created by hzheng on 2017/8/21.
 */
public interface SeckillService {

    String queryProductAndStock(String productId);

    void secKillSuccess(String orderId);
}
