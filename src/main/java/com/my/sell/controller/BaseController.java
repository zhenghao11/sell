package com.my.sell.controller;

import com.my.sell.enums.ExceptionEnum;
import com.my.sell.exception.SellException;
import com.my.sell.viewObject.ResultViewObject;

/**
 * Controller父类
 * created by hzheng on 2017/7/23.
 */
public class BaseController {

    /**
     * 成功 状态码位0 返回数据
     *
     * @param data
     * @return
     */
    public ResultViewObject getSuccessResultViewObject(Object data) {
        ResultViewObject resultViewObject = new ResultViewObject();
        resultViewObject.setCode(0);
        resultViewObject.setMsg("操作成功");
        resultViewObject.setData(data);
        return resultViewObject;
    }

    /**
     * 成功  状态码为0 无数据返回
     *
     * @return
     */
    public ResultViewObject getSuccessResultViewObject() {
        ResultViewObject resultViewObject = new ResultViewObject();
        resultViewObject.setCode(0);
        resultViewObject.setMsg("操作成功");
        return resultViewObject;

    }

    /**
     * 失败 状态码为1 无数据返回
     *
     * @return
     */
    public ResultViewObject getFailResultViewObject(String failMsg) {
        ResultViewObject resultViewObject = new ResultViewObject();
        resultViewObject.setCode(1);
        resultViewObject.setMsg(failMsg);
        return resultViewObject;
    }

    /**
     * 失败 可以控制的异常(抛出SellException) 状态码为ExceptionEnum当中的某一个 无数据返回
     *
     * @return
     */
    public ResultViewObject getFailResultViewObject(SellException e) {
        ResultViewObject resultViewObject = new ResultViewObject();
        resultViewObject.setCode(e.getCode());
        resultViewObject.setMsg(e.getMessage());
        return resultViewObject;
    }

    /**
     * 失败 可以控制的异常(抛出的不是SellException) 状态码为ExceptionEnum当中的某一个 无数据返回
     *
     * @return
     */
    public ResultViewObject getFailResultViewObject(ExceptionEnum e) {
        ResultViewObject resultViewObject = new ResultViewObject();
        resultViewObject.setCode(e.getCode());
        resultViewObject.setMsg(e.getMsg());
        return resultViewObject;
    }

    /**
     * 失败
     *
     * @return
     */
    public ResultViewObject getFailResultViewObject(Exception e) {
        ResultViewObject resultViewObject = new ResultViewObject();
        resultViewObject.setCode(ExceptionEnum.OTHER_EXCEPTION.getCode());
        resultViewObject.setMsg(e.getMessage());
        return resultViewObject;
    }

}
