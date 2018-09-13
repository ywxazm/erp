package com.ywx.erp.service.impl;

import com.ywx.erp.dao.MenuDao;
import com.ywx.erp.entity.MenuDo;
import com.ywx.erp.service.MenuService;

import java.util.Iterator;
import java.util.List;

public class MenuServiceImpl extends BaseServiceImpl<MenuDo> implements MenuService {

    private MenuDao menuDao;
    public void setMenuDao(MenuDao menuDao) {
        super.setBaseDao(menuDao);
        this.menuDao = menuDao;
    }

    @Override
    public MenuDo getMenuDoByEmpId(int id) {
        MenuDo menuDo = menuDao.getDo(0);
        List<MenuDo> menuDoList = menuDao.getMenuDoByEmpId(id);
        StringBuffer menuDoIds = new StringBuffer();
        if (null == menuDoList) {
            logger.debug("menuDoList is null");
            return null;
        }
        for (MenuDo m : menuDoList) {
            menuDoIds.append(m.getMenuid()).append(",");
        }
        logger.debug("have ids = {}", menuDoIds);

        List<MenuDo> menuList01 = menuDo.getMenus();  //一级菜单
        Iterator<MenuDo> it01 = menuList01.listIterator();
        while (it01.hasNext()) {
            MenuDo m01 = it01.next();

            List<MenuDo> menuList02 = m01.getMenus();
            Iterator<MenuDo> it02 = menuList02.listIterator();
            int j = -1;
            while (it02.hasNext()) {
                MenuDo m02 = it02.next();
                if (!menuDoIds.toString().contains(m02.getMenuid().toString())) {
                    it02.remove();
                }else {
                    j++;
                }
            }

            if (j == -1) {
                it01.remove();
            }
        }

        menuDo.setMenus(menuList01);
        return menuDo;
    }

    @Override
    public List<MenuDo> getMenuDoListByEmpId(int id) {
        return menuDao.getMenuDoByEmpId(id);
    }
}
