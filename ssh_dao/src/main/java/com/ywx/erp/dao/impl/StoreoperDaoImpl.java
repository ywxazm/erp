package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.StoreoperDao;
import com.ywx.erp.entity.StoreoperDo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class StoreoperDaoImpl extends BaseDaoImpl<StoreoperDo> implements StoreoperDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(StoreoperDo t1, StoreoperDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(StoreoperDo.class);
        if (null != t1) {
            if (null != t1.getEmpuuid()) {
                dc.add(Restrictions.eq("empuuid", t1.getEmpuuid()));
            }
            if (null != t1.getGoodsuuid()) {
                dc.add(Restrictions.eq("goodsuuid", t1.getGoodsuuid()));
            }
            if (null != t1.getType()) {
                dc.add(Restrictions.eq("type", t1.getType()));
            }
        }
        return dc;
    }
}
