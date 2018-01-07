package com.my.sell.service;

import com.my.sell.dto.OrderDto;
import com.my.sell.model.OrderDetail;
import com.my.sell.model.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * created by hzheng on 2017/7/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceTest {

    @Autowired
    OrderMasterService orderMasterService;
    @Autowired
    OrderDetailService orderDetailService;
    @Test
    public void cancelOrderMaster() throws Exception {
        OrderMaster orderMaster = orderMasterService.findByOrderId("1500808675799");
        List<OrderDetail> orderDetailList = orderDetailService.findByOrderId("1500808675799");
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        orderMasterService.cancelOrderMaster(orderDto);
    }
    @Test
    public void createOrderMasterAndOrderDetail() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("lalala");
        orderDto.setBuyerPhone("1363636336");
        orderDto.setBuyerAddress("abc");
        orderDto.setBuyerOpenid(".lalala");
        //OrderDetail
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("2");
        orderDetail1.setProductQuantity(10);
        orderDetailList.add(orderDetail1);
        //添加OrderMaster和OrderDetail
        orderDto.setOrderDetailList(orderDetailList);
        orderMasterService.createOrderMasterAndOrderDetail(orderDto);
    }

    @Test
    public void finishOrderMaster() throws Exception {
        orderMasterService.finishOrderMaster("1500806305544");
    }

    @Test
    public void payOrderMaster() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId("1500805857418");
        orderMasterService.payOrderMaster(orderDto);
    }
}