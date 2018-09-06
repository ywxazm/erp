package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.EmpDao;
import com.ywx.erp.entity.EmpDo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class EmpDaoImpl extends BaseDaoImpl<EmpDo> implements EmpDao {

    @Override
    protected DetachedCriteria getDetachedCriteria(EmpDo e1, EmpDo e2, Object obj) {
        DetachedCriteria dc = DetachedCriteria.forClass(EmpDo.class);
        if (null != e1) {
            if (!StringUtils.isEmpty(e1.getUsername())) {
                dc.add(Restrictions.like("username", e1.getUsername(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(e1.getName())) {
                dc.add(Restrictions.like("name", e1.getName(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(e1.getEmail())) {
                dc.add(Restrictions.like("email", e1.getEmail(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(e1.getTele())) {
                dc.add(Restrictions.like("tele", e1.getTele(), MatchMode.ANYWHERE));
            }
            if (null != e1.getGender()) {
                dc.add(Restrictions.eq("gender", e1.getGender()));
            }
            if (!StringUtils.isEmpty(e1.getAddress())) {
                dc.add(Restrictions.like("address", e1.getAddress(), MatchMode.ANYWHERE));
            }
            //员工所属部门查询
            if (null != e1.getDepDo() && null != e1.getDepDo().getUuid()) {
                dc.add(Restrictions.eq("depDo", e1.getDepDo()));
            }
            //时间区间查询,起始时间
            if (null != e1.getBirthday()) {
                //ge >   greater than     // >= great than or equal to
                dc.add(Restrictions.ge("birthday", e1.getBirthday()));
            }
        }
        //时间区间查询，结束时间
        if (null != e2 && null != e2.getBirthday()) {
            //le  <   less than
            dc.add(Restrictions.le("birthday", e2.getBirthday()));
        }
        return dc;
    }

    @Override
    public EmpDo findByUsernameAndPwd(String username, String pwd) {
        List<EmpDo> list = (List<EmpDo>)this.getHibernateTemplate().find("from EmpDo where username = ? and pwd = ?", username, pwd);
        return null == list || list.size() == 0 ? null : list.get(0);
    }
}
