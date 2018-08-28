package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.GoodstypeDao;
import com.ywx.erp.entity.GoodsTypeDo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class GoodstypeDaoImpl extends BaseDaoImpl<GoodsTypeDo> implements GoodstypeDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(GoodsTypeDo t1, GoodsTypeDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(GoodsTypeDo.class);
        if (null != t1) {
            if (!StringUtils.isEmpty(t1.getName())) {
                dc.add(Restrictions.like("name", t1.getName(), MatchMode.ANYWHERE));
            }
        }
        return dc;
    }
}
