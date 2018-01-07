package com.my.sell.dao;

import com.my.sell.model.OrderDetail;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;


/**
 * created by hzheng on 2017/7/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void testAdd(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setOrderId("1");
        orderDetail.setProductId("1");
        orderDetail.setProductName("麦饼");
        orderDetail.setProductPrice(new BigDecimal(10.50));
        orderDetail.setProductQuantity(1);
        orderDetail.setProductIcon("/images/maibing.jpg");
        orderDetail.setCreateTime(new Date());
        orderDetailRepository.save(orderDetail);
    }

    @Test
    @Transactional
    public void testDelete(){
        orderDetailRepository.deleteByOrderId("1502008984417");
   //     orderDetailRepository.delete(orderDetailRepository.findByOrderId("1502008984417"));
    }
}