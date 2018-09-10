package com.ywx.erp.service.test;

import com.ywx.erp.dao.MenuDao;
import com.ywx.erp.entity.MenuDo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest {

    @Test
    public void test01() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        MenuDao menuDao = (MenuDao) ac.getBean("menuDao");
        MenuDo menuDo = menuDao.getDo("0");
        System.out.println(menuDo);
    }
}
