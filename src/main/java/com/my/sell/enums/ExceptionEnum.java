package com.my.sell.enums;

import lombok.Getter;

/**
 *
 * created by hzheng on 2017/7/22.
 */
@Getter
public enum ExceptionEnum {
    /**
     * 错误信息和错误码
     */
    PRODUCT_WITHOUT(101,"该商品不存在"),
    PRODUCT_FAIL_STOCK(102,"库存不正确"),
    ORDER_CANCEL_FAIL(103,"订单取消失败"),
    ORDER_LIST_NOT_EXIST(104,"订单不存在"),
    ORDER_FINISH_FAIL(105,"订单完结失败"),
    ORDER_PAY_FAIL(106,"订单支付失败"),
    ORDER_PAY_STATUS_FAIL(107,"支付状态错误"),
    WECHAT_TOKEN_ERROR(108,"获取accessToken失败"),
    AMOUNT_NOT_EQUAL(109,"微信通知金额和系统金额不一致"),
    IMG_IS_EMPTY(110,"上传的图片不能为空"),
    ON_SALE_FAIL(111,"商品上架失败"),
    OFF_SALE_FAIL(112,"商品下架失败"),
    USER_IS_NULL(113,"用户不存在"),
    PASSWORD_IS_WRONG(114,"密码错误"),
    USER_ALREADY_EXIST(115,"用户已存在"),
    PARAM_ERROR(116,"参数错误"),
    COOKIE_IS_NULL(117,"cookie不存在此信息"),
    REDIS_IS_NULL(118,"redis不存在此信息"),
    SECKILL_FAIL(119,"秒杀失败,请重试"),
    STOCK_IS_NULL(120,"秒杀失败,活动已结束"),

    /**
     * 异常信息
     */
    NULL_POINT_EXCEPTION(201,"空指针异常"),
    ILLEGAL_ARGUMENT_EXCEPTION(202,"参数不合法"),
    PERSISTENCE_EXCEPTION(203,"数据库异常,可能是sql语句有误"),
    OTHER_EXCEPTION(500,"其他异常"),
    ;

    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
