package com.ywx.erp.service.impl;

import com.ywx.erp.dao.OrdersDao;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.entity.OrdersDo;
import com.ywx.erp.service.OrdersService;

import java.util.Date;

public class OrdersServiceImpl extends BaseServiceImpl<OrdersDo> implements OrdersService {

    private OrdersDao ordersDao;
    public void setOrdersDao(OrdersDao ordersDao) {
        super.setBaseDao(ordersDao);
        this.ordersDao = ordersDao;
    }

    @Override
    public void addDo(OrdersDo ordersDo) {
        ordersDo.setState(OrdersDo.STATE_START);
        ordersDo.setType(OrdersDo.TYPE_IN);
        ordersDo.setCreatetime(new Date());

        double total = 0.00;
        for (OrderdetailDo odd : ordersDo.getOrderDetailDos()) {
            total += odd.getMoney();
            odd.setState(OrderdetailDo.STATE_NOT_IN);
            odd.setOrdersDo(ordersDo);
        }

        ordersDo.setTotalmoney(total);
        ordersDao.addDo(ordersDo);          //TODO:此处可以实现级联保存
    }

    @Override
    public void doCheck(long id, long empDoId) {
        OrdersDo ordersDo = ordersDao.getDo(id);
        ordersDo.setState(OrdersDo.STATE_CHECK);
        ordersDo.setChecker(empDoId);
        ordersDo.setChecktime(new Date());
    }

    @Override
    public void doStart(long id, long empDoId) {
        OrdersDo ordersDo = ordersDao.getDo(id);
        ordersDo.setState(OrdersDo.STATE_START);
        ordersDo.setStarter(empDoId);
        ordersDo.setStarttime(new Date());
    }
}
