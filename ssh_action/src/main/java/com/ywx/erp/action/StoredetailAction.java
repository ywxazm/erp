package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ywx.erp.common.BaseConstants;
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
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }
    public void setStoredetailService(StoredetailService storedetailService) {
        super.setBaseService(storedetailService);
        this.storedetailService = storedetailService;
    }

    @Override
    public void getDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            StoredetailDo storedetailDo = storedetailService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TGOODSNAME, storedetailDo.getGoodsname());
            map.put(BaseConstants.TGOODSUUID, storedetailDo.getGoodsuuid());
            map.put(BaseConstants.TNUM, storedetailDo.getNum());
            map.put(BaseConstants.TSTORENAME, storedetailDo.getStorename());
            map.put(BaseConstants.TSTOREUUID, storedetailDo.getStoreuuid());
            map.put(BaseConstants.TUUID, storedetailDo.getUuid());
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
            List<StoredetailDo> storedetailDoList = storedetailService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = storedetailService.getCount(t, tt, obj);       //统计总条目数
            for (StoredetailDo storedetailDo : storedetailDoList) {
                storedetailDo.setGoodsname(goodsService.getDo(storedetailDo.getGoodsuuid()).getName());
                storedetailDo.setStorename(storeService.getDo(storedetailDo.getStoreuuid()).getName());
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put(ROWS, storedetailDoList);
            map.put(TOTAL, count);
            String json = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            write(json);
            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("operaObj is = {}, listByPage() is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }
}
