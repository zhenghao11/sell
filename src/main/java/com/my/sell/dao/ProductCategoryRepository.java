package com.my.sell.dao;

import com.my.sell.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * created by hzheng on 2017/7/20.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes);

    ProductCategory findByCategoryType(Integer categoryType);
}
