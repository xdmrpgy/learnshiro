package com.roypan.learnshiro.shiro;

import com.roypan.learnshiro.entity.SysPermission;
import com.roypan.learnshiro.entity.SysRole;
import com.roypan.learnshiro.entity.User;
import com.roypan.learnshiro.repository.UserRepository;
import com.roypan.learnshiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author by Roy Pan
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 权限验证
     * @param principalCollection
     * @return 当前登录用户的角色和权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String userName = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.queryByUserName(userName);

        for (SysRole role : user.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for (SysPermission permission : role.getPermissionList()){
                authorizationInfo.addStringPermission(permission.getName());
            }
        }
        return authorizationInfo;
    }

    /**
     * 用户认证
     * @param token
     * @return 当前用户信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userService.queryByUserName(userName);

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),getName());
        return simpleAuthenticationInfo;
    }
}
