package com.cecb2b.cms.util;


import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 * Created by LuoGuanHai on 2017/1/5.
 */
public class CollectionUtil {

    /**
     * 判断集合是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断集合是否为非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断map是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return CollectionUtils.isEmpty(map);
    }

    /**
     * 判断map是否为非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}
