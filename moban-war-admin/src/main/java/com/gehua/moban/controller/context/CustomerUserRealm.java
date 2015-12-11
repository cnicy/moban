package com.gehua.moban.controller.context;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rono on 2015/12/11.
 */
public class CustomerUserRealm extends AuthorizingRealm {
    public CustomerUserRealm(){
        super.setAuthenticationCachingEnabled(false);
        // 授权
        super.setAuthorizationCacheName("authorizationCache");
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher("MD5");
        credentialsMatcher.setHashIterations(2); //  两次加密
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 授权，即权限验证，验证某个已认证的用户是否拥有某个权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roleStrings = new HashSet<String>();
        Set<String> permissionStrings = new HashSet<String>();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleStrings);
        authorizationInfo.setStringPermissions(permissionStrings);

        return authorizationInfo;
    }

    /**
     * 身份认证/登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        final String userName = (String)token.getPrincipal();
        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(
                        "userName", //用户名
                        "7d94103a7f26c296f9c911b027faeff4", //密码
                        ByteSource.Util.bytes("4eecf205fb1f31805765be1a60f8d16b"),//salt = username+salt
                        getName()  //realm name
                );
        return authenticationInfo;
    }
}
