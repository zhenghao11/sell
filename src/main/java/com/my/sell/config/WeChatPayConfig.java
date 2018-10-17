package com.my.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.my.sell.constant.WeChatConstant;
import com.my.sell.constant.WeChatPayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * created by hzheng on 2017/7/27.
 */
@Component
public class WeChatPayConfig {
    @Autowired
    WeChatPayConstant weChatPayConstant;
    @Autowired
    WeChatConstant weChatConstant;

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppSecret(weChatConstant.getAppSecret());
        wxPayH5Config.setAppId(weChatPayConstant.getAppPayID());
        wxPayH5Config.setMchId(weChatPayConstant.getMchId());
        wxPayH5Config.setMchKey(weChatPayConstant.getMchKey());
        wxPayH5Config.setKeyPath(weChatPayConstant.getKeyPath());
        wxPayH5Config.setNotifyUrl(weChatPayConstant.getNotifyUrl());
        return wxPayH5Config;
    }
}
