package com.hwua.config;

import com.hwua.realm.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {
    @Bean
    public Realm userRealm(CredentialsMatcher hashedCredentialsMatcher) throws Exception{
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() throws Exception{
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/pages/login.jsp","anon");
        filterChainDefinitionMap.put("/logout.do","logout");
        filterChainDefinitionMap.put("/login.do","anon");//匿名可访问
        filterChainDefinitionMap.put("/**","authc");//否则返回到LoginUrl所指定的页面中
        shiroFilterChainDefinition.addPathDefinitions(filterChainDefinitionMap);
        return shiroFilterChainDefinition;
    }
    @Bean
    public SessionsSecurityManager securityManager(Realm userRealm){
        SessionsSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    @Bean
    public CredentialsMatcher hashedCredentialsMatcher() throws Exception{
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);// 设置加密算法类型
        credentialsMatcher.setHashIterations(1024);//设置加密次数
        return credentialsMatcher;
    }
}
