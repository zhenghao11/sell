package com.my.sell.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.my.sell.dto.OrderDto;
import com.my.sell.enums.ExceptionEnum;
import com.my.sell.exception.SellException;
import com.my.sell.model.OrderMaster;
import com.my.sell.service.OrderMasterService;
import com.my.sell.service.PayService;
import com.my.sell.utils.JsonUtil;
import com.my.sell.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * created by hzheng on 2017/7/27.
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";
    @Autowired
    BestPayServiceImpl bestPayService;
    @Autowired
    OrderMasterService orderMasterService;

    @Override
    public PayResponse create(OrderMaster orderMaster) {
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderId(orderMaster.getOrderId());
        payRequest.setOrderAmount(orderMaster.getOrderAmount().doubleValue());
        payRequest.setOpenid(orderMaster.getBuyerOpenid());
        payRequest.setOrderName(ORDER_NAME);
        log.info("【payRequest:{}】", JsonUtil.modelToJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【payResponse:{}】",JsonUtil.modelToJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notifyPay(String notifyData) {
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        //查询订单
        OrderMaster orderMaster = orderMasterService.findByOrderId(payResponse.getOrderId());
        //判断订单是否存在
        if(null == orderMaster){
            log.error("【订单号不存在,微信通知订单号:】{}",payResponse.getOrderId());
            throw new SellException(ExceptionEnum.ORDER_LIST_NOT_EXIST);
        }
        //判断微信通知金额和系统金额是否一致 0.1 0.10   < 0.01
        if(!MathUtil.equal(orderMaster.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信通知金额和系统金额不一致,微信通知金额:{},系统金额:{}】",payResponse.getOrderAmount(),orderMaster.getOrderAmount());
            throw new SellException(ExceptionEnum.AMOUNT_NOT_EQUAL);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        //修改订单的支付状态
        orderMasterService.payOrderMaster(orderDto);
        return payResponse;
    }

    @Override
    public RefundResponse refundPay(OrderDto orderDto) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDto.getOrderId());
        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        OrderMaster orderMaster = orderMasterService.findByOrderId(refundRequest.getOrderId());
        //判断订单是否存在
        if(null == orderMaster){
            throw new SellException(ExceptionEnum.ORDER_LIST_NOT_EXIST);
        }
        log.info("【退款的refundRequest】:{}",refundRequest);
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【退款的refundResponse】:{}",refundResponse);
        //修改订单状态
        orderMasterService.finishOrderMaster(refundRequest.getOrderId());
        return refundResponse;
    }

}
