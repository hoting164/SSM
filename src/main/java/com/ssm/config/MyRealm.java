package com.ssm.config;

import com.ssm.entity.Permission;
import com.ssm.entity.Employee;
import com.ssm.service.PermissionService;
import com.ssm.service.EmpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private EmpService service;

    @Autowired
    private PermissionService pservice;
    /**
     * 权限检验
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        Employee emp = (Employee) subject.getPrincipal();
        info.addRole(emp.getRoleinfo());
        List<Permission> perms = pservice.getPermsByRoleId(emp.getRoleid());
        for (Permission perm : perms) {
            if(perm.getPinfo()!=null&&!"".equals(perm.getPinfo())) {
                info.addStringPermission(perm.getPinfo());
            }
        }
        return info;
    }


    /**
     * 首先执行登录验证
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken up = (UsernamePasswordToken) authenticationToken;
        Employee emp = service.SelectByName(up.getUsername());
        if(emp!=null){
            return new SimpleAuthenticationInfo(emp, emp.getPassword(), getName());
        }else return null;
    }
}
