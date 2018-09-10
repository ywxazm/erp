package com.ywx.erp.service;

import com.ywx.erp.entity.RoleDo;
import com.ywx.erp.vo.TreeVo;

import java.util.List;

public interface RoleService extends BaseService<RoleDo> {

    List<TreeVo> readRoleMenu(int uuid);

    void updateRoleMenu(int uuid, String checkedStr);


}
