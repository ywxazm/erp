package com.ywx.erp.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class PIOUtil implements Serializable {

    protected static final Logger logger = LoggerFactory.getLogger(PIOUtil.class);

    /**
     * 通用导出功能
     *
     * @param sheet
     * @param dataList
     */
    public static void export(HSSFSheet sheet, List dataList, Class clazz) throws Exception {

        if (null == dataList || dataList.size() == 0) {
            return;
        }

        List<String> nameList = getColumName(clazz);
        setColumsInfo(sheet, nameList);

        pareProcess(dataList);                  //TODO:这里可以做得更详细，例如例出所有的外键对应的表的数据
        setValue(sheet, dataList, nameList);
    }

    private static void pareProcess(List dataList) {
        ListIterator listIterator = dataList.listIterator();
        while (listIterator.hasNext()) {
            Object obj = listIterator.next();
            String dataJson = JSONObject.toJSONString(obj).substring(dataJson.indexOf("{") + 1, dataJson.lastIndexOf("}"));
            System.out.println(dataJson);
            if (dataJson.contains("{")) {
                String dataJson3 = dataJson2.substring(dataJson.indexOf("{") + 1, dataJson.lastIndexOf("}"));
                if (dataJson3.contains("name")) {
                    String dataJson4 = dataJson3.substring(dataJson3.indexOf("name"));
                    String name = dataJson4.substring(dataJson4.indexOf(":") + 1, dataJson4.indexOf(","));
                    dataJson.replace(newDataJson, name);
                }
            }
            System.out.println(dataJson);
            dataList.remove(obj);
            dataList.add(JSONObject.parse(dataJson));
        }
    }

    /**
     * 设置Cell内容
     *
     * @param sheet
     * @param dataList
     * @param nameList
     */
    private static void setValue(HSSFSheet sheet, List dataList, List<String> nameList) {
        HSSFRow row;// 设置内容
        int i = 1;
        for (Object o : dataList) {
            row = sheet.createRow(i);                       //新建行
            String json = JSONObject.toJSONString(o).replaceAll("\"", "");
            int j = 0;
            for (String s : nameList) {
                int start = json.indexOf(s) + s.length() + 1;
                String newJson = json.substring(start);
                if (!newJson.contains(",")) {
                    row.createCell(j).setCellValue(newJson.substring(0, newJson.length() - 1));
                } else {
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
     *
     * @param sheet
     * @param nameList
     */
    private static void setColumsInfo(HSSFSheet sheet, List<String> nameList) {
        //设置第一行
        HSSFRow row = sheet.createRow(0);          //创建行
        int columnWidths = 4000;
        HSSFCell cell = null;                               //最小单元容器
        for (int i = 0; i < nameList.size(); i++) {
            cell = row.createCell(i);                       //创建第一个Cell
            cell.setCellValue(nameList.get(i));             //设置值
            sheet.setColumnWidth(i, columnWidths);          //设置Cell的列序列，和列宽
        }
    }

    /**
     * 获取列信息
     *
     * @param clazz
     * @return
     */
    private static List<String> getColumName(Class clazz) {
        List<String> nameList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            nameList.add(field.getName());
        }
        return nameList;
    }

    /**
     * 关闭流
     *
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
