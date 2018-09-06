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
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            StoreDo storeDo = storeService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TUUID, storeDo.getUuid());
            map.put(BaseConstants.TNAME , storeDo.getName());
            map.put(BaseConstants.TEMPUUID, storeDo.getEmpuuid());
            write(JSONObject.toJSONString(map));
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo() is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    @Override
    public void listByPage() {
        Long startTime = System.currentTimeMillis();
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
            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("operaObj is = {}, listByPage() is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 查看当前用户名下仓库列表
     */
    public void myListStore() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, myListStore doing, Do = {}", this, t);
        try {
            if (null == getT()) {
                setT(new StoreDo());
            }

            EmpDo user = getLoginUser();

            getT().setEmpuuid(user.getUuid());
            super.list();
            logger.debug("operaObj is = {}, myListStore() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.error("operaObj is = {}, myListStore() is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, myListStore() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 仓库库存报警
     */
    public void inventoryWarn() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, inventoryWarn() doing, currentTime = {}", this, System.currentTimeMillis());
        try {
            storeService.sendWarnStoreMail();
            logger.debug("operaObj is = {}, inventoryWarn() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.error("operaObj is = {}, inventoryWarn() is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, inventoryWarn() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

}
