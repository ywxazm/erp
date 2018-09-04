package com.ywx.erp.service.impl;

import com.ywx.erp.common.PIOUtil;
import com.ywx.erp.common.StringConstants;
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
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SupplierServiceImpl extends BaseServiceImpl<SupplierDo> implements SupplierService {

    //EXCEL工作表间名称
    private static final String SUPPLIERSHEETNAME = "供应商列表";
    private static final String CUSTERSHEETNAME = "客户列表";

    private SupplierDao supplierDao;
    public void setSupplierDao(SupplierDao supplierDao) {
        super.setBaseDao(supplierDao);
        this.supplierDao = supplierDao;
    }

//    @Override
//    public void export(OutputStream os, SupplierDo t) {
//        List<SupplierDo> supplierList = super.list(t, null, null);
//        HSSFWorkbook wk = new HSSFWorkbook();               //创建excel文件
//        HSSFSheet sheet = null;
//        switch (t.getType()) {                              //创建工作表
//            case StringConstants.ONECHAR: sheet = wk.createSheet(SUPPLIERSHEETNAME); break;
//            case StringConstants.TWOCHAR: sheet = wk.createSheet(CUSTERSHEETNAME);  break;
//            default: logger.debug("export type error, typeCode = {}", t.getType());
//        }
//
//        PIOUtil.export(sheet, supplierList);
//        PIOUtil.closeStream(os, wk);
//    }


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
