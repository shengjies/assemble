package com.sj.common.shiro;

import com.sj.common.jwt.JwtToken;
import com.sj.common.jwt.JwtUtil;
import com.sj.customer.user.domain.User;
import com.sj.customer.user.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User username = JwtUtil.getUserByToken(principals.toString());
        User u = userService.findByUserName(username.getUsername());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = auth.getCredentials().toString();
        User username = JwtUtil.getUserByToken(token);
        if(username == null){
            throw  new AuthenticationException("token无效");
        }
        User u = userService.findByUserName(username.getUsername());
        if(u == null){
            throw  new AuthenticationException("用户不存在");
        }
        return new SimpleAuthenticationInfo(token,token,"my_realm");
    }

}
