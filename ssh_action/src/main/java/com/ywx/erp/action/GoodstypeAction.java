package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.GoodstypeDo;
import com.ywx.erp.service.GoodstypeService;

import java.util.HashMap;

public class GoodstypeAction extends BaseAction<GoodstypeDo> {

    private GoodstypeService goodstypeService;
    public void setGoodstypeService(GoodstypeService goodstypeService) {
        super.setBaseService(goodstypeService);
        this.goodstypeService = goodstypeService;
    }

    @Override
    public void getDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            GoodstypeDo goodstypeDo = goodstypeService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TUUID, goodstypeDo.getUuid());
            map.put(BaseConstants.TNAME, goodstypeDo.getName());
            write(JSONObject.toJSONString(map));
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

}
