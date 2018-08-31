package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.OrdersDao;
import com.ywx.erp.entity.OrderdetailDo;
import com.ywx.erp.entity.OrdersDo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class OrdersDaoImpl extends BaseDaoImpl<OrdersDo> implements OrdersDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(OrdersDo t1, OrdersDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(OrdersDo.class);
        if (null != t1) {
            if (null != t1.getState()) {
                dc.add(Restrictions.eq("state", t1.getState()));
            }
            if (null != t1.getType()) {
                dc.add(Restrictions.eq("type", t1.getType()));
            }
        }
        return dc;
    }
}
