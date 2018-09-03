package com.ywx.erp.service.impl;

import com.ywx.erp.dao.ReportDao;
import com.ywx.erp.service.ReportService;

import java.util.Date;
import java.util.List;

public class ReportServiceImpl implements ReportService {

    private ReportDao reportDao;
    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public List ordersReport(Date startDate, Date endDate) {
        return reportDao.ordersReport(startDate, endDate);
    }
}
