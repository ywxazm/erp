package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.service.OrderdetailService;

import java.util.HashMap;

public class OrderdetailAction extends BaseAction<OrderdetailDo> {

    private OrderdetailService orderdetailService;
    public void setOrderdetailService(OrderdetailService orderdetailService) {
        super.setBaseService(orderdetailService);
        this.orderdetailService = orderdetailService;
    }

    //定义常量
    private static final String NOLOGIN = "No Login";
    private static final String DOINSTORESUCCESS = "DoInStore Success";
    private static final String DOINSTOREFAIL = "DoInStore Fail";

    //接收数据
    private Long storeuuid;     //仓库ID
    public Long getStoreuuid() {
        return storeuuid;
    }
    public void setStoreuuid(Long storeuuid) {
        this.storeuuid = storeuuid;
    }

    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            OrderdetailDo orderdetailDo = orderdetailService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put(BaseConstants.TORDERUUID, orderdetailDo.getOrdersDo().getUuid());
            map.put(BaseConstants.TSTATE, orderdetailDo.getState());
            map.put(BaseConstants.TSTOREUUID, orderdetailDo.getStoreuuid());
            map.put(BaseConstants.TENDER, orderdetailDo.getEnder());
            map.put(BaseConstants.TENDTIME, orderdetailDo.getEndtime());
            map.put(BaseConstants.TMONEY, orderdetailDo.getMoney());
            map.put(BaseConstants.TNUM, orderdetailDo.getNum());
            map.put(BaseConstants.TPRICE, orderdetailDo.getPrice());
            map.put(BaseConstants.TGOODSNAME, orderdetailDo.getGoodsname());
            map.put(BaseConstants.TGOODSUUID, orderdetailDo.getGoodsuuid());
            map.put(BaseConstants.TUUID, orderdetailDo.getUuid());
            write(JSONObject.toJSONString(map));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }

    /**
     * 入库
     */
    public void doInStore() {
        logger.debug("operaObj is = {}, doInStore doing, storeuuid = {}", this, storeuuid);
        try {
            EmpDo user = getLoginUser();
            if (null == user) {
                write(ajaxReturn(BaseConstants.FALSE, NOLOGIN));
                return;
            }
            orderdetailService.doInStore(id, storeuuid, user.getUuid());
            write(ajaxReturn(BaseConstants.TRUE, DOINSTORESUCCESS));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, doInStore is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(BaseConstants.FALSE, DOINSTOREFAIL));
        }
    }

    /**
     * 出库
     */
    public void doOutStore() {
        logger.debug("operaObj is = {}, doOutStore doing, storeuuid = {}", this, storeuuid);
        try {
            EmpDo user = getLoginUser();
            if (null == user) {
                write(ajaxReturn(BaseConstants.FALSE, NOLOGIN));
                return;
            }
            orderdetailService.doOutStore(id, storeuuid, user.getUuid());
            write(ajaxReturn(BaseConstants.TRUE, DOINSTORESUCCESS));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, doOutStore is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(BaseConstants.FALSE, DOINSTOREFAIL));
        }
    }

}
