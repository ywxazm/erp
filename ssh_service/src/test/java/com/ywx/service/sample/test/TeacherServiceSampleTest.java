package com.ywx.service.sample.test;

import com.ywx.entity.sample.TeacherDo;
import com.ywx.service.sample.TeacherServiceSample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TeacherServiceSampleTest {

    @Test
    public void test01() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        TeacherServiceSample teacherServiceSample = (TeacherServiceSample)ac.getBean("teacherServiceSample");
        List<TeacherDo> list = teacherServiceSample.list();
        list.stream().forEach(System.out::println);
    }
}
