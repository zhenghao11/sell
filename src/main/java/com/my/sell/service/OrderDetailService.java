package com.my.sell.service;

import com.my.sell.model.OrderDetail;

import java.util.List;

/**
 * created by hzheng on 2017/7/23.
 */
public interface OrderDetailService {
    //根据订单id查找订单详情
    List<OrderDetail> findByOrderId(String orderId);
}
