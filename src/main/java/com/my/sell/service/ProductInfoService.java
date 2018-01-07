package com.my.sell.service;

import com.my.sell.dto.CartDto;
import com.my.sell.model.OrderDetail;
import com.my.sell.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * created by hzheng on 2017/7/20.
 */
public interface ProductInfoService {
    // 查找上架商品
    List<ProductInfo> findByProductStatus(Integer productStatus);
    // 加库存
    void increaseProductStock(List<CartDto> cartDtoList);
    // 减库存
    void decreaseProductStock(List<CartDto> cartDtoList);
    // 通过商品id查找商品
    ProductInfo findByProductId(String productId);

    Page<ProductInfo> findAll(PageRequest pageRequest);
    // 新增商品
    void save(ProductInfo productInfo);
    // 商品上架
    void onSale(String productId);
    // 商品下架
    void offSale(String productId);
}
