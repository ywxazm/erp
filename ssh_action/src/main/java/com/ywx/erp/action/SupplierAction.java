package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.SupplierDo;
import com.ywx.erp.service.SupplierService;

import java.util.HashMap;

public class SupplierAction extends BaseAction<SupplierDo> {

    private SupplierService supplierService;
    public void setSupplierService(SupplierService supplierService) {
        super.setBaseService(supplierService);
        this.supplierService = supplierService;
    }

    @Override
    public void getDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            SupplierDo supplierDo = supplierService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TUUID, supplierDo.getUuid());
            map.put(BaseConstants.TNAME, supplierDo.getName());
            map.put(BaseConstants.TADDERSS, supplierDo.getAddress());
            map.put(BaseConstants.TCONTACT, supplierDo.getContact());
            map.put(BaseConstants.TTELE, supplierDo.getTele());
            map.put(BaseConstants.TEMAIL, supplierDo.getEmail());
            map.put(BaseConstants.TTYPE, supplierDo.getType());
            write(JSONObject.toJSONString(map));
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception ex) {
            logger.error("operaObj is = {}, getDo() is error, msg = {} ", this, ex.getMessage());
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }
}
