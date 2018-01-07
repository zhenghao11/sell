package com.my.sell.controller;

import com.my.sell.converter.OrderForm2OrderDTOConverter;
import com.my.sell.dto.OrderDto;
import com.my.sell.form.OrderForm;
import com.my.sell.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * created by hzheng on 2017/7/23.
 */
@RestController
@RequestMapping(value = "/buyer/order")
public class BuyerOrderController extends BaseController{
    @Autowired
    OrderMasterService orderMasterService;
    @PostMapping(value = "/create")
    public String create(OrderForm orderForm){
        OrderDto orderDto = OrderForm2OrderDTOConverter.convert(orderForm);
        OrderDto oD = orderMasterService.createOrderMasterAndOrderDetail(orderDto);
        Map<String, String> map = new HashMap<>();
        return "order/list";
    }
}
