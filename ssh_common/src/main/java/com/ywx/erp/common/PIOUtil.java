package com.ywx.erp.common;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.exception.ErpException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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
            logger.info("the export data is null, end");
            return;
        }

        String firstLineDate = JSONObject.toJSONString(dataList.get(0));
        Map<String, Object> firstLineDataMap = (Map<String, Object>) JSONObject.parse(firstLineDate);
        List<String> nameList = getColumName(firstLineDataMap, clazz);
        setColumsInfo(sheet, nameList);

        List<Map<String, Object>> newList = pareProcess(dataList);
        setValue(sheet, newList, nameList);
    }

    /**
     * 通用导入功能
     */
    public static void importData(File file, Class clazz) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {
        //获取对应的Do,dao的名字
        FileInputStream is = new FileInputStream(file);
        Workbook wb = getWorkbook(file, is);
        String actionName = clazz.getName();
        int start = actionName.lastIndexOf(".");
        String doName = "com.ywx.erp.entity" + actionName.substring(start).replace("Action", "Do");
        String daoName = "com.ywx.erp.dao.impl" + actionName.substring(start).replace("Action", "DaoImpl");

        List<Object> dataList = getDataList(wb, doName);

        //写数据库
        Class<?> daoClazz = Class.forName(daoName);
        //Object obj = daoClazz.newInstance();
        daoName = actionName.substring(start + 1).replace("Action", "Dao");
        daoName = daoName.substring(0, 1).toLowerCase() + daoName.substring(1);
        //ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:applicationContext-*.xml");
        WebApplicationContext ac = ContextLoader.getCurrentWebApplicationContext(); //TODO: 获取正在运行的Spring上下文
        Object obj = ac.getBean(daoName);//TODO: 很重要，如果直接是反射获取Dao对象，HibernateTemplete == null， 需要如上操作

        Method[] methods = daoClazz.getMethods();
        for (Object data : dataList) {
            for (Method method : methods) {
                if (method.getName().contains("add")) {
                    method.invoke(obj, data);
                }
            }
        }

        closeWk(is);
    }

    /**
     * 判断导入文件版本
     * @param file
     * @param is
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(File file, InputStream is) throws IOException {
        String fileName = file.getName();
        System.out.println(fileName);
        String extendNmae = fileName.substring(fileName.lastIndexOf(".") + 1);
        Workbook wb = null;
        switch (extendNmae) {
            case "xls"  :   wb = new HSSFWorkbook(is); break;
            case "xlsx" :   wb = new XSSFWorkbook(is); break;
            default     :   throw new ErpException("Import file type error, the file name is " + extendNmae);
        }
        return wb;
    }

    /**
     * 读取提交Excel,组装成List集合
     *
     * @param wb
     * @param doName
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static List<Object> getDataList(Workbook wb, String doName) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, InvocationTargetException {
        //填充匹配的数据,放到List中
        Class<?> clazz = Class.forName(doName);
        Method[] methods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();
        Sheet sheet = wb.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        List<Object> dataList = new ArrayList<>();
        for (int i = 1; i <= lastRow; i++) {
            Object obj = clazz.newInstance();
            for (int j = 0; j < fields.length; j++) {
                String columName = sheet.getRow(0).getCell(j).getStringCellValue();
                String columValue = sheet.getRow(i).getCell(j).getStringCellValue();
                for (Method method : methods) {
                    if (method.getName().toLowerCase().contains(columName) && method.getName().contains("set")) {
                        method.invoke(obj, processParamType(method, columValue));
                    }
                }
            }
            dataList.add(obj);
        }
        return dataList;
    }

    /**
     * 根据方法定义参数类型，将值转化为对应类型
     *
     * @param method
     * @param columValue
     * @return
     */
    public static Object processParamType(Method method, String columValue) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> parameterType = parameterTypes[0];
        System.out.println(parameterType.getTypeName());

        String typeName = parameterType.getTypeName();
        if ("java.lang.String".equals(typeName)) {
            return columValue;
        } else if ("java.lang.Character".equals(typeName)) {
            return "0".equals(typeName) ? '0' : '1';
        } else if ("java.lang.Double".equals(typeName)) {
            Double.parseDouble(columValue);
        } else if ("java.lang.Integer".equals(typeName)) {
            Integer.parseInt(columValue);
        } else if ("java.lang.Long".equals(typeName)) {
            Long.parseLong(columValue);
        } else if ("java.lang.Float".equals(typeName)) {
            Float.parseFloat(columValue);
        } else if ("java.lang.Boolean".equals(typeName)) {
            if ("false".equals(columValue) || "0".equals(columValue)) {
                return false;
            } else if ("true".equals(columValue) || "1".equals(columValue)) {
                return true;
            }
        }
        return null;
    }

    /**
     * 前处理，处理有外键的数据
     *
     * @param dataList
     */
    private static List<Map<String, Object>> pareProcess(List dataList) {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            String lineData = JSONObject.toJSONString(dataList.get(i));
            Map<String, Object> lineDataMap = (Map<String, Object>) JSONObject.parse(lineData);
            process(lineDataMap);
            list.add(lineDataMap);
        }
        return list;
    }

    /**
     * 导出时，如果在JOSN中带有另一个JavaBean的Json格式数据，则只有名字
     *
     * @param lineDataMap
     */
    private static void process(Map<String, Object> lineDataMap) {
        Set<String> keys = lineDataMap.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = JSONObject.toJSONString(lineDataMap.get(key));
            System.out.println("value = " + value);
            if (value.contains("{")) {
                Map<String, Object> map = (Map<String, Object>) JSONObject.parse(value);
                lineDataMap.put(key, map.get("uuid"));
                if (null != map.get("name"))
                    lineDataMap.put(key, map.get("name"));
            }
        }
    }

    /**
     * 设置Cell内容
     *
     * @param sheet
     * @param dataList
     * @param nameList
     */
    private static void setValue(HSSFSheet sheet, List<Map<String, Object>> dataList, List<String> nameList) {
        System.out.println(JSONObject.toJSON(dataList));
        HSSFRow row;
        int i = 1;
        for (Map<String, Object> map : dataList) {
            row = sheet.createRow(i);                       //新建行
            int j = 0;
            for (String name : nameList) {
                row.createCell(j).setCellValue(JSONObject.toJSONString(map.get(name)).replace("\"", ""));
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
    private static List<String> getColumName(Map<String, Object> firstLineDataMap, Class clazz) {
        List<String> nameList = new ArrayList<>();
        if (null != clazz) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                nameList.add(field.getName());
            }
        } else {
            Set<String> set = firstLineDataMap.keySet();
            nameList.addAll(set);
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
            wk.write(os);      //写流
        } catch (IOException e) {
            logger.error("error msg is = {}", e.getMessage());
        } finally {
            try {
                wk.close();    //关流
            } catch (IOException e) {
                logger.error("close stream error, msg = {}", e.getMessage());
            }
        }
    }

    /**
     * 关闭is
     * * @param wb
     */
    public static void closeWk(InputStream is) {
        try {
            is.close();
        } catch (IOException e) {
            logger.error("error msg = {}", e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("close stream error, msg = {}", e.getMessage());
            }
        }
    }
}
