package com.my.sell.dao;

import com.my.sell.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * created by hzheng on 2017/7/22.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{
    List<OrderDetail> findByOrderIdIn(List<String> orderIdList);

    @Modifying
    @Query(value = "DELETE FROM order_detail WHERE order_id = ?1",nativeQuery = true)
    void deleteByOrderId(String orderId);

    OrderDetail findByOrderId(String s);

}