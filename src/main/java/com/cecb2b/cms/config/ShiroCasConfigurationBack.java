//package com.cecb2b.cms.config;
//
//import com.cecb2b.cms.common.helper.ConfigHelper;
//import com.cecb2b.cms.common.shiro.MyShiroCasRealm;
//import org.apache.shiro.cache.ehcache.EhCacheManager;
//import org.apache.shiro.cas.CasFilter;
//import org.apache.shiro.cas.CasSubjectFactory;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.web.filter.DelegatingFilterProxy;
//
//import javax.servlet.Filter;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * Shiro集成Cas配置
// */
//@Configuration
//public class ShiroCasConfigurationBack {
//
//    private static final Logger logger = LoggerFactory.getLogger(ShiroCasConfigurationBack.class);
//
//    // Cas登录页面地址
//    public static String casLoginUrl = ConfigHelper.getCasServerUrlPrefix() + "/login";
//    // Cas登出页面地址
//    public static String casLogoutUrl = ConfigHelper.getCasServerUrlPrefix() + "/logout";
//
//    // casFilter UrlPattern
//    public static String casFilterUrlPattern = "/shiro-cas";
//    // 登录地址
//    public static String loginUrl = casLoginUrl + "?service=" + ConfigHelper.getCurrSystemDomainUrl() + casFilterUrlPattern;
//
//    // 初始化权限控制规则
//    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//    static {
//        filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
//        filterChainDefinitionMap.put("/user", "authc");
//        filterChainDefinitionMap.put("/demo/index", "authc");
//        filterChainDefinitionMap.put("/login", "anon");
//        filterChainDefinitionMap.put("/**", "authc");
//    }
//
//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean(name = "myShiroCasRealm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public MyShiroCasRealm myShiroCasRealm(EhCacheManager cacheManager) {
//        MyShiroCasRealm realm = new MyShiroCasRealm();
//       // realm.setCacheManager(cacheManager);
//        return realm;
//    }
//
//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public EhCacheManager getEhCacheManager() {
//        EhCacheManager em = new EhCacheManager();
//        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
//        return em;
//    }
//
//
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroCasRealm myShiroCasRealm) {
//        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
//        dwsm.setRealm(myShiroCasRealm);
//        //      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
//        // dwsm.setCacheManager(getEhCacheManager());
//        // 指定 SubjectFactory
//        dwsm.setSubjectFactory(new CasSubjectFactory());
//        return dwsm;
//    }
//
//    /**
//     * 网络请求的权限过滤, 拦截外部请求
//     */
//    @Bean(name = "shiroFilter")
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, CasFilter casFilter) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 必须设置 SecurityManager
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl(loginUrl);
//        // 登录成功后要跳转的连接
//        shiroFilterFactoryBean.setSuccessUrl("/demo/index");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//        // 添加casFilter到shiroFilter中
//        Map<String, Filter> filters = new HashMap<>();
//        filters.put("casFilter", casFilter);
//        shiroFilterFactoryBean.setFilters(filters);
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
//        daap.setProxyTargetClass(true);
//        return daap;
//    }
//
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//
//
//
//
//    /**
//     * 注册DelegatingFilterProxy（Shiro）
//     *
//     * @return
//     * @author SHANHY
//     * @create  2016年1月13日
//     */
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
//        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        return filterRegistration;
//    }
//
//
//
//
//    /**
//     * CAS过滤器
//     */
//    @Bean(name = "casFilter")
//    public CasFilter getCasFilter() {
//        CasFilter casFilter = new CasFilter();
//        casFilter.setName("casFilter");
//        casFilter.setEnabled(true);
//        // 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的 doGetAuthenticationInfo 方法向CasServer验证tiket
//        casFilter.setFailureUrl(loginUrl);// 我们选择认证失败后再打开登录页面
//        return casFilter;
//    }
//
//
//
//}