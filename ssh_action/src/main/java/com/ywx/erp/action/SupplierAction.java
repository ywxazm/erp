package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.ywx.erp.common.PIOConstants;
import com.ywx.erp.common.StringConstants;
import com.ywx.erp.entity.SupplierDo;
import com.ywx.erp.exception.ErpException;
import com.ywx.erp.service.SupplierService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class SupplierAction extends BaseAction<SupplierDo> {

    //导出文件名
    private static final String SUPPLIERFILENAME = "供应商.xls";
    private static final String CUSTMERFILENAME = "客户.xls";


    private SupplierService supplierService;

    public void setSupplierService(SupplierService supplierService) {
        super.setBaseService(supplierService);
        this.supplierService = supplierService;
    }

    /**
     * 根据ID去获取
     */
    @Override
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            SupplierDo supplierDo = supplierService.getDo(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("t.uuid", supplierDo.getUuid());
            map.put("t.name", supplierDo.getName());
            map.put("t.address", supplierDo.getAddress());
            map.put("t.contact", supplierDo.getContact());
            map.put("t.tele", supplierDo.getTele());
            map.put("t.email", supplierDo.getEmail());
            map.put("t.type", supplierDo.getType());
            write(JSONObject.toJSONString(map));
        } catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {} ", this, ex.getMessage());
        }
    }

//    /**
//     * 导出excel文件
//     */
//    public void export() {
//        logger.debug("operaObj is = {}, export() doing", this);
//        String fileName = StringConstants.NULLSTRING;
//        switch (getT().getType()) {
//            case StringConstants.ONECHAR: fileName = SUPPLIERFILENAME; break;
//            case StringConstants.TWOCHAR: fileName = CUSTMERFILENAME;  break;
//            default: logger.debug("export type error, typeCode = {}", getT().getType());
//        }
//
//        HttpServletResponse response = ServletActionContext.getResponse();
//        try {
//            response.setHeader(PIOConstants.ContentDisposition, PIOConstants.PARAM01.concat(new String(fileName.getBytes(), PIOConstants.ISO_8859_1)));
//            supplierService.export(response.getOutputStream(), getT());
//        } catch (IOException e) {
//            logger.error("operaObj is = {}, export is error, msg = {} ", this, e.getMessage());
//        }
//    }

    private File file;//上传的文件
    private String fileFileName;//上传的文件名称
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public String getFileFileName() {
        return fileFileName;
    }
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
    public String getFileContentType() {
        return fileContentType;
    }
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    private String fileContentType;//上传的文件类型

    /**
     * 导入数据
     */
    public void doImport(){
        //文件类型判断
        if(!"application/vnd.ms-excel".equals(fileContentType)){
            ajaxReturn(false, "上传的文件必须为excel文件");
            return;
        }
        try {
            supplierService.doImport(new FileInputStream(file));
            ajaxReturn(true, "上传的文件成功");
        } catch (ErpException e){
            ajaxReturn(false, e.getMessage());
        } catch (IOException e) {
            ajaxReturn(false, "上传的文件失败");
            e.printStackTrace();
        }
    }

}
