package com.my.sell.utils;

import com.my.sell.enums.ExceptionEnum;
import com.my.sell.exception.SellException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 文件类
 * created by hzheng on 2017/8/6.
 */
public class FileUtil {

    //图片保存路径
    private final static Path imgPath = Paths.get("F:/git/qianduansell/picture/");
    //图片打开路径
    private final static String openPath = "/files/";

    /**
     * 保存图片
     * @param file
     * @return
     */
    public static String saveImg(MultipartFile file){
        try {
            Files.createDirectories(imgPath);
            String fileName = UUID.randomUUID().toString()+".jpg";
            Files.copy(file.getInputStream(),imgPath.resolve(fileName));
            return openPath+fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
