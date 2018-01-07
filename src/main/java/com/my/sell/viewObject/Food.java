package com.my.sell.viewObject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * created by hzheng on 2017/9/17.
 */
@Data
public class Food {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stock;

}
