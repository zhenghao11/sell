package com.my.sell.controller;

import com.my.sell.model.ProductCategory;
import com.my.sell.model.ProductInfo;
import com.my.sell.service.ProductCategoryService;
import com.my.sell.service.ProductInfoService;
import com.my.sell.viewObject.ProductCategoryViewObject;
import com.my.sell.viewObject.ProductInfoViewObject;
import com.my.sell.viewObject.ResultViewObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by hzheng on 2017/7/20.
 */
@RestController
@RequestMapping(value = "/buyer/product")
public class ProductInfoController extends BaseController {

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping(value = "/list")
    public ResultViewObject list() {
        try {
            //查询所有上架商品
            List<ProductInfo> productInfoList = productInfoService.findByProductStatus(0);
            List<Integer> productCategoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType)
                    .collect(Collectors.toList());
            //查询所有上架商品对应的类目id
            List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(productCategoryTypeList);
            List<ProductCategoryViewObject> productCategoryViewObjectList = new ArrayList<ProductCategoryViewObject>();
            for (ProductInfo productInfo : productInfoList) {
                ProductCategoryViewObject productCategoryViewObject = new ProductCategoryViewObject();
                ProductInfoViewObject productInfoViewObject = new ProductInfoViewObject();
                BeanUtils.copyProperties(productInfo, productInfoViewObject);
                for (ProductCategory productCategory : productCategoryList) {
                    if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                        productCategoryViewObject.setCategoryName(productCategory.getCategoryName());
                        productCategoryViewObject.setCategoryType(productCategory.getCategoryType());
                    }
                }
                //添加productInfoViewObject
                productCategoryViewObject.setProductInfoViewObject(Collections.singletonList(productInfoViewObject));
                productCategoryViewObjectList.add(productCategoryViewObject);
            }
            return getSuccessResultViewObject(productCategoryViewObjectList);
        } catch (BeansException e) {
            e.printStackTrace();
            return getFailResultViewObject("操作失败");
        }
    }
}
