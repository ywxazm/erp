package com.ywx.erp.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.redsum.bos.ws.Waybilldetail;
import com.redsum.bos.ws.impl.IWaybillWs;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.entity.OrdersDo;
import com.ywx.erp.service.EmpService;
import com.ywx.erp.service.OrdersService;
import com.ywx.erp.service.SupplierService;

import java.util.HashMap;
import java.util.List;

public class OrdersAction extends BaseAction<OrdersDo> {

    private OrdersService ordersService;
    private EmpService empService;
    private SupplierService supplierService;
    private IWaybillWs waybillWs;

    public IWaybillWs getWaybillWs() {
        return waybillWs;
    }

    public void setWaybillWs(IWaybillWs waybillWs) {
        this.waybillWs = waybillWs;
    }

    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    public void setSupplierService(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void setOrdersService(OrdersService ordersService) {
        super.setBaseService(ordersService);
        this.ordersService = ordersService;
    }
    //定义常量

    private static final String MYORDERS = "myorders";
    private static final String NOLOGIN = "No Login";
    private static final String ORDERISNULL = "Orders Is Null";
    private static final String CHECKSUCCESS = "Check Success";
    private static final String CHECKFAIL = "Check Fail";
    private static final String DOSTARTSUCCESS = "DoStart Success";
    private static final String DOSTARTFAIL = "DoStart Fail";
    //接收数据

    private String oper;
    private String json;
    private Integer waybillSn; //运单号
    public Integer getWaybillSn() {
        return waybillSn;
    }
    public void setWaybillSn(Integer waybillSn) {
        this.waybillSn = waybillSn;
    }
    public String getJson() {
        return json;
    }
    public void setJson(String json) {
        this.json = json;
    }
    public String getOper() {
        return oper;
    }
    public void setOper(String oper) {
        this.oper = oper;
    }
    /**
     * 订单查询，如果有用户ID，则只查询当前用户ID下的订单
     */
    @Override
    public void listByPage() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, query listByPage param is t = {}, tt = {}, obj = {}, page = {}, rows = {}, " +
                "oper = {}", this, t, tt, obj, page, rows, oper);
        try {
            if (MYORDERS.equals(oper)) {
                t.setCreater(getLoginUser().getUuid());
            }

            List<OrdersDo> list = ordersService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = ordersService.getCount(t, tt, obj);       //统计总条目数

            //查询每一张订单下的明细中的操作人
            for (OrdersDo ordersDo : list) {
                ordersDo.setSupplierName(getSupplierName(ordersDo.getSupplieruuid()));
                ordersDo.setEnderName(getEmpName(ordersDo.getEnder()));
                ordersDo.setCheckerName(getEmpName(ordersDo.getChecker()));
                ordersDo.setCreaterName(getEmpName(ordersDo.getCreater()));
                ordersDo.setStarterName(getEmpName(ordersDo.getStarter()));
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put(ROWS, list);
            map.put(TOTAL, count);
            String json = JSONObject.toJSONString(map);
            write(json);
            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("operaObj is = {}, listByPage() is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    private String getSupplierName(Integer id) {
        if (null == id)
            return null;
        return supplierService.getDo(id).getName();
    }

    private String getEmpName(Integer id) {
        if (null == id)
            return null;
        return empService.getDo(id).getName();
    }

    /**
     * 添加订单, 级联添加订单明细
     */
    @Override
    public void addDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, addDo doing, json = {}", this, json);
        try {
            EmpDo loginUser = getLoginUser();
            if (null == loginUser) {
                write(ajaxReturn(BaseConstants.TRUE, NOLOGIN));
                logger.debug("operaObj is = {}, addDo() cast time = {}", this, System.currentTimeMillis() - startTime);
                return;
            }
            if (null == json) {
                write(ajaxReturn(BaseConstants.FALSE, ORDERISNULL));
                logger.debug("operaObj is = {}, addDo() cast time = {}", this, System.currentTimeMillis() - startTime);
                return;
            }

            OrdersDo ordersDo = getT();
            ordersDo.setCreater(loginUser.getUuid());
            List<OrderdetailDo> orderdetailDos = JSON.parseArray(json, OrderdetailDo.class);
            ordersDo.setOrderDetailDos(orderdetailDos);
            ordersService.addDo(ordersDo);

            write(ajaxReturn(BaseConstants.TRUE, ADDSUCCESS));
            logger.debug("operaObj is = {}, addDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception ex) {
            write(ajaxReturn(BaseConstants.FALSE, ADDFAIL));
            logger.error("operaObj is = {}, addDo() is error,  msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, addDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 审核
     */
    public void doCheck() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, doCheck param is id = {}", this, id);
        try {
            ordersService.doCheck(id, getLoginUser().getUuid());
            write(ajaxReturn(BaseConstants.TRUE, CHECKSUCCESS));
            logger.debug("operaObj is = {}, doCheck() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            write(ajaxReturn(BaseConstants.FALSE, CHECKFAIL));
            logger.error("operaObj is = {}, doCheck is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, doCheck() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 确认
     */
    public void doStart() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, doStart param is id = {}", this, id);
        try {
            ordersService.doStart(id, getLoginUser().getUuid());
            write(ajaxReturn(BaseConstants.TRUE, DOSTARTSUCCESS));
            logger.debug("operaObj is = {}, doStart() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            write(ajaxReturn(BaseConstants.FALSE, DOSTARTFAIL));
            logger.error("operaObj is = {}, doStart() is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, doStart() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 查询运单详情
     */
    public void waybilldetailList() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, waybilldetailList param is id = {}", this, id);
        try {
            List<Waybilldetail> waybilldetails = waybillWs.waybilldetailList(waybillSn);
            write(JSONObject.toJSONString(waybilldetails));
            logger.debug("operaObj is = {}, waybilldetailList() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            write(ajaxReturn(BaseConstants.FALSE, DOSTARTFAIL));
            logger.error("operaObj is = {}, waybilldetailList() is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, waybilldetailList() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }
}
