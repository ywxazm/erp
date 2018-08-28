package com.ywx.dao.sample.impl;

import com.ywx.dao.sample.TeacherDaoSample;
import com.ywx.entity.sample.TeacherDo;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class TeacherDaoSampleImpl extends HibernateDaoSupport implements TeacherDaoSample {

    @Override
    public List<TeacherDo> TecherList() {
        List<TeacherDo> techerList = (List<TeacherDo>)this.getHibernateTemplate().find("from TeacherDo");
        return techerList;
    }
}
