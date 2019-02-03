package com.springboot.simple.config;

import com.springboot.simple.cache.MySimpleByteSource;
import com.springboot.simple.domain.SysAuth;
import com.springboot.simple.domain.SysRole;
import com.springboot.simple.domain.SysUser;
import com.springboot.simple.service.SysUserService;
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

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService userService;

    /**
     * 处理授权
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = (SysUser) principal.getPrimaryPrincipal(); //表示获取登录后的user
        for (SysRole role:user.getSysRoleList()){
            authorizationInfo.addRole(role.getRole_name()); //将角色名提供给Info
            for (SysAuth auth:role.getSysAuthList()) {
                authorizationInfo.addStringPermission(auth.getAuth_name()); //将权限名称提高给Info
            }
        }
        return authorizationInfo;
    }

    /**
     * 处理认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 1.从token里面获取用户名
        String username = (String) token.getPrincipal();
        // 2.从数据库查找该用户名，若失败，则抛异常
        SysUser user = userService.querySysUserByUserName(username);
        if (user == null) {
            return null;
        }
        // 3.查询成功则验证数据
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                new MySimpleByteSource(username),
                getName()
        );
        return authenticationInfo;
    }
}