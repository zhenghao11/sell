package com.my.sell.utils;

import com.my.sell.enums.CodeEnum;

/**
 * created by hzheng on 2017/8/4.
 */
public class EnumUtil {

    /**
     * 通过code获取对应的枚举
     * @param code
     * @param enumClass
     * @param <T>
     * @return
     */
    public static <T extends CodeEnum> T getBycode(Integer code,Class<T> enumClass){
        for(T each : enumClass.getEnumConstants()){
            if(code.equals(each.getCode())) return each;
        }
        return null;
    }
}
