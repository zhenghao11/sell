package com.my.sell.mapper;

import com.my.sell.model.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by hzheng on 2017/9/6.
 */
public interface OrderMasterMapper {
    List<OrderMaster> findByKeyword(@Param("keyword")String keyword);
}
