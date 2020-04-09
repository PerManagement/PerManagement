package com.newer.shiro;

import com.newer.domain.Resource;
import com.newer.service.ResourceService;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Create by 何辉
 * 2020/3/28 11:10
 */
@Configuration
public class shiroConfig {
    @Autowired
    private ResourceService service;
    /**
     * 需要提供一个 Realm 的实例。
     * @return
     */
    @Bean
    Realm realm(){
        return new Realm();
    }

    /**
     * 需要配置一个 SecurityManager，在 SecurityManager 中配置 Realm。
     * @return
     */
    @Bean
    DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(realm());
        return manager;
    }

    /**
     * 配置一个 ShiroFilterFactoryBean ，在 ShiroFilterFactoryBean 中指定路径拦截规则等。
     * @return
     */
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition (){
        DefaultShiroFilterChainDefinition definition=new DefaultShiroFilterChainDefinition();
        System.out.println("权限管理");
        definition.addPathDefinition("user/doLogin","anon");
        List<Resource> allURL = this.service.findAllURL();
        for (Resource resource:allURL){
            if (resource.getResourcecode()==null||resource.getUrl()==null){
                continue;
            }
            definition.addPathDefinition(resource.getUrl(),"authc,roles["+resource.getResourcecode()+"]");
        }
      // definition.addPathDefinition("/hello1","authc,roles[hello1]");
       // definition.addPathDefinition("/hello","authc,roles[hello]");
        return definition;
    }
}
