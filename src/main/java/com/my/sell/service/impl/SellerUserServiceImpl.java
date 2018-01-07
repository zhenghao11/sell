package com.my.sell.service.impl;

import com.my.sell.dao.SellerUserRepository;
import com.my.sell.model.SellerInfo;
import com.my.sell.service.SellerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * created by hzheng on 2017/8/11.
 */
@Service
public class SellerUserServiceImpl implements SellerUserService {
    @Autowired
    SellerUserRepository sellerUserRepository;
    @Override
    public SellerInfo findByUserName(String userName) {
        return sellerUserRepository.findByUserName(userName);
    }

    @Override
    public SellerInfo findByUserNameAndPassword(String userName, String password) {
        return sellerUserRepository.findByUserNameAndPassword(userName,password);
    }

    @Override
    public void save(SellerInfo sellerInfo) {
        sellerInfo.setOpenid(UUID.randomUUID().toString().replaceAll("-",""));
        sellerInfo.setCreateTime(new Date());
        sellerInfo.setUpdateTime(new Date());
        sellerUserRepository.save(sellerInfo);
    }
}
