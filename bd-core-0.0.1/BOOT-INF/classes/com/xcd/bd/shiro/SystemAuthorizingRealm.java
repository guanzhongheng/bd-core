package com.xcd.bd.shiro;

import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.service.IUserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {


    @Autowired
    @Lazy
    IUserInfoService service;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        try{
            TUserInfo user = service.findByUserName(token.getUsername());
            ByteSource salt = ByteSource.Util.bytes(user.getUserName());
            if(user != null){
                return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        return new SimpleAuthorizationInfo();
    }

}
