package com.my.sell.controller;

import com.my.sell.dto.OrderDto;
import com.my.sell.exception.SellException;
import com.my.sell.model.OrderDetail;
import com.my.sell.model.OrderMaster;
import com.my.sell.service.OrderDetailService;
import com.my.sell.service.OrderMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by hzheng on 2017/8/4.
 */
@Controller
@RequestMapping(value = "/seller/order")
public class SellOrderController {

    @Autowired
    OrderMasterService orderMasterService;
    @Autowired
    OrderDetailService orderDetailService;
    /**
     * 订单列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<OrderMaster> orderMasterList = orderMasterService.findAll(pageRequest);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orderMasterList",orderMasterList.getContent());
        map.put("totalPages",orderMasterList.getTotalPages());
        map.put("currentPage",page);
        return new ModelAndView("order/list",map);
    }

    /**
     * 订单列表(通过关键字模糊查询)
     * @param keyword
     * @return
     */
    @PostMapping(value = "/search")
    @ResponseBody
    public Object search(@RequestParam(value = "keyword") String keyword){
        List<OrderMaster> byKeyword = orderMasterService.findByKeyword(keyword);
        return byKeyword;
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping(value = "/cancel")
    public String cancel(@RequestParam("orderId") String orderId,Model model){
        try{
            Assert.notNull(orderId,"订单Id不能为空");
            OrderMaster orderMaster = orderMasterService.findByOrderId(orderId);
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orderMaster,orderDto);
            orderDto.setOrderDetailList(orderDetailService.findByOrderId(orderId));
            orderMasterService.cancelOrderMaster(orderDto);
        }catch(SellException e){
            e.printStackTrace();
            model.addAttribute("msg",e.getMessage());
            model.addAttribute("url","/seller/order/list");
            return "common/error";
        }
        return "redirect:/seller/order/list";
    }

    /**
     * 订单详情
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping(value = "/detail")
    public String detail(@RequestParam("orderId") String orderId,
                         @RequestParam("page") Integer page,Model model){
        try{
            Assert.notNull(orderId,"订单id不能为空");
            List<OrderDetail> orderDetailList = orderDetailService.findByOrderId(orderId);
            OrderMaster orderMaster = orderMasterService.findByOrderId(orderId);
            model.addAttribute("orderDetailList",orderDetailList);
            model.addAttribute("orderMaster",orderMaster);
            model.addAttribute("page",page);
        }catch(SellException e){
            e.printStackTrace();
            return "common/error";
        }
        return "order/detail";
    }

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @GetMapping(value = "/finish")
    public String finish(@RequestParam("orderId") String orderId,Model model){
        try{
            Assert.notNull(orderId,"订单id不能为空");
            orderMasterService.finishOrderMaster(orderId);
        }catch (SellException e){
            e.printStackTrace();
            model.addAttribute("msg","完结订单失败");
            model.addAttribute("url","/seller/order/list");
            return "common/error";
        }
        return "redirect:/seller/order/detail?orderId="+orderId;
    }

    /**
     * 删除订单
     * @param orderId
     * @param page
     * @param model
     * @return
     */
    @GetMapping(value = "/delete")
    public String delete(@RequestParam("orderId") String orderId,
                         @RequestParam("page") Integer page,Model model){
        try{
            Assert.notNull(orderId,"订单id不能为空");
            orderMasterService.deleteByOrderId(orderId);
        }catch(SellException e){
            model.addAttribute("msg","删除订单失败");
            model.addAttribute("url","/seller/order/list?page="+page);
            return "common/error";
        }
        return "redirect:/seller/order/list?page="+page;
    }
}
