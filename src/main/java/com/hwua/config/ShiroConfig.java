package com.hwua.config;

import com.hwua.realm.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public Realm userRealm(CredentialsMatcher credentialsMatcher)throws Exception{
        UserRealm userRealm=new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    /**
     * 通过DefaultWebSecurityManager创建securityManager对象（是shiro核心对象）
     * @return
     * @throws Exception
     */
    @Bean
    public SessionsSecurityManager securityManager(Realm userRealm)throws Exception{
        SessionsSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    /**
     * 配置一个ShiroFilterFactoryBean对象
     * 将上面方法中创建的securityManager核心对象配置到ShiroFilterFactoryBean对象中
     * 并进行对拦截器的拦截路径和认证级别进行设置
     *
     * @return
     * @throws Exception
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SessionsSecurityManager sessionsSecurityManager)throws Exception{
        ShiroFilterFactoryBean filterFactoryBean=new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(sessionsSecurityManager);
        filterFactoryBean.setLoginUrl("/403.jsp");//当认证失败的时候跳转到Login.jsp页面
        Map<String,String> map=new LinkedHashMap<>();
        map.put("/index","anon");//此过滤器代表不用认证，直接可以访问
        map.put("/login","anon");//此过滤器代表不用认证，直接可以访问
        map.put("/pages/**","authc");
        filterFactoryBean.setFilterChainDefinitionMap(map);
        return filterFactoryBean;
    }


    /**
     * 配置一个CredentialsMatcher类型的加密对象
     * 当调用自定义realm验证方法的时候，会把表单传过来的密码交给我们的加密匹配器对象进行加密
     * 加密好以后在和数据库中渠道的密码进行比对
     * @return
     * @throws Exception
     */
    @Bean
    public CredentialsMatcher credentialsMatcher()throws Exception{
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);//选择对应的加密设置，这里使用的MD5
        hashedCredentialsMatcher.setHashIterations(1024);//设置加密次数
        return hashedCredentialsMatcher;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }


//    /**
//     * 配置一个shiro过滤器，用来拦截所有的请求，对不同的请求url交给不同的过滤器来进行处理
//     *
//     * @return
//     * @throws Exception
//     */
//    public ShiroFilterChainDefinition shiroFilterChainDefinition()throws Exception{
//        DefaultShiroFilterChainDefinition shiroFilter=new DefaultShiroFilterChainDefinition();
//        Map<String,String> map=new LinkedHashMap<>();
//        map.put("/manager/user","anon");//anon此过滤器代表不用认证，直接可以访问
//        map.put("/manager/**","authc");//此过滤器代表认证过滤器，也就是说，此url必须登录后才能访问
//        map.put("/login","anon");
//        map.put("/manager/logout","logout");//此过滤其代表认证过滤器，也就是说，此url必须登录后才能访问
//        shiroFilter.addPathDefinitions(map);
//        return shiroFilter;
//
//    }


}
