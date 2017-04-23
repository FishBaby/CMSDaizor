package com.cecb2b.cms.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * 数组工具类
 * Created by LuoGuanHai on 2017/1/5.
 */
public class ArrayUtil {

    /**
     *  判断数组是否为非空
     * /
     public static boolean isNotEmpty(Object[] array) {
     return !isEmpty(array);
     }

     /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}
