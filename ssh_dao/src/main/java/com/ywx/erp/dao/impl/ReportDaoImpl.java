package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.ReportDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportDaoImpl extends HibernateDaoSupport implements ReportDao {

    @Override
    public List ordersReport(Date startDate, Date endDate) {
        String hql = "select new Map(gt.name as name,sum(ol.money) as y) "
                + "from GoodsTypeDo gt, GoodsDo gs, OrderdetailDo ol, OrdersDo o "
                + "where "
                + "gs.goodsTypeDo =gt and ol.ordersDo = o "
                + "and ol.goodsuuid=gs.uuid and o.type='2' ";
        List<Date> dateList = new ArrayList<Date>();
        if(null != startDate){
            hql += "and o.createtime>=? ";
            dateList.add(startDate);
        }
        if(null != endDate){
            hql += "and o.createtime<=? ";
            dateList.add(endDate);
        }
        hql += "group by gt.name";
        //指定转换的类型
        Date[] param = new Date[0];
        //转成新的数组
        Date[] params = dateList.toArray(param);
        return getHibernateTemplate().find(hql,params);
    }
}
