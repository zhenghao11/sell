package com.my.sell.utils;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
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

    public static String sendText(String mobile) {
        String pwd = UUID.randomUUID().toString().substring(0, 4);
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        try {
            String params = "Uid=" + "z475423734" + "&Key=" + "6d5a9f26cd2f83c6b7cd+" + "&smsMob=" + mobile +
                    "&smsText=" + URLEncoder.encode("您的短信验证码为:" + pwd, "utf-8");
            URL url = new URL("http://utf8.api.smschinese.cn/?" + params);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/octet-stream;charset=utf-8");
            // 接收数据
            reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            log.info("StringBuilder:{}", stringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pwd;
    }
}
