package com.my.sell.controller;

import com.my.sell.exception.SellException;
import com.my.sell.model.ProductCategory;
import com.my.sell.model.ProductInfo;
import com.my.sell.service.ProductCategoryService;
import com.my.sell.service.ProductInfoService;
import com.my.sell.utils.FileUtil;
import com.my.sell.utils.OrderRandomUtil;
import com.my.sell.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * created by hzheng on 2017/8/3.
 */
@Controller
@RequestMapping(value = "/seller/product")
public class SellProductController {
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    ProductCategoryService productCategoryService;

    /**
     * 商品列表
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "2") Integer size,
                             Integer tableId) {
        PageRequest pageRequest = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoList = productInfoService.findAll(pageRequest);
        Map<String, Object> map = new HashMap<String, Object>();
/*        List<ProductInfo> content = new ArrayList<ProductInfo>();
        productInfoList.getContent().stream().map(e -> e.getProductStatus() == 0 ? content.add(e) : null)
                .collect(Collectors.toList());*/
        List<ProductInfo> content = productInfoList.getContent();
        for (ProductInfo productInfo : content) {
            List<Integer> categoryTypeList = productInfoList.getContent().stream().map(e -> e.getCategoryType())
                    .collect(Collectors.toList());
            productInfo.setCategoryName(productCategoryService.findByCategoryTypeIn(categoryTypeList).get(0).getCategoryName());
        }
        map.put("productInfoList", content);
        map.put("totalPages", productInfoList.getTotalPages());
        map.put("currentPage", page);
        return new ModelAndView("product/list", map);
    }

    /**
     * 新增/修改页面
     *
     * @param productId
     * @param model
     * @return
     */
    @GetMapping(value = "/addIndex")
    public String addIndex(@RequestParam(value = "productId", required = false) String productId, Model model) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productInfoService.findByProductId(productId);
            if (null != productInfo) {
                model.addAttribute("productInfo", productInfo);
            }
        }
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        model.addAttribute("productCategoryList", productCategoryList);
        return "product/addIndex";
    }

    /**
     * 新增/修改商品
     *
     * @param productInfo
     * @param file
     * @return
     */
    @PostMapping(value = "/addOrUpdate")
    public String update(ProductInfo productInfo, MultipartFile file) {
/*        if(null == file || 0 == file.getSize()){
            throw new SellException(ExceptionEnum.IMG_IS_EMPTY);
        }*/
        Assert.hasLength(productInfo.getProductName(), "请输入商品名称");
        Assert.isTrue(RegexUtil.priceRegex(productInfo.getProductPrice().toString()), "请输入正确的价格,最多两位小数");
        Assert.isTrue(RegexUtil.productStockRegex(productInfo.getProductStock()), "请输入正确的库存");
        Assert.hasLength(productInfo.getProductDescription(), "请输入商品描述");
        Assert.notNull(productInfo.getProductStatus(), "请选择商品状态");
        Assert.notNull(productInfo.getCategoryType(), "请选择类目编号");
        if (StringUtils.isEmpty(productInfo.getProductId())) {
            productInfo.setProductIcon(FileUtil.saveImg(file));
            productInfo.setProductId(OrderRandomUtil.getOrderRandomNum());
            productInfo.setCreateTime(new Date());
        } else {
            ProductInfo byProductId = productInfoService.findByProductId(productInfo.getProductId());
            productInfo.setProductIcon(byProductId.getProductIcon());
            productInfo.setCreateTime(byProductId.getCreateTime());
        }
        productInfo.setUpdateTime(new Date());
        productInfoService.save(productInfo);
        return "redirect:/seller/product/list";
    }

    /**
     * 商品上架
     *
     * @param productId
     * @param page
     * @return
     */
    @GetMapping(value = "/onSale")
    public String onSale(@RequestParam("productId") String productId,
                         @RequestParam("page") Integer page) {
        try {
            Assert.notNull(productId, "商品Id不能为空");
            productInfoService.onSale(productId);
        } catch (SellException e) {
            e.printStackTrace();
            return "redirect:/seller/product/list?page=" + page;
        }
        return "redirect:/seller/product/list?page=" + page;
    }

    /**
     * 商品下架
     *
     * @param productId
     * @param page
     * @return
     */
    @GetMapping(value = "/offSale")
    public String offSale(@RequestParam("productId") String productId,
                          @RequestParam("page") Integer page) {
        try {
            Assert.notNull(productId, "商品Id不能为空");
            productInfoService.offSale(productId);
        } catch (SellException e) {
            e.printStackTrace();
            return "redirect:/seller/product/list?page=" + page;
        }
        return "redirect:/seller/product/list?page=" + page;
    }
}
