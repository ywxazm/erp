package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.SupplierDo;
import com.ywx.erp.service.SupplierService;

import java.util.HashMap;

public class SupplierAction extends BaseAction<SupplierDo> {

    private SupplierService supplierService;

    public void setSupplierService(SupplierService supplierService) {
        super.setBaseService(supplierService);
        this.supplierService = supplierService;
    }

    /**
     * 根据ID去获取
     */
    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            SupplierDo supplierDo = supplierService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.uuid" , supplierDo.getUuid());
            map.put("t.name" , supplierDo.getName());
            map.put("t.address" , supplierDo.getAddress());
            map.put("t.contact" , supplierDo.getContact());
            map.put("t.tele" , supplierDo.getTele());
            map.put("t.email" , supplierDo.getEmail());
            map.put("t.type" , supplierDo.getType());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {} ", this, ex.getMessage());
        }
    }
}
