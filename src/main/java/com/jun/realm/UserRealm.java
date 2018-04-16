package com.jun.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jun on 2018/4/17.
 */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private IUserService iUserService;


    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //设置当前用户拥有的角色
        Set<String> roles = new HashSet<String>();
        roles.add("r1");
        roles.add("r2");
        //加入角色名
        authorizationInfo.setRoles(roles);
        //设置当前用户的权限
        Set<String> permissionNames = new HashSet<String>();
        permissionNames.add("add");
        permissionNames.add("view");
        authorizationInfo.setStringPermissions(permissionNames);

        return authorizationInfo;
    }

    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String)token.getPrincipal();
        IUser iUser = iUserService.findByUsername(userName);
        if(iUser==null){
            //用户不存在
            throw new UnknownAccountException();
        }

//      if(iUser.getLocked()=1){
//          throw new LockedAccountException();
//      }

//      iUser.setPassword("003d75ffdd78e6469723594eae0e21aa");
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(iUser.getLoginUser(),
                iUser.getPassword(), getName());

        return simpleAuthenticationInfo;
    }

}