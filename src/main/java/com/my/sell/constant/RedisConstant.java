package com.my.sell.constant;

/**
 * created by hzheng on 2017/8/11.
 */
public interface RedisConstant {
    // 保存到redis 用户名前缀
    String REDIS_NAME = "userName";
    // 用户有效时间
    Long REDIS_TIME = 7200l;
}
