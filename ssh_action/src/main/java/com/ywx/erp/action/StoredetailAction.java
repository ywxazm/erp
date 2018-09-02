package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ywx.erp.entity.StoredetailDo;
import com.ywx.erp.service.GoodsService;
import com.ywx.erp.service.StoreService;
import com.ywx.erp.service.StoredetailService;

import java.util.HashMap;
import java.util.List;

public class StoredetailAction extends BaseAction<StoredetailDo> {

    private StoredetailService storedetailService;
    private GoodsService goodsService;
    private StoreService storeService;
    public void setStoredetailService(StoredetailService storedetailService) {
        super.setBaseService(storedetailService);
        this.storedetailService = storedetailService;
    }
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public void listByPage() {
        logger.debug("operaObj is = {}, query listByPage param is t = {}, tt = {}, obj = {}, page = {}, rows = {}", this, t, tt, obj, page, rows);
        try {
            List<StoredetailDo> storedetailDoList = storedetailService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = storedetailService.getCount(t, tt, obj);       //统计总条目数
            for (StoredetailDo storedetailDo : storedetailDoList) {
                storedetailDo.setGoodsname(goodsService.getDo(storedetailDo.getGoodsuuid()).getName());
                storedetailDo.setStorename(storeService.getDo(storedetailDo.getStoreuuid()).getName());
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put("rows", storedetailDoList);
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
            StoredetailDo storedetailDo = storedetailService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.goodsname", storedetailDo.getGoodsname());
            map.put("t.goodsuuid", storedetailDo.getGoodsuuid());
            map.put("t.num", storedetailDo.getNum());
            map.put("t.storename", storedetailDo.getStorename());
            map.put("t.storeuuid", storedetailDo.getStoreuuid());
            map.put("t.uuid", storedetailDo.getUuid());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }
}
