系统使用技术
springboot + springmvc + hibernate + maven + cas + shiro + mysql

配置文件
系统启动依赖的配置(application.properties}
mysql信息
redis
hibernate

业务需要的配置(app.properties)


// shiro安全过滤规则
com.cecb2b.cms.config.ShiroCasConfiguration.java 如下:
    // 初始化权限控制规则
    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
    static {
        filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
        filterChainDefinitionMap.put("/user", "authc");
        filterChainDefinitionMap.put("/demo/**", "authc");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/**", "anon");
    }
