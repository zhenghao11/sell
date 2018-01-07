package com.my.sell.viewObject;

import lombok.Data;

import java.util.List;

/**
 * created by hzheng on 2017/9/17.
 */
@Data
public class FoodType {
    private Integer id;
    private String name;
    private List<Food> foodList;
}
