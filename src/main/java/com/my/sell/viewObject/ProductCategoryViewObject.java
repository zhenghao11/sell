package com.my.sell.viewObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * created by hzheng on 2017/7/20.
 */
@Data
public class ProductCategoryViewObject {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoViewObject> productInfoViewObject;
}
