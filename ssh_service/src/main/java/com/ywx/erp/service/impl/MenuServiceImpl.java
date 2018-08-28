package com.ywx.erp.service.impl;

import com.ywx.erp.dao.MenuDao;
import com.ywx.erp.entity.MenuDo;
import com.ywx.erp.service.MenuService;

public class MenuServiceImpl extends BaseServiceImpl<MenuDo> implements MenuService {

    private MenuDao menuDao;
    public void setMenuDao(MenuDao menuDao) {
        super.setBaseDao(menuDao);
        this.menuDao = menuDao;
    }

}
