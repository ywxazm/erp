package com.ywx.service.sample.impl;

import com.ywx.dao.sample.TeacherDaoSample;
import com.ywx.entity.sample.TeacherDo;
import com.ywx.service.sample.TeacherServiceSample;

import java.util.List;

public class TeacherServiceSampleImpl implements TeacherServiceSample {

    private TeacherDaoSample teacherDaoSample;

    public void setTeacherDaoSample(TeacherDaoSample teacherDaoSample) {
        this.teacherDaoSample = teacherDaoSample;
    }

    @Override
    public List<TeacherDo> list() {
        return teacherDaoSample.TecherList();
    }
}
