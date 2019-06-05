package com.junjie.shiro;

/**
 * shrio配置类
 */

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.sun.deploy.perf.DeployPerfUtil.put;

@Configuration
public class ShiroConfig {
    /**
     * 创建ShiroFilterFactoryBean
     * \
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器权限拦截
        /**
         * anon:无需登陆 访问资源
         * authc: 必须认证才能访问
         * user ;如果使用rememberMe 功能可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         *---------
         */
        // setFilterChainDefinitionMap
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        //放行  anon
        filterMap.put("/ok", "anon");

        //授权访问   顺序很重要
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        filterMap.put("/*/*", "authc");
//        filterMap.put("/user/add","authc");


        //修改拦截默认的页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 创建 DefaultWebSecurityManager
     *
     * @Qualifier 关联v
     */

    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
//        securityManager.setSessionManager();
        return securityManager;
    }


    /**
     * 创建Realm
     */


    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }


    /**
     * shirodialect
     *
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }
}
