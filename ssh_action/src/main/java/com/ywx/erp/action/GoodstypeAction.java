package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.GoodsTypeDo;
import com.ywx.erp.service.GoodstypeService;

import java.util.HashMap;

public class GoodstypeAction extends BaseAction<GoodsTypeDo> {

    private GoodstypeService goodstypeService;

    public void setGoodstypeService(GoodstypeService goodstypeService) {
        super.setBaseService(goodstypeService);
        this.goodstypeService = goodstypeService;
    }

    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            GoodsTypeDo goodsTypeDo = goodstypeService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.uuid", goodsTypeDo.getUuid());
            map.put("t.name", goodsTypeDo.getName());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }

}
