package com.my.sell.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * created by hzheng on 2017/9/7.
 */
@Slf4j
public class TextUtil {

    public static String sendText(String mobile){
        String pwd = UUID.randomUUID().toString().substring(0,4);
        HttpURLConnection conn = null;
        try {
            String params = "Uid=" + "z475423734" + "&Key=" +"6d5a9f26cd2f83c6b7cd+" + "&smsMob=" + mobile +
                    "&smsText=" + URLEncoder.encode("您的短信验证码为:"+ pwd, "utf-8");
            URL url = new URL("http://utf8.api.smschinese.cn/?"+params);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type","application/octet-stream;charset=utf-8");
            conn = (HttpURLConnection) url.openConnection();
            // 接收数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != conn){
                conn.disconnect();
            }
        }
        return pwd;
    }
}
