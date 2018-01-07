package com.my.sell.dao;

import com.my.sell.dto.OrderDto;
import com.my.sell.model.OrderDetail;
import com.my.sell.model.OrderMaster;
import com.my.sell.utils.OrderRandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * created by hzheng on 2017/7/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterReposirtoryTest {
    @Autowired
    OrderMasterRepository orderMasterRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void testAdd(){

    }

    @Test
    public void testFindOrderDto(){
        List<OrderMaster> orderMasters = orderMasterRepository.findAll();
        // lambda表达式
        List<String> orderIdList = orderMasters.stream().map(e -> e.getOrderId()).collect(Collectors.toList());
        List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderIdIn(orderIdList);
        for(OrderMaster orderMaster : orderMasters){
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orderMaster,orderDto);
            for(OrderDetail orderDetail : orderDetailList){
                if(orderMaster.getOrderId().equals(orderDetail.getOrderId())){
                    orderDto.setOrderDetailList(Arrays.asList(orderDetail));
                }
            }
            orderDtoList.add(orderDto);
        }
        log.info("================={}",orderDtoList);
    }

    @Test
    public void testTest() throws NoSuchMethodException {
        OrderMaster byOrderId = orderMasterRepository.findByOrderId("1503507606739");
        Method[] declaredMethods = byOrderId.getClass().getDeclaredMethods();
        for(Method method : declaredMethods){
            System.out.println(method);
        }
    }
}