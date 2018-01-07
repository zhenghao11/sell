package com.my.sell.service;

import com.my.sell.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * created by hzheng on 2017/7/20.
 */
public interface ProductCategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    List<ProductCategory> findAll();

    Page<ProductCategory> findAll(PageRequest pageRequest);

    ProductCategory findOne(Integer categoryId);

    void save(ProductCategory productCategory);
}
