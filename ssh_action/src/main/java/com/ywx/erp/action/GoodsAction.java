package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.GoodsDo;
import com.ywx.erp.service.GoodsService;

import java.util.HashMap;

public class GoodsAction extends BaseAction<GoodsDo> {

    private GoodsService goodsService;
    public void setGoodsService(GoodsService goodsService) {
        super.setBaseService(goodsService);
        this.goodsService = goodsService;
    }

    /**
     * 根据ID去获取部门信息
     */
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            GoodsDo goodsDo = goodsService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.uuid", goodsDo.getUuid());
            map.put("t.name", goodsDo.getName());
            map.put("t.origin", goodsDo.getOrigin());
            map.put("t.producer", goodsDo.getProducer());
            map.put("t.unit", goodsDo.getUnit());
            map.put("t.inprice", goodsDo.getInprice());
            map.put("t.outprice", goodsDo.getOutprice());
            map.put("t.goodsTypeDo", goodsDo.getGoodstypeDo());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }


}
