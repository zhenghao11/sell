package com.my.sell.enums;

import lombok.Getter;

/**
 * created by hzheng on 2017/7/22.
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESS(1,"成功支付"),
    FAIL(2,"失败支付");

    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
