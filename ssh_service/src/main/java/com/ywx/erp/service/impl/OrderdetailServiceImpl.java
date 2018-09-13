package com.ywx.erp.service.impl;

import com.redsum.bos.ws.impl.IWaybillWs;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.dao.OrderdetailDao;
import com.ywx.erp.dao.StoredetailDao;
import com.ywx.erp.dao.StoreoperDao;
import com.ywx.erp.dao.SupplierDao;
import com.ywx.erp.entity.*;
import com.ywx.erp.exception.ErpException;
import com.ywx.erp.service.OrderdetailService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderdetailServiceImpl extends BaseServiceImpl<OrderdetailDo> implements OrderdetailService {

    private OrderdetailDao orderdetailDao;
    private StoreoperDao storeoperDao;
    private StoredetailDao storedetailDao;
    private IWaybillWs waybillWs;
    private SupplierDao supplierDao;
    public void setWaybillWs(IWaybillWs waybillWs) {
        this.waybillWs = waybillWs;
    }
    public void setSupplierDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public void setStoreoperDao(StoreoperDao storeoperDao) {
        this.storeoperDao = storeoperDao;
    }
    public void setStoredetailDao(StoredetailDao storedetailDao) {
        this.storedetailDao = storedetailDao;
    }
    public void setOrderdetailDao(OrderdetailDao orderdetailDao) {
        super.setBaseDao(orderdetailDao);
        this.orderdetailDao = orderdetailDao;
    }

    //常量定义
    private static final String GOODSDONEINSTORE = "Goods In Store Done";                   //商品已入库
    private static final String INVENTORYDEFICIENCY = "Inventory Deficiency";               //库存不足
    private static final String NOINVENTORYOFGOODS = "No Inventory Of Goods";               //无此商品库存
    private static final String ORDERSINVENTORYOUTSTORE = "Orders Inventory Out Store";     //订单已出库

    @Override
    public void doInStore(int uuid, int storeId, int empId) {
        //更新商品明细
        OrderdetailDo orderdetailDo = orderdetailDao.getDo(uuid);
        if (BaseConstants.STATE_IN_OUT.equals(orderdetailDo.getState())) {
            throw new ErpException(GOODSDONEINSTORE);
        }
        orderdetailDo.setState(BaseConstants.STATE_IN_OUT);
        orderdetailDo.setEnder(empId);
        orderdetailDo.setEndtime(Calendar.getInstance().getTime());
        orderdetailDo.setStoreuuid(storeId);

        //更新商品库存
        StoredetailDo storedetailDo = new StoredetailDo();
        storedetailDo.setGoodsuuid(orderdetailDo.getGoodsuuid());
        storedetailDo.setStoreuuid(orderdetailDo.getStoreuuid());
        List<StoredetailDo> list = storedetailDao.list(storedetailDo, null, null);
        if (null == list || list.size() == 0) {
            storedetailDo.setNum(orderdetailDo.getNum());
        }else {
            storedetailDo.setNum(orderdetailDo.getNum() + list.get(0).getNum());
        }
        storedetailDao.addDo(storedetailDo);

        //增加库存更新记录
        StoreoperDo storeoperDo = new StoreoperDo();
        storeoperDo.setEmpuuid(empId);
        storeoperDo.setGoodsuuid(orderdetailDo.getGoodsuuid());
        storeoperDo.setNum(orderdetailDo.getNum());
        storeoperDo.setOpertime(Calendar.getInstance().getTime());
        storeoperDo.setStoreuuid(orderdetailDo.getStoreuuid());
        storeoperDo.setType(BaseConstants.STATE_IN_OUT);
        storeoperDao.addDo(storeoperDo);

        //更新订单状态的判断
        OrdersDo ordersDo = orderdetailDo.getOrdersDo();
        OrderdetailDo paramCount = new OrderdetailDo();
        paramCount.setState(BaseConstants.STATE_NOT_IN_OUT);
        paramCount.setOrdersDo(ordersDo);
        Long count = orderdetailDao.getCount(paramCount, null, null);
        if (null != count && count == BaseConstants.ZEROSTR) {
            ordersDo.setState(BaseConstants.STATE_END);
            ordersDo.setEnder(empId);
            ordersDo.setEndtime(Calendar.getInstance().getTime());
        }
    }

    /**
     *
     * @param uuid  订单明细ID
     * @param storeId   仓库ID
     * @param empId   用户ID
     */
    @Override
    public void doOutStore(int uuid, int storeId, int empId) {
        //判断此商品是否已经出库
        OrderdetailDo orderdetailDo = getDo(uuid);
        if ("1".equals(orderdetailDo.getState())) {
            throw new ErpException(ORDERSINVENTORYOUTSTORE);
        }

        //判断用户名下的仓库是否有足够的库存
        StoredetailDo storedetailDo = new StoredetailDo();
        storedetailDo.setStoreuuid(storeId);
        storedetailDo.setGoodsuuid(orderdetailDo.getGoodsuuid());
        List<StoredetailDo> list = storedetailDao.list(storedetailDo, null, null);

        if (null != list && list.size() > 0) {
            StoredetailDo sdd = list.get(0);
            int num = sdd.getNum() - orderdetailDo.getNum();
            if (num > 0) {
                sdd.setNum(num);      //更新仓库明细表
            }else {
                throw new ErpException(INVENTORYDEFICIENCY);
            }
        }else {
            throw new ErpException(NOINVENTORYOFGOODS);
        }

        //更新订单明细
        orderdetailDo.setEnder(empId);
        orderdetailDo.setEndtime(new Date());
        orderdetailDo.setState(BaseConstants.STATE_IN_OUT);
        orderdetailDo.setStoreuuid(storeId);

        //添加库存变更操作记录表
        StoreoperDo storeoperDo = new StoreoperDo();
        storeoperDo.setEmpuuid(empId);
        storeoperDo.setGoodsuuid(orderdetailDo.getGoodsuuid());
        storeoperDo.setNum(orderdetailDo.getNum());
        storeoperDo.setOpertime(new Date());
        storeoperDo.setStoreuuid(storeId);
        storeoperDo.setType(BaseConstants.STATE_IN_OUT);
        storeoperDao.addDo(storeoperDo);

        //检查订单下所有明细是否已出库
        OrderdetailDo orderdetailDo1 = new OrderdetailDo();
        OrdersDo ordersDo = orderdetailDo.getOrdersDo();
        orderdetailDo1.setOrdersDo(orderdetailDo.getOrdersDo());
        orderdetailDo1.setState(BaseConstants.STATE_NOT_IN_OUT);
        Long count = orderdetailDao.getCount(orderdetailDo1, null, null);
        if (0 == count) {
            ordersDo.setEnder(empId);
            ordersDo.setEndtime(new Date());
            ordersDo.setState("1");
            SupplierDo supplierDo = supplierDao.getDo(ordersDo.getSupplieruuid());
            int waybillSn = waybillWs.addWaybill(1, supplierDo.getAddress(), supplierDo.getName(), supplierDo.getAddress(), "--");
            ordersDo.setWaybillsn(waybillSn);
        }
    }
}
