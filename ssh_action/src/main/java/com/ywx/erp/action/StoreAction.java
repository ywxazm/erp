package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.StoreDo;
import com.ywx.erp.service.EmpService;
import com.ywx.erp.service.StoreService;

import java.util.HashMap;
import java.util.List;

public class StoreAction extends BaseAction<StoreDo> {

    private StoreService storeService;
    private EmpService empService;
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }
    public void setStoreService(StoreService storeService) {
        super.setBaseService(storeService);
        this.storeService = storeService;
    }

    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            StoreDo storeDo = storeService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TUUID, storeDo.getUuid());
            map.put(BaseConstants.TNAME , storeDo.getName());
            map.put(BaseConstants.TEMPUUID, storeDo.getEmpuuid());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }

    @Override
    public void listByPage() {
        logger.debug("operaObj is = {}, query listByPage param is t = {}, tt = {}, obj = {}, page = {}, " +
                "rows = {}", this, t, tt, obj, page, rows);
        try {
            List<StoreDo> storeDoList = storeService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = storeService.getCount(t, tt, obj);       //统计总条目数

            for (StoreDo storeDo : storeDoList) {
                storeDo.setEmpname(empService.getDo(storeDo.getEmpuuid()).getName());
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put(ROWS, storeDoList);
            map.put(TOTAL, count);
            String str = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            write(str);
        } catch (Exception e) {
            logger.error("operaObj is = {}, query listByPage is error, info = {}", this, e.getMessage());
        }
    }

    /**
     * 查看当前用户名下仓库列表
     */
    public void myListStore() {
        logger.debug("operaObj is = {}, myListStore doing, Do = {}", this, t);
        try {
            if (null == getT()) {
                setT(new StoreDo());
            }

            EmpDo user = getLoginUser();

            getT().setEmpuuid(user.getUuid());
            super.list();
        }catch (Exception ex) {
            logger.error("operaObj is = {}, myListStore is error, msg = {}", this, ex.getMessage());
        }
    }

    /**
     * 仓库库存报警
     */
    public void inventoryWarn() {
        logger.debug("operaObj is = {}, inventoryWarn() doing, currentTime = {}", this, System.currentTimeMillis());
        try {
            storeService.sendWarnStoreMail();
        }catch (Exception ex) {
            logger.error("operaObj is = {}, inventoryWarn is error, msg = {}", this, ex.getMessage());
            ex.printStackTrace();
        }
    }

}
