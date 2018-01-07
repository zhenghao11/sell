package com.my.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * created by hzheng on 2017/8/23.
 */
@Component
@Slf4j
public class RedisLock {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     * @param key    商品id
     * @param value  超时时间
     * @return
     */
    public Boolean lock(String key,String value){
        if(stringRedisTemplate.opsForValue().setIfAbsent(key,value)) return true;
        // 获取当前value
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            // 获取上一个时间的value 也就是上一个锁的时间
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if(!StringUtils.isEmpty(oldValue) && currentValue.equals(oldValue)) return true;
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key,String value){
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value))
            stringRedisTemplate.opsForValue().getOperations().delete(key);
    }
}
