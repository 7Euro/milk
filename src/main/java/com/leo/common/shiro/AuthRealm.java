package com.leo.common.shiro;

import com.leo.common.result.StatusEnum;
import com.leo.common.utils.ShiroUtil;
import com.leo.domain.Role;
import com.leo.domain.User;
import com.leo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.leo.common.constant.AdminConst;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * 创建自定义Realm
 * 用来给shiro注入认证信息和授权信息。
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/22 10:14
 **/
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑......");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户Principal对象
        User user = (User) principals.getPrimaryPrincipal();
        //管理员拥有所有权限
        if(user.getId().equals(AdminConst.ADMIN_ID)){
            info.addRole(AdminConst.ADMIN_ROLE_NAME);
            info.addStringPermission("*:*:*");
            return info;
        }
        //赋予角色和资源授权
        Set<Role> roles = ShiroUtil.getSubjectRoles();
        roles.forEach(role -> {
            info.addRole(role.getName());
            role.getMenus().forEach(menu -> {
                String perms = menu.getPerms();
                if (menu.getStatus().equals(StatusEnum.OK.getCode())
                        && !StringUtils.isEmpty(perms) && !perms.contains("*")) {
                    //授权角色下所有权限
                    info.addStringPermission(perms);
                }
            });
        });

        return info;
    }

    /**
     * 认证
     * @param authenticationTokens
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationTokens) throws AuthenticationException {
        System.out.println("执行认证逻辑......");
        //UsernamePasswordToken用于存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationTokens;
        //获取数据库中的用户名密码
        User user = userService.getByName(token.getUsername());
        //判断用户名是否存在
        if (user == null) {
            throw new UnknownAccountException();
        } else if (user.getStatus().equals(StatusEnum.FREEZED.getCode())) {
            throw new LockedAccountException();
        }
        // 对盐进行加密处理
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());
        /* 传入密码自动判断是否正确
         * 参数1：传入对象给Principal
         * 参数2：正确的用户密码
         * 参数3：加盐处理
         * 参数4：固定写法
         */
        return new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
    }

    /**
     * 自定义密码验证匹配器
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        setCredentialsMatcher(new SimpleCredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
                UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
                SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) authenticationInfo;
                // 获取明文密码及密码盐
                String password = String.valueOf(token.getPassword());
                String salt = CodecSupport.toString(info.getCredentialsSalt().getBytes());

                return equals(ShiroUtil.encrypt(password, salt), info.getCredentials());
            }
        });
    }
}
