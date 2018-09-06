package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.common.WriteDate;
import com.ywx.erp.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class ReportAction implements WriteDate {

    static final Logger logger = LoggerFactory.getLogger(ReportAction.class);

    private ReportService reportService;
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    //接收数据
    private Date startDate;
    private Date endDate;
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 采购订单和销售订单报表
     */
    public void ordersReport() {
        try {
            logger.debug("operaObj is = {}, ordersReport param is startDate = {}, endDate = {}", this, startDate, endDate);
            List list = reportService.ordersReport(startDate, endDate);
            write(JSONObject.toJSONString(list));
        } catch (Exception e) {
            logger.error("operaObj is = {}, ordersReport is error, info = {}", this, e.getMessage());
            e.printStackTrace();
        }
    }
}
