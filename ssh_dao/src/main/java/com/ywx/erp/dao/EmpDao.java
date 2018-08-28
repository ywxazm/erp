package com.ywx.erp.dao;

import com.ywx.erp.entity.EmpDo;

public interface EmpDao extends BaseDao<EmpDo>{

    EmpDo findByUsernameAndPwd(String username, String pwd);

}
