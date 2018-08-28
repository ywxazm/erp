package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.GoodsDao;
import com.ywx.erp.entity.GoodsDo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class GoodsDaoImpl extends BaseDaoImpl<GoodsDo> implements GoodsDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(GoodsDo t1, GoodsDo t2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(GoodsDo.class);
        if (null != t1) {                                                                            //TODO: 是不是可以用switch来代替
            if (!StringUtils.isEmpty(t1.getName())) {
                dc.add(Restrictions.like("name", t1.getName(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(t1.getOrigin())) {
                dc.add(Restrictions.like("origin", t1.getOrigin(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(t1.getProducer())) {
                dc.add(Restrictions.like("producer", t1.getProducer(), MatchMode.ANYWHERE));
            }
            if (null != t1.getGoodsTypeDo() && null != t1.getGoodsTypeDo().getUuid()) {             //TODO：极为重要！！！ 注意前后两个条件， 猜想： hibernate会给它一个实例，但内容为null
                dc.add(Restrictions.eq("goodsTypeDo", t1.getGoodsTypeDo()));        //TODO： 多对一的情况要注意
            }
            //进价，起点
            if (null != t1.getInprice()) {
                dc.add(Restrictions.ge("inprice", t1.getInprice()));
            }
            //销价,起点
            if (null != t1.getOutprice()) {
                dc.add(Restrictions.ge("outprice", t1.getOutprice()));
            }
        }
        if(null != t2) {
            //进价，终点
            if (null != t2.getInprice()) {
                dc.add(Restrictions.le("inprice", t2.getInprice()));
            }
            //销价,终点
            if (null != t2.getOutprice()) {
                dc.add(Restrictions.le("outprice", t2.getOutprice()));
            }
        }
        return dc;
    }
}
