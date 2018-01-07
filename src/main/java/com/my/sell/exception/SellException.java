package com.my.sell.exception;

import com.my.sell.enums.ExceptionEnum;
import lombok.Getter;

/**
 * created by hzheng on 2017/7/22.
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public SellException(ExceptionEnum exceptionEnum,String msg){
        super(msg);
        this.code = exceptionEnum.getCode();
    }


}
