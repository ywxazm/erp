package com.ywx.erp.service.impl;

import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.dao.MenuDao;
import com.ywx.erp.dao.RoleDao;
import com.ywx.erp.entity.MenuDo;
import com.ywx.erp.entity.RoleDo;
import com.ywx.erp.service.RoleService;
import com.ywx.erp.vo.TreeVo;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl extends BaseServiceImpl<RoleDo> implements RoleService {

    private RoleDao roleDao;
    private MenuDao menuDao;

    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    /**
     * 根据角色ID，读取角色菜单
     * @return 返回菜单树
     */
    @Override
    public List<TreeVo> readRoleMenu(int uuid) {
        List<TreeVo> treeList = new ArrayList<>();
        MenuDo rootMenu = menuDao.getDo(0);                         //根菜单
        List<MenuDo> menuDoList = roleDao.getDo(uuid).getMenuDoList();  //角色下的菜单列表
        if (null == menuDoList) {
            return null;
        }
        for (MenuDo m1 : rootMenu.getMenus()) {
            TreeVo mt1 = new TreeVo();
            mt1.setId(m1.getMenuid());
            mt1.setText(m1.getMenuname());
            for (MenuDo m2 : m1.getMenus()) {
                TreeVo mt2 = new TreeVo();
                mt2.setId(m2.getMenuid());
                mt2.setText(m2.getMenuname());
                if (menuDoList.contains(m2)) {
                    mt2.setChecked(true);
                }
                mt1.getChildren().add(mt2);
            }
            treeList.add(mt1);
        }
        return treeList;
    }

    /**
     * 更新角色对应的菜单
     * @param uuid       角色id
     * @param checkedStr 菜单ids
     */
    @Override
    public void updateRoleMenu(int uuid, String checkedStr) {
        List<MenuDo> menuDoList = new ArrayList<>();
        RoleDo roleDo = roleDao.getDo(uuid);
        if (null != checkedStr && checkedStr.trim().length() != 0) {
            String[] menuids = checkedStr.split(BaseConstants.DOUHAOSTR);
            for (String s : menuids) {
                MenuDo menuDo = menuDao.getDo(Integer.parseInt(s));
                menuDoList.add(menuDo);
            }
        }
        roleDo.setMenuDoList(menuDoList);
    }

}
