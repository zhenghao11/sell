package com.my.sell.service.impl;

import com.my.sell.dao.OrderDetailRepository;
import com.my.sell.model.OrderDetail;
import com.my.sell.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * created by hzheng on 2017/7/23.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        return orderDetailRepository.findByOrderIdIn(Arrays.asList(orderId));
    }
}
