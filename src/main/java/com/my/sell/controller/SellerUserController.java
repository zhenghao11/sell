package com.my.sell.controller;

import com.my.sell.constant.CookieConstant;
import com.my.sell.constant.RedisConstant;
import com.my.sell.enums.ExceptionEnum;
import com.my.sell.exception.SellException;
import com.my.sell.model.SellerInfo;
import com.my.sell.service.SellerUserService;
import com.my.sell.utils.CookieUtil;
import com.my.sell.utils.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * created by hzheng on 2017/8/11.
 */
@Controller
@RequestMapping(value = "seller/user")
public class SellerUserController {
    @Autowired
    SellerUserService sellerUserService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 用户登录界面
     */
    @RequestMapping(value = "/goToLogin")
    public String goToLogin(String returnUrl,Model model){
        model.addAttribute("returnUrl",returnUrl);
        return "user/login";
    }

    /**
     * 用户登录
     * @param sellerInfo
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(SellerInfo sellerInfo, Model model, HttpServletResponse response,
                        @RequestParam(value = "returnUrl",required = false) String returnUrl){
        Assert.notNull(sellerInfo.getUserName(),"用户名不能为空");
        Assert.notNull(sellerInfo.getPassword(),"密码不能为空");
        SellerInfo sI = new SellerInfo();
        sI = sellerUserService.findByUserName(sellerInfo.getUserName());
        if(null == sI){
            model.addAttribute("userNameMsg", ExceptionEnum.USER_IS_NULL.getMsg());
            return "/user/login";
        }
        sI = sellerUserService.findByUserNameAndPassword(sellerInfo.getUserName(),sellerInfo.getPassword());
        if(null == sI){
            model.addAttribute("passwordMsg",ExceptionEnum.PASSWORD_IS_WRONG.getMsg());
            return "/user/login";
        }
        String key = UUID.randomUUID().toString();
        // 添加信息到redis
        stringRedisTemplate.opsForValue().set(key,RedisConstant.REDIS_NAME,RedisConstant.REDIS_TIME, TimeUnit.SECONDS);
        // 添加信息到cookie
        CookieUtil.addCookie(CookieConstant.COOKIE_NAME,key,-1,response);
        // 跳转到未登录前访问的页面
        if(!StringUtils.isEmpty(returnUrl)) return "redirect:" + returnUrl;
        return "redirect:/seller/order/list";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping(value = "/goToRegister")
    public String goToRegister(){
        return "user/register";
    }

    /**
     * 用户注册
     * @param sellerInfo
     * @param model
     * @return
     */
    @RequestMapping(value = "/register")
    public String register(SellerInfo sellerInfo,Model model,String text){
        try{
            Assert.notNull(sellerInfo.getUserName(),"用户名不能为空");
            Assert.notNull(sellerInfo.getPassword(),"密码不能为空");
            SellerInfo sI = sellerUserService.findByUserName(sellerInfo.getUserName());
            if(null != sI){
                model.addAttribute("msg",ExceptionEnum.USER_ALREADY_EXIST.getMsg());
                return "user/register";
            }
            sellerUserService.save(sellerInfo);
            return "user/login";
        }catch(SellException e){
            e.printStackTrace();
            model.addAttribute("msg",e.getMessage());
            return "user/register";
        }
    }

    /**
     * 获取短信验证码
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/getMobileText")
    @ResponseBody
    public String getMobileText(@RequestParam(value = "mobile") String mobile){
        return TextUtil.sendText(mobile);
    }
}
