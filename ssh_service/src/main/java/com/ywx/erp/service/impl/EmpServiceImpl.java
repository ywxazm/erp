package com.ywx.erp.service.impl;

import com.opensymphony.xwork2.ActionContext;
import com.ywx.erp.dao.EmpDao;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.exception.ErpException;
import com.ywx.erp.service.EmpService;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 1.在添加用户，查询用户，更改密码，重置密码的功能上，都会对提交的密码进行加密处理，且处理方式应该一致
 */
public class EmpServiceImpl extends BaseServiceImpl<EmpDo> implements EmpService {

    private EmpDao empDao;

    public void setEmpDao(EmpDao empDao) {
        super.setBaseDao(empDao);
        this.empDao = empDao;
    }

    @Override
    public void addDo(EmpDo empDo) {        //初始化密码设置为用户名
        Md5Hash md5Hash = new Md5Hash(empDo.getUsername(), empDo.getUsername(), 2);      //TODO:此处对于密码的处理用到了MD5
        empDo.setPwd(md5Hash.toString());
        empDao.addDo(empDo);
    }

    @Override
    public EmpDo findByUsernameAndPwd(String username, String pwd) {
        return empDao.findByUsernameAndPwd(username, new Md5Hash(pwd, username,2).toString());   //TODO:验证密码用到了MD5
    }

    /**
     * 直接从session中拿用户
     * @return
     */
    @Override
    public EmpDo getCurrentUser() {
        EmpDo empDo = (EmpDo) ActionContext.getContext().getSession().get("user");
        if (null != empDo) {
            return empDo;
        }
        return null;
    }

    /**
     * 更新密码： 以前台提交的用户ID，查询用户，再根据提交的密码与用户实体密码进行比较
     * @param uuid
     * @param oldPwd
     * @param newPwd
     */
    @Override
    public void updatePwd(Long uuid, String oldPwd, String newPwd) {
        EmpDo empDo = empDao.getDo(uuid);
        //旧密码比较
        String md5 = new Md5Hash(oldPwd, empDo.getUsername(), 2).toString();
        if (!empDo.getPwd().equals(md5)) {
            throw new ErpException("原密码错误");        //TODO: 错误将被Action捕获，不会中断业务
        }

        Md5Hash md5Hash = new Md5Hash(newPwd, empDo.getUsername(), 2);
        empDo.setPwd(md5Hash.toString());       //TODO:这个地方用到了持久态
    }

    /**
     * 直接通过用户ID，进行密码的修改,默认值为“1”
     * @param id
     */
    @Override
    public void resetPwd(long id) {
        EmpDo empDo = empDao.getDo(id);
        empDo.setPwd(new Md5Hash("1", empDo.getUsername(), 2).toString());
    }
}
