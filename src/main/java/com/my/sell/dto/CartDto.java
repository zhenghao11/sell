package com.my.sell.dto;

import lombok.Data;

/**
 * created by hzheng on 2017/7/23.
 */
@Data
public class CartDto {
    //商品id
    private String productId;
    //购买数量
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
