package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.RoleDao;
import com.ywx.erp.entity.RoleDo;
import com.ywx.erp.entity.SupplierDo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class RoleDaoImpl extends BaseDaoImpl<RoleDo> implements RoleDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(RoleDo t1, RoleDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(RoleDo.class);
        if (null != t1) {
            if (null != t1.getUuid()) {
                dc.add(Restrictions.eq("uuid", t1.getUuid()));
            }
        }
        return dc;

    }
}
