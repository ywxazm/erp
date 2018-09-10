package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.exception.ErpException;
import com.ywx.erp.service.EmpService;
import com.ywx.erp.vo.TreeVo;

import java.util.HashMap;
import java.util.List;

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
    private String checkedStr; //角色ID
    public String getCheckedStr() {
        return checkedStr;
    }
    public void setCheckedStr(String checkedStr) {
        this.checkedStr = checkedStr;
    }
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
        Long startTime = System.currentTimeMillis();
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
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
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

    /**
     * 读取角色
     */
    public void readEmpRoles() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, readEmpRoles doing, id = {}", this, id);
        try {
            List<TreeVo> treeVoList = empService.readEmpRoles(id);
            write(JSONObject.toJSONString(treeVoList));

            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, readEmpRoles done, cast time = {}", this, endTime - startTime);
        }catch (Exception ex) {
            Long endTime = System.currentTimeMillis();
            logger.error("operaObj is = {}, readEmpRoles is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, readEmpRoles done, cast time = {}", this, endTime - startTime);
        }
    }

    /**
     * 更新角色
     */
    public void updateEmpRoles() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, readEmpRoles doing, id = {}", this, id);
        try {
            empService.updateEmpRoles(id, checkedStr);
            write(ajaxReturn(true, "更新成功"));
            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, updateEmpRoles done, cast time = {}", this, endTime - startTime);
        }catch (Exception ex) {
            write(ajaxReturn(true, "更新失败"));
            Long endTime = System.currentTimeMillis();
            logger.error("operaObj is = {}, updateEmpRoles is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, updateEmpRoles done, cast time = {}", this, endTime - startTime);
        }
    }


}
