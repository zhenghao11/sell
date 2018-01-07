package com.my.sell;

import com.my.sell.constant.WeChatConstant;
import com.my.sell.constant.WeChatPayConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellApplicationTests {
	@Autowired
	WeChatConstant weChatConstant;
	@Autowired
	WeChatPayConstant weChatPayConstant;
	@Autowired
    RedisTemplate<String,String> redisTemplate;

	@Test
	public void contextLoads() {
/*		System.out.println(weChatPayConstant.getKeyPath());
		System.out.println(weChatPayConstant.getMchId());
		System.out.println(weChatPayConstant.getMchKey());
		System.out.println(weChatPayConstant.getNotifyUrl());*/
	//	redisTemplate.opsForHash().put("boringM","1","100");
	//	redisTemplate.expire("boringM",60l, TimeUnit.SECONDS);
        Set<Object> boringMList = redisTemplate.opsForHash().keys("boringM");
        for(Object o : boringMList){
            System.out.println(redisTemplate.opsForHash().get("boringM",o.toString()));
        }
    }

}
