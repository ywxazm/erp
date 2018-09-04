package com.ywx.erp.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PIOUtil implements Serializable {

    protected static final Logger logger = LoggerFactory.getLogger(PIOUtil.class);

    /**
     * 通用导出功能
     * @param sheet
     * @param dataList
     */
    public static void export(HSSFSheet sheet, List dataList){

        if (null == dataList || dataList.size() == 0) {
            return;
        }

        List<String> list = getColumName(dataList);
        setColumsInfo(sheet, list);
        setValue(sheet, dataList, list);
    }

    /**
     * 设置Cell内容
     * @param sheet
     * @param dataList
     * @param list
     */
    private static void setValue(HSSFSheet sheet, List dataList, List<String> list) {
        HSSFRow row;// 设置内容
        int i = 1;
        for (Object o : dataList) {
            row = sheet.createRow(i);                       //新建行
            String json = JSONObject.toJSONString(o).replaceAll("\"", "");
            int j = 0;
            for (String s : list) {
                int start = json.indexOf(s) + s.length() + 1;
                String newJson = json.substring(start);
                if (!newJson.contains(",")) {
                    row.createCell(j).setCellValue(newJson.substring(0, newJson.length() - 1));
                }else {
                    int end = newJson.indexOf(",");
                    row.createCell(j).setCellValue(newJson.substring(0, end));
                }
                j++;
            }
            i++;
        }
    }

    /**
     * 设置第一行的列信息
     * @param sheet
     * @param list
     */
    private static void setColumsInfo(HSSFSheet sheet, List<String> list) {
        //设置第一行
        HSSFRow row = sheet.createRow(0);          //创建行
        int columnWidths = 4000;
        HSSFCell cell = null;                               //最小单元容器
        for (int i = 0; i < list.size(); i++) {
            cell = row.createCell(i);                       //创建第一个Cell
            cell.setCellValue(list.get(i));                 //设置值
            sheet.setColumnWidth(i, columnWidths);          //设置Cell的列序列，和列宽
        }
    }

    /**
     * 获取列信息
     * @param dataList
     * @return
     */
    private static List<String> getColumName(List dataList) {
        String[] srr = JSONObject.toJSONString(dataList.get(0)).split(",");
        List<String> list = new ArrayList<>();
        for (String s : srr) {
            if (s.contains("{")) {
                int i = s.indexOf("{");
                s = s.substring(i + 1);
            }
            int start = s.indexOf("\"");
            int end = s.substring(start + 1).indexOf("\"");
            list.add(s.substring(start + 1, end + 1));
        }
        return list;
    }

    /**
     * 关闭流
     * @param os
     * @param wk
     */
    public static void closeStream(OutputStream os, HSSFWorkbook wk) {
        try {
            wk.write(os);                                                   //写流
        } catch (IOException e) {
            logger.error("error msg is = {}", e.getMessage());
        } finally {
            try {
                wk.close();                                                 //关流
            } catch (IOException e) {
                logger.error("close stream error, msg = {}", e.getMessage());
            }
        }
    }
}
