package com.my.sell.enums;

import lombok.Getter;

/**
 * created by hzheng on 2017/8/6.
 */
@Getter
public enum SaleEnum implements CodeEnum{
    ON_SALE(0,"上架"),
    OFF_SALE(1,"下架");

    private Integer code;
    private String msg;

    SaleEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
