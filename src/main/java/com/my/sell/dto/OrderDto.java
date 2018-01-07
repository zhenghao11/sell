package com.my.sell.dto;

import com.my.sell.model.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * created by hzheng on 2017/7/22.
 */
@Data
public class OrderDto {
    //订单id
    private String orderId;
    //买家姓名
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信openid
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态, 默认0新下单
    private Integer orderStatus;
    //支付状态, 默认0未支付
    private Integer payStatus;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //订单详情
    private List<OrderDetail> orderDetailList;
}
