package com.my.sell.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * created by hzheng on 2017/7/27.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatPayConstant {
    private String appPayID;
    private String mchId;
    private String mchKey;
    private String keyPath;
    private String notifyUrl;

}

