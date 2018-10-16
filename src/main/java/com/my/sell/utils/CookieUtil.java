package com.my.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by hzheng on 2017/8/11.
 */
public class CookieUtil {

    /**
     * 添加cookie
     *
     * @param name
     * @param value
     * @param maxAge
     * @param reponse
     */
    public static void addCookie(String name, String value, Integer maxAge,
                                 HttpServletResponse reponse) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        reponse.addCookie(cookie);
    }

    /**
     * 获取cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies || 0 == cookies.length) return null;
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) return cookie;
        }
        return null;
    }
}
