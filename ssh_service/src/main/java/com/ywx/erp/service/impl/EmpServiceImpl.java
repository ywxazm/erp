package com.ywx.erp.service.impl;

import com.opensymphony.xwork2.ActionContext;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.dao.EmpDao;
import com.ywx.erp.dao.RoleDao;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.RoleDo;
import com.ywx.erp.exception.ErpException;
import com.ywx.erp.service.EmpService;
import com.ywx.erp.vo.TreeVo;
import org.apache.shiro.crypto.hash.Md5Hash;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.在添加用户，查询用户，更改密码，重置密码的功能上，都会对提交的密码进行加密处理，且处理方式应该一致
 */
public class EmpServiceImpl extends BaseServiceImpl<EmpDo> implements EmpService {

    private EmpDao empDao;
    private RoleDao roleDao;
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    public void setEmpDao(EmpDao empDao) {
        super.setBaseDao(empDao);
        this.empDao = empDao;
    }

    //常量定义
    public static final int HASHITERATIONS = 2;
    public static final String DEFAULTPWD = "1";
    public static final String USER = "user";
    public static final String PWDERROR = "Pwd Error";

    /**
     * 新增用户时，初始化密码为用户名
     */
    @Override
    public void addDo(EmpDo empDo) {        //初始化密码设置为"1"
        //TODO:此处对于密码的处理用到了MD5
        Md5Hash md5Hash = new Md5Hash(DEFAULTPWD, empDo.getUsername(), HASHITERATIONS);
        empDo.setPwd(md5Hash.toString());
        empDao.addDo(empDo);
    }

    @Override
    public EmpDo findByUsernameAndPwd(String username, String pwd) {
        //TODO:验证密码用到了MD5
        EmpDo empDo = empDao.findByUsernameAndPwd(username, new Md5Hash(pwd, username, HASHITERATIONS).toString());
        System.out.println(empDo);
        return empDo;
    }

    /**
     * 更新密码： 以前台提交的用户ID，查询用户，再根据提交的密码与用户实体密码进行比较
     *
     * @param uuid
     * @param oldPwd
     * @param newPwd
     */
    @Override
    public void updatePwd(int uuid, String oldPwd, String newPwd) {
        EmpDo empDo = empDao.getDo(uuid);
        //旧密码比较
        String md5 = new Md5Hash(oldPwd, empDo.getUsername(), HASHITERATIONS).toString();
        if (!empDo.getPwd().equals(md5)) {
            throw new ErpException(PWDERROR);        //TODO: 错误将被Action捕获，不会中断业务
        }

        Md5Hash md5Hash = new Md5Hash(newPwd, empDo.getUsername(), HASHITERATIONS);
        empDo.setPwd(md5Hash.toString());       //TODO:这个地方用到了持久态
    }

    /**
     * 直接通过用户ID，进行密码的修改,默认值为“1”
     *
     * @param id
     */
    @Override
    public void resetPwd(int id) {
        EmpDo empDo = empDao.getDo(id);
        empDo.setPwd(new Md5Hash(DEFAULTPWD, empDo.getUsername(), HASHITERATIONS).toString());
    }

    @Override
    public List<TreeVo> readEmpRoles(int id) {
        List<TreeVo> rstList = new ArrayList<>();
        EmpDo empDo = empDao.getDo(id);
        List<RoleDo> empRoles = empDo.getRoleDoList();
        List<RoleDo> allRoles = roleDao.list(null, null, null);
        for (RoleDo roleDo : allRoles) {
            TreeVo t1 = new TreeVo();
            t1.setId(roleDo.getUuid());
            t1.setText(roleDo.getName());
            if (empRoles.contains(roleDo)) {
                t1.setChecked(true);
            }
            rstList.add(t1);
        }
        return rstList;
    }

    @Override
    public void updateEmpRoles(int id, String checkedStr) {
        List<RoleDo> roleList = new ArrayList<>();
        EmpDo empDo = empDao.getDo(id);
        if (null != checkedStr && checkedStr.trim().length() != 0) {
            String[] roleIds = checkedStr.split(BaseConstants.DOUHAOSTR);
            for (String s : roleIds) {
                RoleDo roleDo = roleDao.getDo(Integer.parseInt(s));
                roleList.add(roleDo);
            }
        }
        empDo.setRoleDoList(roleList);      //会先删除用户角色表中对应用户的角色信息， 再添加对应的角色信息
    }
}
