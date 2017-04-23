package com.cecb2b.cms.common.helper;

import com.cecb2b.cms.util.ConfigConstant;
import com.cecb2b.cms.util.PropertiesUtil;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Properties;

/**
 * 属性文件帮助类
 * Created by LuoGuanHai on 2017/1/5.
 */
public class ConfigHelper {

    private static volatile Map<String, String> LOCAL_CONFIG_CACHE = Maps.newConcurrentMap();

    private static final Properties CONFIG_PROPS = PropertiesUtil.load(ConfigConstant.CONFIG_FILE);

    private static String getAppPropertiesValue(String name) {
        String value = "";
        if (LOCAL_CONFIG_CACHE.containsKey(name)) {
            value = LOCAL_CONFIG_CACHE.get(name);
        } else {
            value = PropertiesUtil.getString(CONFIG_PROPS, name);
            LOCAL_CONFIG_CACHE.put(name, value);
        }
        return value;
    }

    /**
     * 获取图片服务域名
     * @return
     */
    public static String getImgServerDomain(){
        return getAppPropertiesValue(ConfigConstant.IMG_SERVER_DOMAIN);
    }

    public static String getCasServerUrlPrefix(){
        return getAppPropertiesValue("cas_server_url_prefix");
    }

    public static String getCurrSystemDomainUrl(){
        return getAppPropertiesValue("curr_system_domain_url");
    }

}
