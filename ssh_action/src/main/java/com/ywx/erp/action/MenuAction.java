package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.MenuDo;
import com.ywx.erp.service.MenuService;

import java.util.HashMap;

public class MenuAction extends BaseAction<MenuDo> {

    private MenuService menuService;
    public void setMenuService(MenuService menuService) {
        super.setBaseService(menuService);
        this.menuService = menuService;
    }

    /**
     * 提交上的数据为顶层父ID, 然后根据父ID，查找子树，这个控制的过程由Hibernate去完成
     * 要注意表的设计及hibernate自关联的配置
     */
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

    /**
     * 根据ID去获取
     */
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            MenuDo menuDo = menuService.getDo(id.toString());
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.icon", menuDo.getIcon());
            map.put("t.menuid", menuDo.getMenuid());
            map.put("t.menuname", menuDo.getMenuname());
            map.put("t.url", menuDo.getUrl());
            map.put("t.pid", menuDo.getPid());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }
}
