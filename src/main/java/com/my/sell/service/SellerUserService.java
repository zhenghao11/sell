package com.my.sell.service;

import com.my.sell.model.SellerInfo;

/**
 * created by hzheng on 2017/8/11.
 */
public interface SellerUserService {

    SellerInfo findByUserName(String userName);

    SellerInfo findByUserNameAndPassword(String userName, String password);

    void save(SellerInfo sellerInfo);

}
