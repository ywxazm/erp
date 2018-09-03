package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.StoreDao;
import com.ywx.erp.entity.StoreAlertDo;
import com.ywx.erp.entity.StoreDo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StoreDaoImpl extends BaseDaoImpl<StoreDo> implements StoreDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(StoreDo t1, StoreDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(StoreDo.class);
        if (t1 != null) {
            if (null != t1.getName()) {
                dc.add(Restrictions.like("name", t1.getName(), MatchMode.ANYWHERE));
            }
        }
        return dc;
    }

    @Override
    public List<StoreAlertDo> queryInventory() {
        return (List<StoreAlertDo>)this.getHibernateTemplate().find("From StoreAlertDo", null);
    }
}
