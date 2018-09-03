package com.ywx.erp.service;

import java.util.Date;
import java.util.List;

public interface ReportService {

    List ordersReport(Date startDate, Date endDate);
}
