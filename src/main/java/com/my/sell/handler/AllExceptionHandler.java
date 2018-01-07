package com.my.sell.handler;

import com.my.sell.controller.BaseController;
import com.my.sell.enums.ExceptionEnum;
import com.my.sell.exception.SellAuthorizeException;
import com.my.sell.exception.SellException;
import com.my.sell.viewObject.ResultViewObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理类
 * created by hzheng on 2017/8/24.
 */
@ControllerAdvice
@Slf4j
public class AllExceptionHandler extends BaseController{

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object getResultViewObject(Exception e){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        // 打印出异常信息
        log.info("=================异常信息=================");
        log.error(e.getMessage());
        log.info("=================堆栈信息=================");
        StackTraceElement[] errorStack = e.getStackTrace();
        int count = 0; //只打印15行的错误堆栈
        for (StackTraceElement stackTraceElement : errorStack) {
            log.error(stackTraceElement.toString());
            if(count++ > 14) break;
        }
        // 未登录(cookie或者redis没有数据)
        if(e instanceof SellAuthorizeException){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg","请先登录");
            map.put("url","/seller/user/goToLogin?returnUrl=" + requestURI);
            return new ModelAndView("common/error",map);
        }
        if(e instanceof IllegalArgumentException) return getFailResultViewObject(ExceptionEnum.ILLEGAL_ARGUMENT_EXCEPTION);
        if(e instanceof NullPointerException) return getFailResultViewObject(ExceptionEnum.NULL_POINT_EXCEPTION);
        if(e instanceof PersistenceException) return getFailResultViewObject(ExceptionEnum.PERSISTENCE_EXCEPTION);
        if(e instanceof SellException) return getFailResultViewObject((SellException) e);
        return getFailResultViewObject(e);
    }
}
