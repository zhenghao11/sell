package com.my.sell.dao;

import com.my.sell.model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * created by hzheng on 2017/7/22.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    //通过订单id列表查找订单详情
    List<OrderMaster> findByOrderIdIn(List<Integer> orderIdList);

    //通过订单id查找订单详情
    OrderMaster findByOrderId(String orderId);

    //
    List<OrderMaster> findByTest(String openid);
}
