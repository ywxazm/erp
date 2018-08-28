package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.SupplierDao;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.entity.SupplierDo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class SupplierDaoImpl extends BaseDaoImpl<SupplierDo> implements SupplierDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(SupplierDo t1, SupplierDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(SupplierDo.class);
        if (null != t1) {
            if (!StringUtils.isEmpty(t1.getName())) {
                dc.add(Restrictions.like("name", t1.getName(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(t1.getAddress())) {
                dc.add(Restrictions.like("address", t1.getAddress(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(t1.getContact())) {
                dc.add(Restrictions.like("contact", t1.getContact(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(t1.getTele())) {
                dc.add(Restrictions.like("tele", t1.getTele(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(t1.getEmail())) {
                dc.add(Restrictions.like("email", t1.getEmail(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(t1.getType().toString())) {
                dc.add(Restrictions.eq("type", t1.getType()));
            }
        }
        return dc;
    }
}
