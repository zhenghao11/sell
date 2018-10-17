package com.my.sell.handler;

import com.my.sell.controller.BaseController;
import com.my.sell.exception.SellAuthorizeException;
import com.my.sell.exception.SellException;
import com.my.sell.viewObject.ResultViewObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * created by hzheng on 2017/8/19.
 */
@ControllerAdvice
public class CookieRedisHandler extends BaseController {

    @ExceptionHandler(value = SellAuthorizeException.class)
    public String goToLogin(Model model) {
        model.addAttribute("msg", "请先登录");
        model.addAttribute("url", "/seller/user/goToLogin");
        return "common/error";
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultViewObject handlerSellException(SellException e) {
        return getFailResultViewObject(e);
    }
}
