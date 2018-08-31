package com.ywx.erp.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.entity.OrdersDo;
import com.ywx.erp.entity.SupplierDo;
import com.ywx.erp.service.EmpService;
import com.ywx.erp.service.OrdersService;
import com.ywx.erp.service.SupplierService;
import org.apache.struts2.ServletActionContext;

import java.util.HashMap;
import java.util.List;

public class OrdersAction extends BaseAction<OrdersDo> {

    private OrdersService ordersService;
    private EmpService empService;
    private SupplierService supplierService;
    public void setOrdersService(OrdersService ordersService) {
        super.setBaseService(ordersService);
        this.ordersService = ordersService;
    }
    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }
    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void listByPage() {
        try {
            logger.debug("operaObj is = {}, query listByPage param is t = {}, tt = {}, obj = {}, page = {}, rows = {}", this, t, tt, obj, page, rows);
            List<OrdersDo> list = ordersService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = ordersService.getCount(t, tt, obj);       //统计总条目数

            for (OrdersDo ordersDo : list) {
                ordersDo.setSupplierName(getSupplierName(ordersDo.getSupplieruuid()));
                ordersDo.setEnderName(getEmpName(ordersDo.getEnder()));
                ordersDo.setCheckerName(getEmpName(ordersDo.getChecker()));
                ordersDo.setCreaterName(getEmpName(ordersDo.getCreater()));
                ordersDo.setStarterName(getEmpName(ordersDo.getStarter()));
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put("rows", list);
            map.put("total", count);
            String str = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            write(str);
        } catch (Exception e) {
            logger.error("operaObj is = {}, query listByPage is error, info = {}", this, e.getMessage());
            e.printStackTrace();
        }
    }

    private String getSupplierName(Long id) {
        if (null == id)
            return null;
        return supplierService.getDo(id).getName();
    }

    private String getEmpName(Long id) {
        if (null == id)
            return null;
        return empService.getDo(id).getName();
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

    /**
     * 审核
     */
    public void doCheck() {
        try {
            logger.debug("operaObj is = {}, doCheck param is id = {}", this, id);
            ordersService.doCheck(id, getLoginUser().getUuid());
            write(ajaxReturn(true, "审核成功"));
        } catch (Exception e) {
            logger.error("operaObj is = {}, query list is error, info = {}", this, e.getMessage());
            write(ajaxReturn(false, "审核失败"));
        }
    }

    /**
     * 确认
     */
    public void doStart() {
        try {
            logger.debug("operaObj is = {}, doStart param is id = {}", this, id);
            ordersService.doStart(id, getLoginUser().getUuid());
            write(ajaxReturn(true, "确认成功"));
        } catch (Exception e) {
            logger.error("operaObj is = {}, doStart is error, info = {}", this, e.getMessage());
            write(ajaxReturn(false, "确认失败"));
        }
    }

}
