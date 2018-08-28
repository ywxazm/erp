package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.exception.ErpException;
import com.ywx.erp.service.EmpService;

import java.util.HashMap;

public class EmpAction extends BaseAction<EmpDo> {

    private EmpService empService;

    public void setEmpService(EmpService empService) {
        super.setBaseService(empService);
        this.empService = empService;
    }

    /**
     * 根据ID去获取部门信息
     */
    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            EmpDo empDo = baseService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.uuid", empDo.getUuid());
            map.put("t.username", empDo.getUsername());
            map.put("t.pwd", empDo.getPwd());
            map.put("t.name", empDo.getName());
            map.put("t.gender", empDo.getGender());
            map.put("t.email", empDo.getEmail());
            map.put("t.tele", empDo.getTele());
            map.put("t.address", empDo.getAddress());
            //map.put("t.birthday", DateTranf.Date2String(empDo.getBirthday()));          //提交上来的，如果无法解析，可能导致找不到Action的错误
            map.put("t.birthday", empDo.getBirthday());
            map.put("t.depDo", empDo.getDepDo());
            write(JSONObject.toJSONStringWithDateFormat(map, "yyyy-MM-dd HH:mm:ss"));       //TODO: 将日期按格式转换成字符串的方式
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }

    private String oldPwd;
    private String newPwd;
    public String getOldPwd() {
        return oldPwd;
    }
    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
    public String getNewPwd() {
        return newPwd;
    }
    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
    /**
     * 更新密码
     */
    public void updatePwd() {
        logger.debug("operaObj is = {}, updatePwd() doing, oldPwd = {}, newPwd = {}", this, oldPwd, newPwd);
        try {
            EmpDo empDo = empService.getCurrentUser();
            if (null == empDo) {
                write(ajaxReturn(false, "用户未登录"));
            }
            empService.updatePwd(empDo.getUuid(), oldPwd, newPwd);
            write(ajaxReturn(true, "密码修改成功"));
        }catch (ErpException ex) {
            logger.error("operaObj is = {}, updatePwd is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(true, ex.getMessage()));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, updatePwd is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(true, "密码修改失败"));
        }
    }

    /**
     * 重新设置密码,仅供管理员
     */
    public void resetPwd() {
        logger.debug("operaObj is = {}, resetPwd() doing", this, newPwd);
        try {
            empService.resetPwd(id, newPwd);
            write(ajaxReturn(true, "密码修改成功"));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, resetPwd is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(false, "密码修改失败"));
        }
    }
}
