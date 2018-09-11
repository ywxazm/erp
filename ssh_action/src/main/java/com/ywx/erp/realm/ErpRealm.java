package com.ywx.erp.realm;

import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.service.EmpService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class ErpRealm extends AuthorizingRealm {

    private EmpService empService;
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("--------------------授权------------------------");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("--------------------认证------------------------");

        UsernamePasswordToken upt = (UsernamePasswordToken) token;

        EmpDo empDo = empService.findByUsernameAndPwd(upt.getUsername(), upt.getPassword().toString());

        if (null != empDo) {
            return new SimpleAuthenticationInfo(empDo, upt.getPassword().toString(), getName());
        }
        return null;
    }
}
