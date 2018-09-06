package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.StoreoperDo;
import com.ywx.erp.service.EmpService;
import com.ywx.erp.service.GoodsService;
import com.ywx.erp.service.StoreService;
import com.ywx.erp.service.StoreoperService;

import java.util.HashMap;
import java.util.List;

public class StoreoperAction extends BaseAction<StoreoperDo> {

    private StoreoperService storeoperService;
    private EmpService empService;
    private StoreService storeService;
    private GoodsService goodsService;
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
    public void setStoreoperService(StoreoperService storeoperService) {
        super.setBaseService(storeoperService);
        this.storeoperService = storeoperService;
    }

    @Override
    public void getDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            StoreoperDo storeoperDo = storeoperService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TEMPNAME, storeoperDo.getEmpname());
            map.put(BaseConstants.TEMPUUID, storeoperDo.getEmpuuid());
            map.put(BaseConstants.TGOODSNAME, storeoperDo.getGoodsname());
            map.put(BaseConstants.TGOODSUUID, storeoperDo.getGoodsuuid());
            map.put(BaseConstants.TNUM, storeoperDo.getNum());
            map.put(BaseConstants.TOPERTIIME, storeoperDo.getOpertime());
            map.put(BaseConstants.TSTORENAME, storeoperDo.getStorename());
            map.put(BaseConstants.TSTOREUUID, storeoperDo.getStoreuuid());
            map.put(BaseConstants.TTYPE, storeoperDo.getType());
            map.put(BaseConstants.TUUID, storeoperDo.getUuid());
            write(JSONObject.toJSONString(map));
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    @Override
    public void listByPage() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, query listByPage param is t = {}, tt = {}, obj = {}, page = {}, " +
                "rows = {}", this, t, tt, obj, page, rows);
        try {
            List<StoreoperDo> storeoperDoList = storeoperService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = storeoperService.getCount(t, tt, obj);       //统计总条目数

            for (StoreoperDo storeoperDo : storeoperDoList) {
                storeoperDo.setEmpname(empService.getDo(storeoperDo.getEmpuuid()).getName());
                storeoperDo.setGoodsname(goodsService.getDo(storeoperDo.getGoodsuuid()).getName());
                storeoperDo.setStorename(storeService.getDo(storeoperDo.getStoreuuid()).getName());
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put(ROWS, storeoperDoList);
            map.put(TOTAL, count);
            String str = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            write(str);
            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("operaObj is = {}, listByPage() is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }
}
