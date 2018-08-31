package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.OrderdetailDao;
import com.ywx.erp.entity.OrderdetailDo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.StringUtils;

public class OrderdetailDaoImpl extends BaseDaoImpl<OrderdetailDo> implements OrderdetailDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(OrderdetailDo t1, OrderdetailDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(OrderdetailDo.class);
        if (null != t1) {
            if (null != t1.getGoodsuuid()) {
                dc.add(Restrictions.eq("goodsuuid", t1.getGoodsuuid()));
            }
            if (!StringUtils.isEmpty(t1.getGoodsname())) {
                dc.add(Restrictions.like("goodsname", t1.getGoodsname(), MatchMode.ANYWHERE));
            }
            if (null != t1.getPrice()) {
                dc.add(Restrictions.eq("price", t1.getPrice()));
            }
            if (null != t1.getNum()) {
                dc.add(Restrictions.eq("num", t1.getNum()));
            }
            if (null != t1.getMoney()) {
                dc.add(Restrictions.eq("money", t1.getMoney()));
            }
            if (null != t1.getEndtime()) {
                dc.add(Restrictions.like("endtime", t1.getEndtime()));
            }
            if (null != t1.getEnder()) {
                dc.add(Restrictions.eq("ender", t1.getEnder()));
            }
            if (null != t1.getStoreuuid()) {
                dc.add(Restrictions.eq("storeuuid", t1.getStoreuuid()));
            }
            if (null != t1.getState()) {
                dc.add(Restrictions.like("state", t1.getState()));
            }
            if (null != t1.getOrdersDo() && null != t1.getOrdersDo().getUuid()) {
                dc.add(Restrictions.eq("ordersuuid", t1.getOrdersDo().getUuid()));
            }
        }
        return dc;
    }
}
