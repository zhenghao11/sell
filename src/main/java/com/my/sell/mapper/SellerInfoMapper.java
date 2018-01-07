package com.my.sell.mapper;

import com.my.sell.model.SellerInfo;

import java.util.List;

/**
 * created by hzheng on 2017/8/24.
 */
public interface SellerInfoMapper {

    List<SellerInfo> findAllByUpdateTimeDesc();
}
