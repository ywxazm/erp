package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.DepDo;
import com.ywx.erp.service.DepService;

import java.util.HashMap;

public class DepAction extends BaseAction<DepDo> {

    private DepService depService;
    public void setDepService(DepService depService) {
        super.setBaseService(depService);
        this.depService = depService;
    }

    @Override
    public void getDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {},  getDo() doing, uuid = {}", this, id);
        try {
            DepDo depDo = depService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TUUID, depDo.getUuid());
            map.put(BaseConstants.TTELE, depDo.getTele());
            map.put(BaseConstants.TNAME, depDo.getName());
            write(JSONObject.toJSONString(map));
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo() is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }
}
