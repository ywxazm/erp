package com.ywx.erp.realm;

import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.MenuDo;
import com.ywx.erp.service.EmpService;
import com.ywx.erp.service.MenuService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ErpRealm extends AuthorizingRealm {

    static final Logger logger = LoggerFactory.getLogger(ErpRealm.class);

    private EmpService empService;
    private MenuService menuService;
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, doGetAuthorizationInfo() doing", this);
        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
        try {
            EmpDo empDo = (EmpDo) principals.getPrimaryPrincipal();
            List<MenuDo> menuDoList = menuService.getMenuDoListByEmpId(empDo.getUuid());
            StringBuffer powerStr = new StringBuffer();
            for (MenuDo menuDo : menuDoList) {
                sai.addStringPermission(menuDo.getMenuname());      //告诉shiro，用户有什么样的权限
                powerStr.append(menuDo.getMenuname()).append(",");
            }
            logger.debug("operaObj is = {}, doGetAuthorizationInfo cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("operaObj is = {}, doGetAuthorizationInfo is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, doGetAuthorizationInfo cast time = {}", this, System.currentTimeMillis() - startTime);
            e.printStackTrace();
        }
        return sai;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, doGetAuthenticationInfo() doing", this);
        try {
            UsernamePasswordToken upt = (UsernamePasswordToken) token;
            EmpDo empDo = empService.findByUsernameAndPwd(upt.getUsername(), new String(upt.getPassword()));
            if (null != empDo) {
                return new SimpleAuthenticationInfo(empDo, new String(upt.getPassword()), getName());
            }
            logger.debug("operaObj is = {}, doGetAuthenticationInfo cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("operaObj is = {}, doGetAuthenticationInfo is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, doGetAuthenticationInfo cast time = {}", this, System.currentTimeMillis() - startTime);
        }
        return null;
    }
}
