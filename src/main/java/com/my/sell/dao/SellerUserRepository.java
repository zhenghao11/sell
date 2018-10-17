package com.my.sell.dao;

import com.my.sell.model.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by hzheng on 2017/8/11.
 */
public interface SellerUserRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByUserName(String userName);

    SellerInfo findByUserNameAndPassword(String userName, String password);

}
