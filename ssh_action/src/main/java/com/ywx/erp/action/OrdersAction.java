package com.ywx.erp.action;

import com.alibaba.fastjson.JSON;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.entity.OrdersDo;
import com.ywx.erp.service.OrdersService;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class OrdersAction extends BaseAction<OrdersDo> {

    private OrdersService ordersService;
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    //订单明细的字符串数据
    //OrdersDo{uuid=null, createtime=null, checktime=null, starttime=null, endtime=null, type='null', creater=null, checker=null, starter=null, ender=null, supplieruuid=2, totalmoney=null, state='null', waybillsn=null, orderDetailDos=null}
    //[{"num":"9","money":"21.06","goodsuuid":"1","goodsname":"水蜜桃","price":"2.34"}]
    private String json;
    public String getJson() {
        return json;
    }
    public void setJson(String json) {
        this.json = json;
    }
    @Override
    public void addDo() {
        logger.debug("operaObj is = {}, addDo doing, json = {}", this, json);
        try {
            EmpDo loginUser = getLoginUser();
            if (null == loginUser) {
                write(ajaxReturn(false, "用户未登录"));
                return;
            }
            if (null == json) {
                write(ajaxReturn(false, "订单为空"));
                return;
            }

            OrdersDo ordersDo = getT();
            ordersDo.setCreater(loginUser.getUuid());
            List<OrderdetailDo> orderdetailDos = JSON.parseArray(json, OrderdetailDo.class);
            ordersDo.setOrderDetailDos(orderdetailDos);
            ordersService.addDo(ordersDo);

            write(ajaxReturn(true, "添加成功"));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, addDo is error,  msg = {}", this, ex.getMessage());
            write(ajaxReturn(false, "添加失败"));
        }
    }

    private EmpDo getLoginUser() {
        return (EmpDo)ServletActionContext.getContext().getSession().get("user");
    }
}
