package com.ywx.erp.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.common.WriteDate;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.service.EmpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginAction extends ActionSupport implements WriteDate{

    static final Logger logger = LoggerFactory.getLogger(LoginAction.class);

    private EmpService empService;
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    //常量定义
    private static final String USER = "user";
    private static final String LOGINSUCCESS = "Login Sucess";
    private static final String LOGINFAIL = "Login Fail";

    //接收数据
    private String username;    //用户名
    private String pwd;         //密码
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 用户登录
     * 检查用户是否存在，如果存在则存入session
     * 逻辑：  以提交的用户名为salt, 对密码进行md5加密，再以用户名和密码在表中查找
     *         如果找不到，则返回登录失败，停留在login页面
     *         如果找到了，则将user存储在Session中, 返回登录成功，并跳转的首页
     */
    public void checkUser() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, checkUser() doing, username = {}, pwd = {}", this, username, pwd);
        try{
            //创建令牌
            UsernamePasswordToken upt = new UsernamePasswordToken(username.trim(), pwd.trim());
            //获得主题
            Subject subject = SecurityUtils.getSubject();
            subject.login(upt);

            write(ajaxReturn(BaseConstants.TRUE, LOGINSUCCESS));
            logger.debug("operaObj is = {}, checkUser() store done, cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex){
            write(ajaxReturn(BaseConstants.FALSE, LOGINFAIL));
            logger.debug("operaObj is = {}, checkUser() store done, cast time = {}", this, System.currentTimeMillis() - startTime);
            logger.error("operaObj is = {}, checkUser() is error, msg = {}", this, ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     *  主界面显示用户名实现
     *  ajax请求完成， 如果用户登录了，则在主界面显示用户名
     *  逻辑： 前台发送ajax请求，直接从Session中去取“user”,TODO:这个逻辑是有问题的，无法区分用户，只能够登录1个用户
     */
    public void showName() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, showName() doing", this);
        try {
            EmpDo empDo = (EmpDo) SecurityUtils.getSubject().getPrincipal();
            if (null != empDo) {
                write(ajaxReturn(BaseConstants.TRUE, empDo.getName()));
            } else {
                write(ajaxReturn(BaseConstants.FALSE, BaseConstants.NULLSTR));
            }
            logger.debug("operaObj is = {}, showName() store done, cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.debug("operaObj is = {}, showName() store done, cast time = {}", this, System.currentTimeMillis() - startTime);
            logger.error("operaObj is = {}, showName() is error, msg = {}", this, ex.getMessage());
        }
    }

    /**
     * 退出登录
     * TODO: 同上，这个逻辑也是有问题
     */
    public void loginOut() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, loginOut() doing", this);
        try {
            SecurityUtils.getSubject().logout();
            logger.debug("operaObj is = {}, loginOut() store done, cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.debug("operaObj is = {}, loginOut() store done, cast time = {}", this, System.currentTimeMillis() - startTime);
            logger.error("operaObj is = {}, loginOut() is error, msg = {}", this, ex.getMessage());
        }
    }

}
