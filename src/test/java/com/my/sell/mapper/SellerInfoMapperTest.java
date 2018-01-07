package com.my.sell.mapper;

import com.my.sell.model.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * created by hzheng on 2017/8/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoMapperTest {
    @Autowired
    SellerInfoMapper sellerInfoMapper;

    @Test
    public void testFindAllByUpdateTimeDesc(){
        List<SellerInfo> sellerInfoList = sellerInfoMapper.findAllByUpdateTimeDesc();
    }
}
