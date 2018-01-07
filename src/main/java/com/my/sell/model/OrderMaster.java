package com.my.sell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my.sell.enums.OrderMasterEnum;
import com.my.sell.enums.PayStatusEnum;
import com.my.sell.utils.EnumUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;
import java.util.Date;

/**
 * created by hzheng on 2017/7/22.
 */
@SuppressWarnings("all")
@Entity
@Data
@NamedQuery(name = "OrderMaster.findByTest",query = "select o from OrderMaster o where o.orderStatus = 0 and " +
        "o.payStatus = 0 and o.buyerOpenid = ?1 order by o.createTime desc")
public class OrderMaster {
    //订单id
    @Id
    private String orderId;
    //买家名字
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
    //更新时间
    private Date updateTime;

    @JsonIgnore
    public OrderMasterEnum getOrderMasterEnum(){
        return EnumUtil.getBycode(orderStatus,OrderMasterEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getBycode(payStatus,PayStatusEnum.class);
    }
}