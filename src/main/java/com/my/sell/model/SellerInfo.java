package com.my.sell.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * created by hzheng on 2017/8/11.
 */
@Data
@Entity
public class SellerInfo {
    @Id
    @GeneratedValue
    private Integer id;

    private String userName;

    private String password;

    private String openid;

    private Date createTime;

    private Date updateTime;
}
