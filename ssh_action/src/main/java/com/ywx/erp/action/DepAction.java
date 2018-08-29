package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
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
        logger.debug("operaObj is = {},  getDo() doing, uuid = {}", this, id);
        try {
            DepDo depDo = (DepDo) baseService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.uuid", depDo.getUuid());
            map.put("t.tele", depDo.getTele());
            map.put("t.name", depDo.getName());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {},  getDo is error, msg = {}", this, ex.getMessage());
        }
    }
}