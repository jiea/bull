package com.jiea.bull.shiro;

import com.jiea.bull.common.enums.UserStatusType;
import com.jiea.bull.common.exception.BullException;
import com.jiea.bull.common.utils.JwtUtils;
import com.jiea.bull.domain.User;
import com.jiea.bull.service.AuthorizationService;
import com.jiea.bull.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Component
public class ShiroRealm extends AuthorizingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        Long userId = user.getId();

        // 用户权限列表
        Set<String> permsSet = authorizationService.getUserPermissions(userId);
        // 用户角色列表
        Set<String> rolesSet = authorizationService.getUserRoles(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        info.setRoles(rolesSet);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();

        // 认证这一步骤是否验证token是否合法就可以
        Long userId = JwtUtils.getSubject(token);

        User user = userService.getById(userId);

        if(Objects.isNull(user) || UserStatusType.DISABLE.getIndex() == user.getStatus()){
            throw new BullException("用户被禁用");
        }

        return new SimpleAuthenticationInfo(user, token, getName());
    }
}
