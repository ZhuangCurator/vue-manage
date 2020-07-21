package com.huahai.baiding.vue.config;

/**
 * @Author: BaiDing
 * @Date: 2018/11/21 20:59
 * @Email: liujiabaiding@foxmail.com
 */
public class RegularCheck {
    public static final String POSITIVE = "^[0-9]*[1-9][0-9]*$";// 正整数

    /**
     * @param str 要校验的字符串
     * @param reg 正则表达式
     * @return
     */
    public static boolean checkString(String str,String reg) {
        return str != null && !str.equals("") && str.matches(reg);
    }
}
