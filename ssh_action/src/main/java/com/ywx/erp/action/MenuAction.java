package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.MenuDo;
import com.ywx.erp.service.MenuService;

public class MenuAction extends BaseAction<MenuDo> {

    private MenuService menuService;
    public void setMenuService(MenuService menuService) {
        super.setBaseService(menuService);
        this.menuService = menuService;
    }

    //父类ID
    private String menuId;
    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public void getMenuTree() {
        logger.debug("operaObj is = {}, getMenuTree() doing, menuId = {}", this, menuId);
        try {
            MenuDo menuDo = menuService.getDo(menuId);
            write(JSONObject.toJSONString(menuDo));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getMenuTree is error, msg = {}", this, ex.getMessage());
        }
    }
}
