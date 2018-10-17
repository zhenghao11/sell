package com.my.sell.dao;

import com.my.sell.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * created by hzheng on 2017/7/20.
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    // 查询上架商品
    List<ProductInfo> findByProductStatus(Integer productStatus);

    // 通过productId查询商品
    ProductInfo findByProductId(String productId);
}
