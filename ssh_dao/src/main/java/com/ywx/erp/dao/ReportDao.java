package com.ywx.erp.dao;

import java.util.Date;
import java.util.List;

public interface ReportDao {

    List ordersReport(Date startDate, Date endDate);
}
