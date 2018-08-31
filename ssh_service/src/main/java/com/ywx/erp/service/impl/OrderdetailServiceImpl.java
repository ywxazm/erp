package com.ywx.erp.service.impl;

import com.ywx.erp.dao.OrderdetailDao;
import com.ywx.erp.dao.StoredetailDao;
import com.ywx.erp.dao.StoreoperDao;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.entity.OrdersDo;
import com.ywx.erp.entity.StoredetailDo;
import com.ywx.erp.entity.StoreoperDo;
import com.ywx.erp.exception.ErpException;
import com.ywx.erp.service.OrderdetailService;

import java.util.Calendar;
import java.util.List;

public class OrderdetailServiceImpl extends BaseServiceImpl<OrderdetailDo> implements OrderdetailService {

    private OrderdetailDao orderdetailDao;
    private StoreoperDao storeoperDao;
    private StoredetailDao storedetailDao;
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

    @Override
    public void doInStore(long uuid, long storeId, long empId) {
        //更新商品明细
        OrderdetailDo orderdetailDo = orderdetailDao.getDo(uuid);
        if (OrderdetailDo.STATE_IN.equals(orderdetailDo.getState())) {
            throw new ErpException("商品已经入库");
        }
        orderdetailDo.setState(OrderdetailDo.STATE_IN);
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
        storeoperDo.setType(StoreoperDo.STATE_IN);
        storeoperDao.addDo(storeoperDo);

        //更新订单状态的判断
        OrdersDo ordersDo = orderdetailDo.getOrdersDo();
        OrderdetailDo paramCount = new OrderdetailDo();
        paramCount.setState(OrderdetailDo.STATE_NOT_IN);
        paramCount.setOrdersDo(ordersDo);
        Long count = orderdetailDao.getCount(paramCount, null, null);
        if (null != count && count == 0) {
            ordersDo.setState(OrdersDo.STATE_END);
            ordersDo.setEnder(empId);
            ordersDo.setEndtime(Calendar.getInstance().getTime());
        }
    }
}
