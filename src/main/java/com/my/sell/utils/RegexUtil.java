package com.my.sell.utils;

/**
 * created by hzheng on 2017/8/6.
 */
public class RegexUtil {

    private final static String PRODUCTIDREGEX = "\\d+";

    private final static String PRICEREGEX = "\\d{1,8}\\.\\d{1,2}";

    private final static String PRODUCTSTOCKREGEX = "[1-9]\\d+";

    /**
     * productId的正则表达式
     *
     * @param productId
     * @return
     */
    public static Boolean productIdRegex(String productId) {
        return productId.matches(PRODUCTIDREGEX);
    }

    /**
     * 价格的正则
     *
     * @param price
     * @return
     */
    public static Boolean priceRegex(String price) {
        return price.matches(PRICEREGEX);
    }

    /**
     * 库存的正则
     *
     * @param productIcon
     * @return
     */
    public static Boolean productStockRegex(Integer productIcon) {
        return productIcon.toString().matches(PRODUCTSTOCKREGEX);
    }
}
