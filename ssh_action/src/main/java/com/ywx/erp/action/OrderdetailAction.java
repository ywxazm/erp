package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.service.OrderdetailService;

import java.util.HashMap;

public class OrderdetailAction extends BaseAction<OrderdetailDo> {

    private OrderdetailService orderdetailService;
    public void setOrderdetailService(OrderdetailService orderdetailService) {
        super.setBaseService(orderdetailService);
        this.orderdetailService = orderdetailService;
    }

    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            OrderdetailDo orderdetailDo = orderdetailService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.ordersuuid", orderdetailDo.getOrdersDo().getUuid());
            map.put("t.state", orderdetailDo.getState());
            map.put("t.storeuuid", orderdetailDo.getStoreuuid());
            map.put("t.ender", orderdetailDo.getEnder());
            map.put("t.endtime", orderdetailDo.getEndtime());
            map.put("t.money", orderdetailDo.getMoney());
            map.put("t.num", orderdetailDo.getNum());
            map.put("t.price", orderdetailDo.getPrice());
            map.put("t.goodsname", orderdetailDo.getGoodsname());
            map.put("t.goodsuuid", orderdetailDo.getGoodsuuid());
            map.put("t.uuid", orderdetailDo.getUuid());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }

}
