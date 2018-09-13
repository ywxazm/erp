package com.ywx.erp.service;

import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.vo.TreeVo;

import java.util.List;

public interface EmpService extends BaseService<EmpDo> {

    EmpDo findByUsernameAndPwd(String username, String pwd);

    void updatePwd(int uuid, String oldPwd, String newPwd);

    void resetPwd(int id);

    List<TreeVo> readEmpRoles(int id);

    void updateEmpRoles(int id, String checkedStr);

}
