package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.common.BaseConstants;
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

    //常量定义
    private static final String NOLOGIN = "no login";
    private static final String UPDATEPWDSUCCESS = "update pwd success";
    private static final String UPDATEPWDFAIL = "update pwd fail";

    //接收数据
    private String oldPwd;  //旧密码
    private String newPwd;  //新密码
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

    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            EmpDo empDo = baseService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TUUID, empDo.getUuid());
            map.put(BaseConstants.TUSERNAME, empDo.getUsername());
            map.put(BaseConstants.TPWD, empDo.getPwd());
            map.put(BaseConstants.TNAME, empDo.getName());
            map.put(BaseConstants.TGENDER, empDo.getGender());
            map.put(BaseConstants.TEMAIL, empDo.getEmail());
            map.put(BaseConstants.TTELE, empDo.getTele());
            map.put(BaseConstants.TADDERSS, empDo.getAddress());
            //提交上来的，如果无法解析，可能导致找不到Action的错误
            map.put(BaseConstants.TBIRTHDAY, empDo.getBirthday());
            map.put(BaseConstants.TDEPDO, empDo.getDepDo());
            //TODO: 将日期按格式转换成字符串的方式
            write(JSONObject.toJSONStringWithDateFormat(map, BaseConstants.DATEFORMAT));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }

    /**
     * 更新密码
     */
    public void updatePwd() {
        logger.debug("operaObj is = {}, updatePwd() doing, oldPwd = {}, newPwd = {}", this, oldPwd, newPwd);
        try {
            EmpDo empDo = empService.getCurrentUser();
            if (null == empDo) {
                write(ajaxReturn(BaseConstants.FALSE, NOLOGIN));
            }
            empService.updatePwd(empDo.getUuid(), oldPwd, newPwd);
            write(ajaxReturn(BaseConstants.TRUE, UPDATEPWDSUCCESS));
        }catch (ErpException ex) {
            logger.error("operaObj is = {}, updatePwd is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(BaseConstants.FALSE, ex.getMessage()));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, updatePwd is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(BaseConstants.FALSE, UPDATEPWDFAIL));
        }
    }

    /**
     * 重新设置密码
     */
    public void resetPwd() {
        logger.debug("operaObj is = {}, resetPwd doing, id = {}", this, id);
        try {
            empService.resetPwd(id);
            write(ajaxReturn(BaseConstants.TRUE, UPDATEPWDSUCCESS));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, resetPwd is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(BaseConstants.FALSE, UPDATEPWDFAIL));
        }
    }
}
