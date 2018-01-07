package com.my.sell.service;

import com.my.sell.dto.OrderDto;
import com.my.sell.model.OrderMaster;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * created by hzheng on 2017/7/22.
 */
public interface OrderMasterService {
    // 查找所有订单
    List<OrderMaster> findAll();
    // 根据订单id查询订单
    OrderMaster findByOrderId(String orderId);
    // 创建订单和订单详情
    OrderDto createOrderMasterAndOrderDetail(OrderDto orderDto);
    // 支付订单
    OrderDto payOrderMaster(OrderDto orderDto);
    // 取消订单
    OrderDto cancelOrderMaster(OrderDto orderDto);
    // 订单完成
    OrderMaster finishOrderMaster(String orderId);
    // 列表(分页)
    Page<OrderMaster> findAll(PageRequest pageRequest);
    //
    List<OrderMaster> findByTest(String openid);
    // 删除订单和订单详情
    void deleteByOrderId(String orderId);
    // 订单列表(通过关键字模糊查询)
    List<OrderMaster> findByKeyword(String keyword);
}
