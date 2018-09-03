package com.ywx.erp.service.impl;

import com.ywx.erp.dao.SupplierDao;
import com.ywx.erp.entity.SupplierDo;
import com.ywx.erp.exception.ErpException;
import com.ywx.erp.service.SupplierService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Supplier;

public class SupplierServiceImpl extends BaseServiceImpl<SupplierDo> implements SupplierService {

    private SupplierDao supplierDao;

    public void setSupplierDao(SupplierDao supplierDao) {
        super.setBaseDao(supplierDao);
        this.supplierDao = supplierDao;
    }

    @Override
    public void export(OutputStream os, SupplierDo t) {
        // 根据查询条件获取供应商/客户列表
        List<SupplierDo> supplierList = super.list(t, null, null);
        // 创建excel工作簿
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = null;
        // 根据查询条件中的类型来创建相应名称的工作表
        System.out.println("------------------------>" + t.getType());
        if (1 == Integer.parseInt(t.getType().toString())) {
            sheet = wk.createSheet("供应商");
        }
        if (2 == Integer.parseInt(t.getType().toString())) {
            sheet = wk.createSheet("客户");
        }

        // 写入表头
        HSSFRow row = sheet.createRow(0);
        // 定义好每一列的标题
        String[] headerNames = {"名称", "地址", "联系人", "电话", "Email"};
        // 指定每一列的宽度
        int[] columnWidths = {4000, 8000, 2000, 3000, 8000};
        HSSFCell cell = null;
        for (int i = 0; i < headerNames.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerNames[i]);
            sheet.setColumnWidth(i, columnWidths[i]);
        }

        // 写入内容
        int i = 1;
        for (SupplierDo supplierDo : supplierList) {
            row = sheet.createRow(i);
            //必须按照hderarNames的顺序来
            row.createCell(0).setCellValue(supplierDo.getName());//名称
            row.createCell(1).setCellValue(supplierDo.getAddress());//地址
            row.createCell(2).setCellValue(supplierDo.getContact());//联系人
            row.createCell(3).setCellValue(supplierDo.getTele());//联系电话
            row.createCell(4).setCellValue(supplierDo.getEmail());//邮件地址
            i++;
        }
        try {
            // 写入到输出流中
            wk.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭工作簿
                wk.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void doImport(InputStream is) throws IOException {
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);
            String type = "";
            if("供应商".equals(sheet.getSheetName())){
                type = "1";
            }else if("客户".equals(sheet.getSheetName())){
                type = "2";
            }else{
                throw new ErpException("工作表名称不正确");
            }

            //读取数据
            //最后一行的行号
            int lastRow = sheet.getLastRowNum();
            SupplierDo supplierDo = null;
            for(int i = 1; i <= lastRow; i++){
                supplierDo = new SupplierDo();
                supplierDo.setName(sheet.getRow(i).getCell(0).getStringCellValue());//供应商名称
                //判断是否已经存在，通过名称来判断
                List<SupplierDo> list = supplierDao.list(null, supplierDo, null);
                if(list.size() > 0){
                    supplierDo = list.get(0);
                }
                supplierDo.setAddress(sheet.getRow(i).getCell(1).getStringCellValue());//地址
                supplierDo.setContact(sheet.getRow(i).getCell(2).getStringCellValue());//联系人
                supplierDo.setTele(sheet.getRow(i).getCell(3).getStringCellValue());//电话
                supplierDo.setEmail(sheet.getRow(i).getCell(4).getStringCellValue());//Email
                if(list.size() == 0){
                    //新增
                    supplierDo.setType(new Character(type.charAt(0)));
                    supplierDao.addDo(supplierDo);
                }
            }
        } finally{
            if(null != wb){
                try {
                    wb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
