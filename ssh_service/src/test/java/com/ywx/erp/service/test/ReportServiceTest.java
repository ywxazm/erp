package com.ywx.erp.service.test;

import com.ywx.erp.service.ReportService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ReportServiceTest {

    @Test
    public void test01() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        ReportService reportService = (ReportService)ac.getBean("reportService");
        List list = reportService.ordersReport(null, null);
        System.out.println(list);

    }
}
