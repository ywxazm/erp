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
import java.util.Date;
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

    /**
     *
     * @param uuid  订单明细ID
     * @param storeId   仓库ID
     * @param empId   用户ID
     */
    @Override
    public void doOutStore(long uuid, long storeId, long empId) {
        //判断此商品是否已经出库
        OrderdetailDo orderdetailDo = getDo(uuid);
        if ("1".equals(orderdetailDo.getState())) {
            throw new ErpException("订单已出库存");
        }

        //判断用户名下的仓库是否有足够的库存
        StoredetailDo storedetailDo = new StoredetailDo();
        storedetailDo.setStoreuuid(storeId);
        storedetailDo.setGoodsuuid(orderdetailDo.getGoodsuuid());
        List<StoredetailDo> list = storedetailDao.list(storedetailDo, null, null);

        Long num = -1L;
        if (null != list && list.size() > 0) {
            StoredetailDo sdd = list.get(0);
            num = sdd.getNum() - orderdetailDo.getNum();
            if (num > 0) {
                sdd.setNum(num);      //更新仓库明细表
            }else {
                throw new ErpException("库存不足");
            }
        }else {
            throw new ErpException("无此商品库存");
        }

        //更新订单明细
        orderdetailDo.setEnder(empId);
        orderdetailDo.setEndtime(new Date());
        orderdetailDo.setState(OrderdetailDo.STATE_OUT);
        orderdetailDo.setStoreuuid(storeId);

        //添加库存变更操作记录表
        StoreoperDo storeoperDo = new StoreoperDo();
        storeoperDo.setEmpuuid(empId);
        storeoperDo.setGoodsuuid(orderdetailDo.getGoodsuuid());
        storeoperDo.setNum(orderdetailDo.getNum());
        storeoperDo.setOpertime(new Date());
        storeoperDo.setStoreuuid(storeId);
        storeoperDo.setType("2");
        storeoperDao.addDo(storeoperDo);

        //检查订单下所有明细是否已出库
        OrderdetailDo orderdetailDo1 = new OrderdetailDo();
        OrdersDo ordersDo = orderdetailDo.getOrdersDo();
        orderdetailDo1.setOrdersDo(orderdetailDo.getOrdersDo());
        orderdetailDo1.setState("0");
        Long count = orderdetailDao.getCount(orderdetailDo1, null, null);
        if (0 == count) {
            ordersDo.setEnder(empId);
            ordersDo.setEndtime(new Date());
            ordersDo.setState("1");
        }
    }
}