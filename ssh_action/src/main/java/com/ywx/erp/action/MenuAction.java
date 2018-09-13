package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.MenuDo;
import com.ywx.erp.service.MenuService;

import java.util.HashMap;
import java.util.List;

public class MenuAction extends BaseAction<MenuDo> {

    private MenuService menuService;
    public void setMenuService(MenuService menuService) {
        super.setBaseService(menuService);
        this.menuService = menuService;
    }

    //接收数据
    private Integer menuId;      //父类ID
    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public void getDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            MenuDo menuDo = menuService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TICON, menuDo.getIcon());
            map.put(BaseConstants.TMENUID, menuDo.getMenuid());
            map.put(BaseConstants.TMENUNAME, menuDo.getMenuname());
            map.put(BaseConstants.TURL, menuDo.getUrl());
            map.put(BaseConstants.TPID, menuDo.getPid());
            write(JSONObject.toJSONString(map));
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception ex) {
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
            logger.error("operaObj is = {}, getDo() is error, msg = {}", this, ex.getMessage());
        }
    }

    /**
     * 提交上的数据为顶层父ID, 然后根据父ID，查找子树，这个控制的过程由Hibernate去完成
     * 要注意表的设计及hibernate自关联的配置
     */
    public void getMenuTree() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, getMenuTree() doing, userid = {}", this, getLoginUser().getUuid());
        try {
            MenuDo menuDo = menuService.getMenuDoByEmpId(getLoginUser().getUuid());
            write(JSONObject.toJSONString(menuDo));
            logger.debug("operaObj is = {}, getMenuTree() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception ex) {
            logger.error("operaObj is = {}, getMenuTree is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, getMenuTree() cast time = {}", this, System.currentTimeMillis() - startTime);
            ex.printStackTrace();
        }
    }

}
