package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.MenuDao;
import com.ywx.erp.entity.MenuDo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class MenuDaoImpl extends BaseDaoImpl<MenuDo> implements MenuDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(MenuDo t1, MenuDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(MenuDo.class);
        if (null != t1) {
            if (!StringUtils.isEmpty(t1.getMenuname())) {
                dc.add(Restrictions.like("menuname", t1.getMenuname(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(t1.getIcon())) {
                dc.add(Restrictions.like("icon", t1.getIcon(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(t1.getUrl())) {
                dc.add(Restrictions.like("url", t1.getUrl(), MatchMode.ANYWHERE));
            }
            if (null != t1.getPid()) {
                dc.add(Restrictions.eq("pid", t1.getPid()));
            }
        }
        return dc;
    }

    @Override
    public void delDo(int id) {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        MenuDo menuDo = hibernateTemplate.get(MenuDo.class, Integer.valueOf(id).toString());       //id：数据库是string, 如果这里写成Long, hibernate是不会自动转化的
        hibernateTemplate.delete(menuDo);
    }

}
