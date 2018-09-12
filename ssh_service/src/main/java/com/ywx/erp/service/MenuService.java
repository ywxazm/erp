package com.ywx.erp.service;

import com.ywx.erp.entity.MenuDo;

import java.util.List;

public interface MenuService extends BaseService<MenuDo> {

    MenuDo getMenuDoByEmpId(int id);

    List<MenuDo> getMenuDoListByEmpId(int id);

}
