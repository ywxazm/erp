package com.ywx.erp.service.impl;

import com.ywx.erp.dao.OrderdetailDao;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.service.OrderdetailService;

public class OrderdetailServiceImpl extends BaseServiceImpl<OrderdetailDo> implements OrderdetailService {

    private OrderdetailDao orderdetailDao;
    public void setOrderdetailDao(OrderdetailDao orderdetailDao) {
        super.setBaseDao(orderdetailDao);
        this.orderdetailDao = orderdetailDao;
    }

}
