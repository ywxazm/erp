package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.GoodstypeDo;
import com.ywx.erp.service.GoodstypeService;

import java.util.HashMap;

public class GoodstypeAction extends BaseAction<GoodstypeDo> {

    private GoodstypeService goodstypeService;

    public void setGoodstypeService(GoodstypeService goodstypeService) {
        super.setBaseService(goodstypeService);
        this.goodstypeService = goodstypeService;
    }

    /**
     * 回显Bean的属性值
     */
    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            GoodstypeDo goodstypeDo = goodstypeService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.uuid", goodstypeDo.getUuid());
            map.put("t.name", goodstypeDo.getName());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }

}
