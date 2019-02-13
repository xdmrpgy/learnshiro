package com.roypan.learnshiro.shiro;

import com.roypan.learnshiro.utils.PasswordHelper;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by Roy Pan
 */
@Configuration
public class ShiroConfig {

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITRATIONS);
        return hashedCredentialsMatcher;
    }

    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setUnauthorizedUrl("/unauthc");
        filterFactoryBean.setSuccessUrl("/home/index");

        Map<String,String> filterChainMap = new HashMap<>();
        filterChainMap.put("/*","anon");//anon：所有用户可访问，通常作为指定页面的静态资源时使用
        filterChainMap.put("/authc/index","authc");//authc：所有已登陆用户可访问
        filterChainMap.put("/authc/admin","roles[admin]");//roles：有指定角色的用户可访问，通过[ ]指定具体角色
        filterChainMap.put("/authc/renewable","perms[Create,Update]");//perms：有指定权限的用户可访问，通过[ ]指定具体权限
        filterChainMap.put("/authc/removeable","perms[Delete]");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return filterFactoryBean;
    }
}
