package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.StoredetailDao;
import com.ywx.erp.entity.StoredetailDo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class StoredetailDaoImpl extends BaseDaoImpl<StoredetailDo> implements StoredetailDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(StoredetailDo t1, StoredetailDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(StoredetailDo.class);
        if (null != t1) {
            if (null != t1.getStoreuuid()) {
                dc.add(Restrictions.eq("storeuuid", t1.getStoreuuid()));
            }
            if (null != t1.getGoodsuuid()) {
                dc.add(Restrictions.eq("goodsuuid", t1.getGoodsuuid()));
            }
        }
        dc.addOrder(Order.asc("goodsuuid"));
        return dc;
    }
}
