package com.my.sell.dao;

import com.my.sell.dao.ProductCategoryRepository;
import com.my.sell.model.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * created by hzheng on 2017/7/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    public void testAdd(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("主食");
        productCategory.setCategoryType(2);
        productCategory.setCreateTime(new Date());
        productCategoryRepository.save(productCategory);

    }
}