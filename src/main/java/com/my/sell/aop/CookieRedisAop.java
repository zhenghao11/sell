package com.my.sell.aop;

import com.my.sell.constant.CookieConstant;
import com.my.sell.constant.RedisConstant;
import com.my.sell.exception.SellAuthorizeException;
import com.my.sell.utils.CookieUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * created by hzheng on 2017/8/19.
 */
@Component
@Aspect
public class CookieRedisAop {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(* com.my.sell.controller.*.*(..))" + "&& !execution(* com.my.sell.controller.SellerUserController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        Cookie cookie = CookieUtil.getCookie(request, CookieConstant.COOKIE_NAME);
        // 获取cookie
        if (null == cookie) throw new SellAuthorizeException();
        // 获取redis
        String redisValue = stringRedisTemplate.opsForValue().get(cookie.getValue());
        if (StringUtils.isEmpty(redisValue)) throw new SellAuthorizeException();
        // 重新设置redis的有效时间
        stringRedisTemplate.opsForValue().set(cookie.getValue(), RedisConstant.REDIS_NAME, RedisConstant.REDIS_TIME, TimeUnit.SECONDS);
    }
}