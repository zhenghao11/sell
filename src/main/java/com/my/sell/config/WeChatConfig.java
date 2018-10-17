package com.my.sell.config;

import com.my.sell.constant.WeChatConstant;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信公众号配置
 * created by hzheng on 2017/7/25.
 */
@Component
@Slf4j
public class WeChatConfig {
    @Autowired
    WeChatConstant weChatConstant;

    @Bean
    public WxMpService getWxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(getWxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage getWxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(weChatConstant.getAppAuthID());
        wxMpInMemoryConfigStorage.setSecret(weChatConstant.getAppSecret());
        return wxMpInMemoryConfigStorage;
    }
}
