package com.junjie.shiro;

import com.junjie.Service.UserService;
import com.junjie.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的realm
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //执行授权逻辑

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑!");

        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Subject subject= SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();

        info.addStringPermission(user.getPerms());
        return info;
    }

//     * 执行认证逻辑
//     * @param principalCollection
//     * @return
//     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑!");

        //假设用户名和密码


        //编写shiro逻辑判断
        UsernamePasswordToken tok= (UsernamePasswordToken) authenticationToken;


        User user = userService.findUser(tok.getUsername());
        if(user!=null){
            return  new SimpleAuthenticationInfo(user,user.getPassword(),"");
        }
        //判断密码 new子类
        return  null;



    }
}
