package com.ywx.erp.service.impl;

import com.opensymphony.xwork2.ActionContext;
import com.ywx.erp.dao.EmpDao;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.exception.ErpException;
import com.ywx.erp.service.EmpService;
import org.apache.shiro.crypto.hash.Md5Hash;

public class EmpServiceImpl extends BaseServiceImpl<EmpDo> implements EmpService {

    private EmpDao empDao;

    public void setEmpDao(EmpDao empDao) {
        super.setBaseDao(empDao);
        this.empDao = empDao;
    }

    @Override
    public void addDo(EmpDo empDo) {
        Md5Hash md5Hash = new Md5Hash(empDo.getPwd(), empDo.getUsername(), 2);      //TODO:此处对于密码的处理用到了MD5
        empDo.setPwd(md5Hash.toString());
        empDao.addDo(empDo);
    }

    @Override
    public EmpDo findByUsernameAndPwd(String username, String pwd) {
        logger.info("{}", new Md5Hash(pwd, username,2).toString());
        return empDao.findByUsernameAndPwd(username, new Md5Hash(pwd, username,2).toString());   //TODO:验证密码用到了MD5
    }

    @Override
    public EmpDo getCurrentUser() {
        EmpDo empDo = (EmpDo) ActionContext.getContext().getSession().get("user");
        if (null != empDo) {
            return empDo;
        }
        return null;
    }

    @Override
    public void updatePwd(Long uuid, String oldPwd, String newPwd) {
        EmpDo empDo = empDao.getDo(uuid);
        //旧密码比较
        String md5 = new Md5Hash(oldPwd, empDo.getUsername(), 2).toString();
        if (empDo.getPwd() != md5) {
            throw new ErpException("原密码错误");
        }

        Md5Hash md5Hash = new Md5Hash(newPwd, empDo.getUsername(), 2);
        empDo.setPwd(md5Hash.toString());       //TODO:这个地方用到了持久态
    }
}
