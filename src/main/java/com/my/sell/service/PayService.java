package com.my.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.my.sell.dto.OrderDto;
import com.my.sell.model.OrderMaster;

/**
 * created by hzheng on 2017/7/27.
 */
public interface PayService {
    //创建支付
    PayResponse create(OrderMaster orderMaster);
    //支付异步回调
    PayResponse notifyPay(String notifyData);
    //退款
    RefundResponse refundPay(OrderDto orderDto);
}
