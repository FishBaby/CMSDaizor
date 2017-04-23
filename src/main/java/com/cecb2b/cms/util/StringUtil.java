package com.cecb2b.cms.util;

/**
 * 字符串工具类
 *
 * Created by LuoGuanHai on 2017/1/5.
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() < 1;
    }

    /**
     * 判断字符串是否为非空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
