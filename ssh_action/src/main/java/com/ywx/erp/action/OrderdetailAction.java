package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.service.OrderdetailService;
import org.apache.struts2.ServletActionContext;

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

    private Long storeuuid;
    public Long getStoreuuid() {
        return storeuuid;
    }
    public void setStoreuuid(Long storeuuid) {
        this.storeuuid = storeuuid;
    }
    public void doInStore() {
        logger.debug("operaObj is = {}, doInStore doing, storeuuid = {}", this, storeuuid);
        try {
            EmpDo user = getLoginUser();
            if (null == user) {
                write(ajaxReturn(false, "用户未登录"));
                return;
            }
            orderdetailService.doInStore(id, storeuuid, user.getUuid());
            write(ajaxReturn(true, "入库成功"));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, doInStore is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(false, "入库失败"));
        }
    }

    private EmpDo getLoginUser() {
        return (EmpDo) ServletActionContext.getContext().getSession().get("user");
    }

}
