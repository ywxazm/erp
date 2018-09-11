package com.ywx.erp.dao;

import com.ywx.erp.entity.MenuDo;

import java.util.List;

public interface MenuDao extends BaseDao<MenuDo> {

    List<MenuDo> getMenuDoByEmpId(int id);
}
