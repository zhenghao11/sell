package com.my.sell.service.impl;

import com.my.sell.dao.OrderDetailRepository;
import com.my.sell.dao.OrderMasterRepository;
import com.my.sell.dto.CartDto;
import com.my.sell.dto.OrderDto;
import com.my.sell.enums.ExceptionEnum;
import com.my.sell.enums.OrderMasterEnum;
import com.my.sell.enums.PayStatusEnum;
import com.my.sell.exception.SellException;
import com.my.sell.mapper.OrderMasterMapper;
import com.my.sell.model.OrderDetail;
import com.my.sell.model.OrderMaster;
import com.my.sell.model.ProductInfo;
import com.my.sell.service.OrderMasterService;
import com.my.sell.service.PayService;
import com.my.sell.service.ProductInfoService;
import com.my.sell.utils.OrderRandomUtil;
import com.my.sell.websocketController.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by hzheng on 2017/7/22.
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService{
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    PayService payService;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderMasterRepository orderMasterRepository;
    @Autowired
    WebSocket webSocket;

    // mybatis
    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Override
    @Transactional
    public OrderDto createOrderMasterAndOrderDetail(OrderDto orderDto) {
        // 订单id
        String orderId = OrderRandomUtil.getOrderRandomNum();
        orderDto.setOrderId(orderId);
        // 订单总金额
        BigDecimal amountPrice = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetail : orderDto.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findByProductId(orderDetail.getProductId());
            if(null == productInfo){
                throw new SellException(ExceptionEnum.PRODUCT_WITHOUT);
            }
            // 保存订单详情
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(OrderRandomUtil.getOrderRandomNum());
            orderDetail.setOrderId(orderId);
            orderDetail.setUpdateTime(new Date());
            orderDetail.setCreateTime(new Date());
            orderDetailRepository.save(orderDetail);
            amountPrice = amountPrice.add(productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())));
        }
        // 保存订单
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(amountPrice);
        orderMaster.setOrderStatus(OrderMasterEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderId(orderId);
        orderMaster.setUpdateTime(new Date());
        orderMaster.setCreateTime(new Date());
        orderMasterRepository.save(orderMaster);
        // 减库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseProductStock(cartDtoList);
        log.info("【插入订单和订单详情】:{}",orderDto);
        //webSocket推送
        webSocket.sendMessage(orderId);
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto payOrderMaster(OrderDto orderDto) {
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderDto.getOrderId());
        if(!orderMaster.getOrderStatus().equals(OrderMasterEnum.NEW.getCode())){
            throw new SellException(ExceptionEnum.ORDER_PAY_FAIL);
        }
        if(!orderMaster.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            throw new SellException(ExceptionEnum.ORDER_PAY_STATUS_FAIL);
        }
        // 修改支付状态
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster om = orderMasterRepository.save(orderMaster);
        log.info("【支付成功,该订单id】:{}",om.getOrderId());
        return orderDto;
    }

    @Override
    public List<OrderMaster> findAll() {
        return orderMasterRepository.findAll();
    }

    @Override
    public OrderMaster findByOrderId(String orderId) {
        return orderMasterRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderDto cancelOrderMaster(OrderDto orderDto) {
        // 只有新订单可以取消
        if(!orderDto.getOrderStatus().equals(OrderMasterEnum.NEW.getCode())){
            throw new SellException(ExceptionEnum.ORDER_CANCEL_FAIL,"只有新订单可以取消");
        }
        // 修改订单状态
        if(orderDto.getOrderDetailList().isEmpty()){
            throw new SellException(ExceptionEnum.ORDER_LIST_NOT_EXIST);
        }
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderStatus(OrderMasterEnum.CANCEL.getCode());
        orderMaster.setUpdateTime(new Date());
        orderMasterRepository.save(orderMaster);
        // 返回库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList()
                .stream().map(e -> new CartDto(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseProductStock(cartDtoList);
        log.info("【取消订单成功,返回库存】:{}",cartDtoList);
        // 支付成功,但退款
        if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            payService.refundPay(orderDto);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderMaster finishOrderMaster(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderId);
        if(!orderMaster.getOrderStatus().equals(OrderMasterEnum.NEW.getCode())){
            throw new SellException(ExceptionEnum.ORDER_FINISH_FAIL);
        }
        orderMaster.setOrderStatus(OrderMasterEnum.FINISHED.getCode());
        orderMaster.setUpdateTime(new Date());
        OrderMaster om = orderMasterRepository.save(orderMaster);
        log.info("【订单完结,该订单id】:{}",orderId);
        return om;
    }

    @Override
    public Page<OrderMaster> findAll(PageRequest pageRequest) {
        return orderMasterRepository.findAll(pageRequest);
    }

    @Override
    public List<OrderMaster> findByTest(String openid) {
        return orderMasterRepository.findByTest(openid);
    }

    @Override
    @Transactional
    public void deleteByOrderId(String orderId) {
        orderMasterRepository.delete(orderId);
        orderDetailRepository.deleteByOrderId(orderId);
    }

    @Override
    public List<OrderMaster> findByKeyword(String keyword) {
        return orderMasterMapper.findByKeyword(keyword);
    }

}
