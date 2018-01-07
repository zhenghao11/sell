package com.my.sell.viewObject;

import lombok.Data;

import java.util.List;

/**
 * created by hzheng on 2017/7/20.
 */
@Data
public class ResultViewObject <T> {
    private Integer code;
    private String msg;
    private T data;
}
