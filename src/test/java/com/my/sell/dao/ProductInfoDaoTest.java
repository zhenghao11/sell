package com.my.sell.dao;

import com.my.sell.dao.ProductInfoRepository;
import com.my.sell.model.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;


/**
 * created by hzheng on 2017/7/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Test
    public void testAdd(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setProductName("三鲜面");
        productInfo.setProductPrice(new BigDecimal(20.00));
        productInfo.setProductStock(1000);
        productInfo.setProductDescription("清江三鲜面");
        productInfo.setProductIcon("/images/.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        productInfo.setCreateTime(new Date());
        productInfoRepository.save(productInfo);
    }

}