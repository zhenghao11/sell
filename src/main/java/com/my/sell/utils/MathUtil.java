package com.my.sell.utils;

/**
 * created by hzheng on 2017/8/1.
 */
public class MathUtil {

    private final static Double MONEY_CONSTANT = 0.01;

    /**
     * 判断金额是否相同
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equal(Double d1, Double d2) {
        double result = Math.abs(d1 - d2);
        if (result < MONEY_CONSTANT) {
            return true;
        } else {
            return false;
        }
    }

}
