package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
    public void setStoreoperService(StoreoperService storeoperService) {
        super.setBaseService(storeoperService);
        this.storeoperService = storeoperService;
    }
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Override
    public void listByPage() {
        logger.debug("operaObj is = {}, query listByPage param is t = {}, tt = {}, obj = {}, page = {}, rows = {}", this, t, tt, obj, page, rows);
        try {
            List<StoreoperDo> storeoperDoList = storeoperService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = storeoperService.getCount(t, tt, obj);       //统计总条目数

            for (StoreoperDo storeoperDo : storeoperDoList) {
                storeoperDo.setEmpname(empService.getDo(storeoperDo.getEmpuuid()).getName());
                storeoperDo.setGoodsname(goodsService.getDo(storeoperDo.getGoodsuuid()).getName());
                storeoperDo.setStorename(storeService.getDo(storeoperDo.getStoreuuid()).getName());
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put("rows", storeoperDoList);
            map.put("total", count);
            String str = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            write(str);
        } catch (Exception e) {
            logger.error("operaObj is = {}, query listByPage is error, info = {}", this, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            StoreoperDo storeoperDo = storeoperService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.empname", storeoperDo.getEmpname());
            map.put("t.empuuid", storeoperDo.getEmpuuid());
            map.put("t.goodsname", storeoperDo.getGoodsname());
            map.put("t.goodsuuid", storeoperDo.getGoodsuuid());
            map.put("t.num", storeoperDo.getNum());
            map.put("t.opertime", storeoperDo.getOpertime());
            map.put("t.storename", storeoperDo.getStorename());
            map.put("t.storeuuid", storeoperDo.getStoreuuid());
            map.put("t.type", storeoperDo.getType());
            map.put("t.uuid", storeoperDo.getUuid());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }
}
