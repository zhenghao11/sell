package com.my.sell;


import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.my.sell.viewObject.Food;
import org.hibernate.dialect.HANAColumnStoreDialect;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * created by hzheng on 2017/7/23.
 */
public class MyTest {
    public static void main(String[] args) {
       /* int width = 300;
        int height = 300;
        String format = "png";
        String content = "http://192.168.0.100:8080/customer/index";

        HashMap hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hashMap.put(EncodeHintType.MARGIN,2);

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height);
            String fileName = UUID.randomUUID().toString().replaceAll("-","").substring(0,5);
            Path file = new File("F:/imooc/code/file/" + fileName + ".png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix,format,file);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
/*        Food food = new Food();
        food.setId(1);
        food.setName("a");
        List<Food> foodList = new ArrayList<Food>();
        foodList.add(food);
        foodList.stream().filter(e -> e.getId() == 1).forEach(e -> e.setStock(1000));*/

        ////////////////////////////////////////////////////////////////////
     /*   List<Integer> stringList = new ArrayList<Integer>();
        stringList.add(1);
        stringList.add(4);
        stringList.add(1);
        stringList.add(9);
        stringList.add(3);
        Map<Integer, Integer> collect = stringList.stream().filter(e -> e < 5)
                .collect(Collectors.groupingBy(e -> e, Collectors.summingInt(e -> 1)));
        System.out.println(collect);
    }*/
    }
}
