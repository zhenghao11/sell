package com.my.sell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my.sell.enums.SaleEnum;
import com.my.sell.utils.EnumUtil;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * created by hzheng on 2017/7/20.
 */
@Entity
@Data
public class ProductInfo {
    //商品id
    @Id
    private String productId;
    //商品名称
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //描述
    private String productDescription;
    //小图
    private String productIcon;
    //商品状态,0正常1下架
    private Integer productStatus;
    //类目编号
    private Integer categoryType;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;

    //辅助字段
    @Transient
    //类目名称
    private String categoryName;
    @JsonIgnore
    public SaleEnum getSaleEnum(){
        return EnumUtil.getBycode(productStatus,SaleEnum.class);
    }

}
