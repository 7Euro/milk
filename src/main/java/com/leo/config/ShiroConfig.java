package com.leo.config;

import com.leo.common.shiro.AuthRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import java.util.LinkedHashMap;

/**Shiro配置类
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/20 18:47
 **/
@Configuration
public class ShiroConfig {

    /**
     * 创建shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager,ShiroProjectProperties properties) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         *  过滤规则（注意优先级）
         *  —anon 无需认证(登录)可访问
         * 	—authc 必须认证才可访问
         * 	—perms[标识] 拥有资源权限才可访问
         * 	—role 拥有角色权限才可访问
         * 	—user 认证和自动登录可访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/logout", "anon");
        filterMap.put("/captcha", "anon");
        filterMap.put("/noAuth", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/lib/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        // 通过yml配置文件方式配置的[anon]忽略规则
        String[] excludes = properties.getExcludes().split(",");
        for (String exclude : excludes) {
            if (!StringUtils.isEmpty(exclude.trim())) {
                filterMap.put(exclude, "anon");
            }
        }
        // 拦截根目录下所有路径，需要放行的路径必须在之前添加
        filterMap.put("/**", "authc");

        // 设置过滤规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        // 设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 未授权错误页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManage
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("authRealm")AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联一个realm
        securityManager.setRealm(authRealm);
        return securityManager;
    }

    /**
     * 创建realm
     */
    @Bean(name = "authRealm")
    public AuthRealm getAuthRealm() {
        return new AuthRealm();
    }

}