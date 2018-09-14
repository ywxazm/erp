package com.ywx.erp.service.impl;

import com.ywx.erp.dao.MenuDao;
import com.ywx.erp.entity.MenuDo;
import com.ywx.erp.service.MenuService;
import com.ywx.erp.service.jedis.JedisService;

import java.util.Iterator;
import java.util.List;

public class MenuServiceImpl extends BaseServiceImpl<MenuDo> implements MenuService {

    private static MenuDo allMenuDo = new MenuDo();

    private MenuDao menuDao;
    private JedisService jedisService;
    public void setJedisService(JedisService jedisService) {
        this.jedisService = jedisService;
    }
    public void setMenuDao(MenuDao menuDao) {
        super.setBaseDao(menuDao);
        this.menuDao = menuDao;
        allMenuDo = menuDao.getDo(0);
    }

    @Override
    public MenuDo getMenuDoByEmpId(int id) {
        String ids = jedisService.get("getMenuDo_" + id);
        List<MenuDo> menuDoList = null;
        if (null == ids) {
            menuDoList = menuDao.getMenuDoByEmpId(id);
            StringBuffer menuDoIds = new StringBuffer();
            if (null == menuDoList) {
                logger.debug("menuDoList is null");
                return null;
            }
            for (MenuDo m : menuDoList) {
                menuDoIds.append(m.getMenuid()).append(",");
            }
            ids = menuDoIds.toString();
            jedisService.set("getMenuDo_" + id, ids);
            logger.debug("have ids = {}", ids);
        }

        List<MenuDo> menuList01 = allMenuDo.getMenus();  //一级菜单
        Iterator<MenuDo> it01 = menuList01.listIterator();
        while (it01.hasNext()) {
            MenuDo m01 = it01.next();

            List<MenuDo> menuList02 = m01.getMenus();
            Iterator<MenuDo> it02 = menuList02.listIterator();
            int j = -1;
            while (it02.hasNext()) {
                MenuDo m02 = it02.next();
                if (!ids.contains(m02.getMenuid().toString())) {
                    it02.remove();
                }else {
                    j++;
                }
            }

            if (j == -1) {
                it01.remove();
            }
        }

        allMenuDo.setMenus(menuList01);
        return allMenuDo;
    }

    @Override
    public List<MenuDo> getMenuDoListByEmpId(int id) {
        return menuDao.getMenuDoByEmpId(id);
    }
}
