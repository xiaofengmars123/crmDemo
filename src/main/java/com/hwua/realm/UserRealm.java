package com.hwua.realm;


import com.hwua.pojo.Permission;
import com.hwua.pojo.Role;
import com.hwua.pojo.Users;
import com.hwua.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权使用
     * 对当前登录用户进行授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权。。。。。。");
        //获得登录用户信息的方法1：
        Users user =(Users) principals.getPrimaryPrincipal();
        Users user1 = userService.findUser(user.getUsername());
        //获得登录用户信息的方法2：
//        User user2 = (User) SecurityUtils.getSubject().getPrincipals();
//        System.out.println("2==============="+user2.getId()+" "+user2.getUserName()+" "+user2.getPassword());
        //创建SimpleAuthenticationInfo对象，给流对象中设置相关的角色和权限
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        List<Role> role = user1.getRole();
        //添加角色
        for (Role ro:role) {
            authorizationInfo.addRole(ro.getRoleName());
            List<Permission> permissions = ro.getPermissions();
            //添加角色内的权限
            for (Permission per:permissions) {
                authorizationInfo.addStringPermission(per.getUrl());
            }
        }
        return authorizationInfo;





    }

    /**
     * 对当前登录用户进行身份验证
     * 此方法什么时候调用
     * 使用subject对象调用login方法的时候，底层调用的是securityManager对象的login方法，
     *
     * @param token 从controller中传过来的令牌，Username，Password对象
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        System.out.println(token);
        //拿到用户名
        String userName =String.valueOf(token.getPrincipal());
        Users user=null;
        try {
           user = userService.findUser(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user==null){
            throw new UnknownAccountException();
        }
        //将账户名作为盐的定制化参数
        ByteSource salt = ByteSource.Util.bytes(userName);
        //  SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),salt,super.getName());
        return authenticationInfo;//把此对象返回给shiro，shiro会拿这个对象取和你的subject传过来的token进行密码比对
    }
}
