package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.RoleDo;
import com.ywx.erp.service.RoleService;
import com.ywx.erp.vo.TreeVo;

import java.util.List;

public class RoleAction extends BaseAction<RoleDo> {

    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        super.setBaseService(roleService);
        this.roleService = roleService;
    }

    //接收数据
    private String checkedStr;
    public String getCheckedStr() {
        return checkedStr;
    }
    public void setCheckedStr(String checkedStr) {
        this.checkedStr = checkedStr;
    }

    /**
     * 读取角色菜单
     */
    public void readRoleMenu() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, readRoleMenu() doing", this);
        try {
            List<TreeVo> menuTreeVoList = roleService.readRoleMenu(getId());
            write(JSONObject.toJSONString(menuTreeVoList));
            logger.debug("operaObj is = {}, readRoleMenu() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception ex) {
            logger.error("operaObj is = {}, readRoleMenu is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, readRoleMenu() cast time = {}", this, System.currentTimeMillis() - startTime);
            ex.printStackTrace();
        }
    }

    /**
     * 更新角色菜单
     */
    public void updateRoleMenu() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, updateRoleMenu() doing, uuid = {}, checkedStr = {}", this, id, checkedStr);
        try {
            roleService.updateRoleMenu(getId(), checkedStr);
            write(ajaxReturn(true, "更新成功"));
            logger.debug("operaObj is = {}, updateRoleMenu() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception ex) {
            logger.error("operaObj is = {}, updateRoleMenu is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, updateRoleMenu() cast time = {}", this, System.currentTimeMillis() - startTime);
            write(ajaxReturn(false, "更新失败"));
        }
    }
}
