package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.DepDao;
import com.ywx.erp.entity.DepDo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class DepDaoImpl extends BaseDaoImpl<DepDo> implements DepDao{

    /**
     * 根据DepDo设置查询条件
     * @param d1,d2,obj
     * @return
     */
    protected DetachedCriteria getDetachedCriteria(DepDo d1, DepDo d2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(DepDo.class);
        if (null != d1) {
            if (!StringUtils.isEmpty(d1.getName())){
                dc.add(Restrictions.like("name", d1.getName(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(d1.getTele())){
                dc.add(Restrictions.like("tele", d1.getTele(), MatchMode.ANYWHERE));
            }
        }
        return dc;
    }
}
