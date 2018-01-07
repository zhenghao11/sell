package com.my.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.my.sell.model.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * created by hzheng on 2017/7/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceTest {
    @Autowired
    OrderMasterService orderMasterService;
    @Autowired
    PayService payService;
    @Test
    public void create() throws Exception {
        OrderMaster orderMaster = orderMasterService.findByOrderId("1500806305544");
        payService.create(orderMaster);
    }

}