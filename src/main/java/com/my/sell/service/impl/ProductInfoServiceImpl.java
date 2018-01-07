package com.my.sell.service.impl;

import com.my.sell.dao.ProductInfoRepository;
import com.my.sell.dto.CartDto;
import com.my.sell.enums.ExceptionEnum;
import com.my.sell.enums.SaleEnum;
import com.my.sell.exception.SellException;
import com.my.sell.model.OrderDetail;
import com.my.sell.model.ProductInfo;
import com.my.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * created by hzheng on 2017/7/20.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findByProductStatus(Integer productStatus) {
        return productInfoRepository.findByProductStatus(productStatus);
    }

    @Override
    public void increaseProductStock(List<CartDto> cartDtoList) {
        for(CartDto cartDto : cartDtoList){
            ProductInfo productInfo = productInfoRepository.findByProductId(cartDto.getProductId());
            if(null == productInfo){
                throw new SellException(ExceptionEnum.PRODUCT_WITHOUT);
            }
            int newStock = productInfo.getProductStock() + cartDto.getProductQuantity();
            if(newStock <= 0){
                throw new SellException(ExceptionEnum.PRODUCT_FAIL_STOCK);
            }
            productInfo.setProductStock(newStock);
            productInfo.setUpdateTime(new Date());
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void decreaseProductStock(List<CartDto> cartDtoList) {
        for(CartDto cartDto : cartDtoList){
            ProductInfo productInfo = productInfoRepository.findByProductId(cartDto.getProductId());
            if(null == productInfo){
                throw new SellException(ExceptionEnum.PRODUCT_WITHOUT);
            }
            int newProductStock = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(newProductStock < 0){
                throw new SellException(ExceptionEnum.PRODUCT_FAIL_STOCK);
            }
            productInfo.setProductStock(newProductStock);
            productInfo.setUpdateTime(new Date());
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public ProductInfo findByProductId(String productId) {
        return productInfoRepository.findByProductId(productId);
    }

    @Override
    public Page<ProductInfo> findAll(PageRequest pageRequest) {
        return productInfoRepository.findAll(pageRequest);
    }

    @Override
    public void save(ProductInfo productInfo) {
        productInfoRepository.save(productInfo);
    }

    @Override
    public void onSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if(null == productInfo){
            throw new SellException(ExceptionEnum.PRODUCT_WITHOUT);
        }
        if(SaleEnum.OFF_SALE.getCode() != productInfo.getProductStatus()){
            throw new SellException(ExceptionEnum.ON_SALE_FAIL);
        }
        productInfo.setProductStatus(SaleEnum.ON_SALE.getCode());
        productInfo.setUpdateTime(new Date());
        productInfoRepository.save(productInfo);
    }

    @Override
    public void offSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findOne(productId);
        if(null == productInfo){
            throw new SellException(ExceptionEnum.PRODUCT_WITHOUT);
        }
        if(SaleEnum.ON_SALE.getCode() != productInfo.getProductStatus()){
            throw new SellException(ExceptionEnum.OFF_SALE_FAIL);
        }
        productInfo.setProductStatus(SaleEnum.OFF_SALE.getCode());
        productInfo.setUpdateTime(new Date());
        productInfoRepository.save(productInfo);
    }
}
