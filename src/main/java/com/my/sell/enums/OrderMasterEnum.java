package com.my.sell.enums;

import lombok.Getter;

/**
 * created by hzheng on 2017/7/22.
 */
@Getter
public enum OrderMasterEnum implements CodeEnum {
    NEW(0,"新订单"),
    CANCEL(1,"已取消"),
    FINISHED(2,"已完成"),
    OUT(3,"过期");

    private Integer code;
    private String msg;

    OrderMasterEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
