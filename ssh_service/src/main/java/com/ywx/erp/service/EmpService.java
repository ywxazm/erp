package com.ywx.erp.service;

import com.ywx.erp.entity.EmpDo;

public interface EmpService extends BaseService<EmpDo> {

    EmpDo findByUsernameAndPwd(String username, String pwd);

    void updatePwd(Long uuid, String oldPwd, String newPwd);

    EmpDo getCurrentUser();

}
