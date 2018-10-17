package com.my.sell.controller;

import com.my.sell.model.ProductCategory;
import com.my.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * created by hzheng on 2017/8/8.
 */
@Controller
@RequestMapping(value = "/seller/category")
public class SellCategoryController {
    @Autowired
    ProductCategoryService productCategoryService;

    /**
     * 类目列表
     *
     * @param page
     * @param size
     * @param model
     * @return
     */
    @GetMapping(value = "/list")
    public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "2") Integer size, Model model) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductCategory> productCategoryList = productCategoryService.findAll(pageRequest);
        model.addAttribute("productCategoryList", productCategoryList.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productCategoryList.getTotalPages());
        return "category/list";
    }

    /**
     * 跳转到修改页面
     *
     * @param categoryId
     * @param model
     * @return
     */
    @GetMapping(value = "/updateIndex")
    public String update(@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model) {
        if (null != categoryId) {
            ProductCategory productCategory = productCategoryService.findOne(categoryId);
            if (null != productCategory) {
                model.addAttribute("productCategory", productCategory);
            }
        }
        return "category/updateIndex";
    }

    /**
     * 新增/修改类目
     *
     * @param productCategory
     * @return
     */
    @PostMapping(value = "/addOrUpdate")
    public String addOrUpdate(ProductCategory productCategory) {
        Assert.hasLength(productCategory.getCategoryName(), "请输入类目名称");
        Assert.notNull(productCategory.getCategoryType(), "请输入类目编号");
        productCategory.setUpdateTime(new Date());
        if (null == productCategory.getCategoryId()) {
            productCategory.setCreateTime(new Date());
        }
        productCategoryService.save(productCategory);
        return "redirect:/seller/category/list";
    }
}
