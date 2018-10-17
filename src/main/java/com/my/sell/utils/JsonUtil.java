package com.my.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Json工具类
 * created by hzheng on 2017/7/29.
 */
public class JsonUtil {

    public static String modelToJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
