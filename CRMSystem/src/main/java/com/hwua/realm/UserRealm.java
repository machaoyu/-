package com.hwua.realm;

import com.hwua.pojo.Role;
import com.hwua.pojo.Users;
import com.hwua.service.UserService;
import org.apache.shiro.SecurityUtils;
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

    //给当前用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)throws AuthenticationException{
        System.out.println("执行了授权方法.....");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        Users user =(Users) SecurityUtils.getSubject().getPrincipal();
        List<Role> roleList= null;
        try {
            roleList = userService.findUserById(user.getId()).getRoleList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Role role:roleList) {
            authorizationInfo.addRole(role.getRoleName());
        }
        return authorizationInfo;
    }

    //给当前用户进行校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证方法.....");
        //从token获取用户名,从主体传过来的认证信息中获取
        String username = (String) token.getPrincipal();
        //从数据库查找用户
        Users user = null;
        try {
            user = userService.queryUserByUserName(username);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user==null){
            throw  new UnknownAccountException();
        }
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());// 得到佐料
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
        return authenticationInfo;
    }
    }

