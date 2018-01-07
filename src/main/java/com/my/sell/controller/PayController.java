package com.my.sell.controller;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import com.my.sell.enums.ExceptionEnum;
import com.my.sell.exception.SellException;
import com.my.sell.model.OrderMaster;
import com.my.sell.service.OrderMasterService;
import com.my.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付
 * created by hzheng on 2017/7/27.
 */
@Controller
@Slf4j
//@RequestMapping(value = "pay")
public class PayController extends BaseController{
    @Autowired
    OrderMasterService orderMasterService;
    @Autowired
    PayService payService;
    @Autowired
    BestPayServiceImpl bestPayService;

    /**
     * 创建支付(需要注册微信商户才可以使用)
     * @param orderId
     * @param returnUrl
     */
    @GetMapping(value = "/create")
    public String create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl,
                       Model model){
        log.info("returnUrl:{}",returnUrl);
        OrderMaster orderMaster = orderMasterService.findByOrderId(orderId);
        if(null == orderMaster){
            throw new SellException(ExceptionEnum.ORDER_LIST_NOT_EXIST);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        PayResponse payResponse = payService.create(orderMaster);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        model.addAttribute(map);
        return "pay/create";
    }

    /**
     * 支付测试接口
     * @param openid
     * @return
     */
    @GetMapping(value = "/pay")
    public ModelAndView pay(@RequestParam("openid") String openid){
        OrderMaster orderMaster = orderMasterService.findByTest(openid).get(0);
        //
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderId(orderMaster.getOrderId());
        payRequest.setOrderAmount(orderMaster.getOrderAmount().doubleValue());
        payRequest.setOpenid(openid);
        payRequest.setOrderName("测试支付");
        log.info("【payRequest:{}】",com.my.sell.utils.JsonUtil.modelToJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【payResponse:{}】",com.my.sell.utils.JsonUtil.modelToJson(payResponse));
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("payResponse",payResponse);
        map.put("returnUrl","http://sell.springboot.cn/#/order/"+orderMaster.getOrderId());
        return new ModelAndView("pay/create",map);
    }

    /**
     * 异步回调
     */
    @PostMapping(value = "/notify")
    public ModelAndView notify(@RequestBody String notifyData) throws Exception {
        log.info("【异步回调】request={}", notifyData);
        PayResponse response = payService.notifyPay(notifyData);
        log.info("【异步回调】response={}", JsonUtil.toJson(response));
        return new ModelAndView("pay/success");
    }
}
