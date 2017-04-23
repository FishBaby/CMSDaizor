package com.cecb2b.cms.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by LuoGuanHai on 2017/1/5.
 */
public class CastUtil {

    /**
     * 转为String类型
     */
    public static String castString(Object obj) {
        return castString(obj, "");
    }

    /**
     * 转为String类型(提供默认值)
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为double类型(默认值为0)
     */
    public static double castDouble(Object obj) {
        return castDouble(obj, 0);
    }

    /**
     * 转为double类型(提供默认值)
     */
    public static double castDouble(Object obj, double defaultValue) {
        double value = defaultValue;
        if (null != obj) {
            String strValue = castString(obj);
            if (StringUtils.isNotBlank(strValue)) {
                try {
                    value = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为long类型(默认值为0)
     */
    public static Long castLong(Object obj) {
        return castLong(obj, 0);
    }

    /**
     * 转为long类型(提供默认值)
     */
    public static Long castLong(Object obj, long defaultValue) {
        long value = defaultValue;
        if (null != obj) {
            String strValue = castString(obj);
            if (StringUtils.isNotBlank(strValue)) {
                try {
                    value = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为int类型(默认值为0)
     */
    public static int castInt(Object obj){
        return castInt(obj, 0);
    }

    /**
     * 转为int类型(提供默认值)
     */
    public static int castInt(Object obj, int defaultValue) {
        int value = defaultValue;
        if (null != obj) {
            String strValue = castString(obj);
            if (StringUtils.isNotBlank(strValue)) {
                try {
                    value = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static float castFload(Object obj){
        return castFloat(obj, 0);
    }

    public static float castFloat(Object obj, float defaultValue) {
        float value = defaultValue;
        if (null != obj){
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)){
                try {
                    value = Float.parseFloat(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为boolean类型(默认值为false)
     */
    public static boolean castBoolean(Object obj) {
        return castBoolean(obj, false);
    }

    /**
     * 转为boolean类型(提供默认值)
     */
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean value = defaultValue;
        if (null != obj) {
            String strValue = castString(obj);
            if (StringUtils.isNotBlank(strValue)) {
                try {
                    value = Boolean.valueOf(strValue);
                } catch (Exception e) {
                    value = defaultValue;
                }
            }
        }
        return defaultValue;
    }
}
