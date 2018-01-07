package com.my.sell.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * created by hzheng on 2017/7/25.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatConstant {

    private String appAuthID;
    private String appSecret;
}
