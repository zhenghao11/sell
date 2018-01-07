package com.my.sell.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * created by hzheng on 2017/7/22.
 */
@Entity
@Data
public class OrderDetail {
    //订单详情id
    @Id
    private String detailId;
    //订单id
    private String orderId;
    //商品id
    private String productId;
    //商品名称
    private String productName;
    //价格
    private BigDecimal productPrice;
    //数量
    private Integer productQuantity;
    //小图
    private String productIcon;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
}
