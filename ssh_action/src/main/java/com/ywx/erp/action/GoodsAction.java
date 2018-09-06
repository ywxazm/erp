package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.GoodsDo;
import com.ywx.erp.service.GoodsService;

import java.util.HashMap;

public class GoodsAction extends BaseAction<GoodsDo> {

    private GoodsService goodsService;
    public void setGoodsService(GoodsService goodsService) {
        super.setBaseService(goodsService);
        this.goodsService = goodsService;
    }

    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            GoodsDo goodsDo = goodsService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TUUID, goodsDo.getUuid());
            map.put(BaseConstants.TNAME, goodsDo.getName());
            map.put(BaseConstants.TORIGIN, goodsDo.getOrigin());
            map.put(BaseConstants.TPRODUCER, goodsDo.getProducer());
            map.put(BaseConstants.TUNIT, goodsDo.getUnit());
            map.put(BaseConstants.TINPRICE, goodsDo.getInprice());
            map.put(BaseConstants.TOUTPRICE, goodsDo.getOutprice());
            map.put(BaseConstants.TGOODSTYPEDO, goodsDo.getGoodstypeDo());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }
}
