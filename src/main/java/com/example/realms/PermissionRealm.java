package com.example.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.*;

public class PermissionRealm extends AuthorizingRealm
{
    @Override
    public String getName()
    {
        return "PermissionRealm";
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        System.out.println(principalCollection.getPrimaryPrincipal());
        System.out.println(principalCollection.getRealmNames());
        Set<String> roles = new HashSet<String>();
        Collections.addAll(roles, "role1", "role2");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.addStringPermission("user:create");
        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        if(authenticationToken.getPrincipal().equals("zhangsan"))
            return new SimpleAuthenticationInfo("zhangsan", "123", this.getName());
        else
            throw new AuthorizationException();
    }
}
