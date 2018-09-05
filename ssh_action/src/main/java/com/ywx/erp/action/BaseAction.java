package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.ywx.erp.common.PIOConstants;
import com.ywx.erp.common.StringConstants;
import com.ywx.erp.common.WriteDate;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.service.BaseService;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements WriteDate{

    static final Logger logger = LoggerFactory.getLogger(BaseAction.class);

    protected BaseService<T>  baseService;
    protected void setBaseService(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    //前台提交参数
    protected T t;
    protected T tt;
    protected Object obj;
    public T getT() {
        return t;
    }
    public void setT(T t) {
        this.t = t;
    }
    public T getTt() {
        return tt;
    }
    public void setTt(T tt) {
        this.tt = tt;
    }
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * 查询t
     */
    public void list() {
        try {
            logger.debug("operaObj is = {}, query list param is t = {}, tt = {}, obj = {}", this, t, tt, obj);
            List<T> list = baseService.list(t, tt, obj);
            String str = JSONObject.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);           //fastJson参数用于关联查询时，关闭循环引用
            write(str);
        } catch (Exception e) {
            logger.error("operaObj is = {}, query list is error, info = {}", this, e.getMessage());
        }
    }

    //第几页
    protected int page;
    //每页显示条目数
    protected int rows;
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    /**
     * 分页查询
     */
    public void listByPage() {
        logger.debug("operaObj is = {}, query listByPage param is t = {}, tt = {}, obj = {}, page = {}, rows = {}", this, t, tt, obj, page, rows);
        try {
            List<T> list = baseService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = baseService.getCount(t, tt, obj);       //统计总条目数
            HashMap<String, Object> map = new HashMap<>();
            map.put("rows", list);
            map.put("total", count);
            String str = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            write(str);
        } catch (Exception e) {
            logger.error("operaObj is = {}, query listByPage is error, info = {}", this, e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 添加
     */
    public void addDo() {
        logger.debug("operaObj is = {}, addDo doing, addDo = {}", this,  t);
        try {
            baseService.addDo(t);
            write(ajaxReturn(true, "添加成功"));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, addDo is error,  msg = {}", this, ex.getMessage());
            write(ajaxReturn(false, "添加失败"));
        }
    }

    protected Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 删除部门
     */
    public void delDo() {
        logger.debug("operaObj is = {}, delDo() doing, id = {}", this, id);
        try {
            baseService.delDo(id);
            write(ajaxReturn(true, "删除成功"));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, delDo is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(false, "删除失败"));
        }
    }

    /**
     * 根据ID去获取
     */
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            T t = baseService.getDo(id);
            write(JSONObject.toJSONString(t));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }

    /**
     * 更新
     */
    public void updateDo() {
        logger.debug("operaObj is = {}, updateDo() doing, Do = {}", this, t);
        try {
            baseService.updateDo(t);
            write(ajaxReturn(true, "更新成功"));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, updateDep is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(false, "更新失败"));
        }
    }

    /**
     * 获取当前用户
     */
    protected EmpDo getLoginUser() {
        return (EmpDo)ServletActionContext.getContext().getSession().get("user");
    }


    /**
     * 导出
     */
    public void export() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, export store doing", this);

        String fileName = PIOConstants.UNDEFINE;
        String thisNameClassPath = this.getClass().getName();
        String actionName = thisNameClassPath.substring(thisNameClassPath.lastIndexOf(".") + 1);
        switch (actionName) {
            case "DepAction":           fileName = PIOConstants.DEPFILRNAME; break;
            case "EmpAction":           fileName = PIOConstants.EMPFILRNAME; break;
            case "GoodsAction":         fileName = PIOConstants.GOODSFILRNAME; break;
            case "GoodstypeAction":     fileName = PIOConstants.GOODSTYPEFILRNAME; break;
            case "OrderdetailAction":   fileName = PIOConstants.ORDERDETAILFILRNAME; break;
            case "OrdersAction":        fileName = PIOConstants.ORDERFILRNAME; break;
            case "StoreAction":         fileName = PIOConstants.STOREFILRNAME; break;
            case "StoredetailAction":   fileName = PIOConstants.STOREODETAILFILRNAME; break;
            case "StoreoperAction":     fileName = PIOConstants.STOREOPERFILRNAME; break;
            case "SupplierAction":      fileName = PIOConstants.SUPPLIERFILRNAME; break;
            default:                    logger.debug("export type error, typeCode = {}", actionName);
        }

        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader(PIOConstants.ContentDisposition, PIOConstants.PARAM01.concat(new String(fileName.getBytes(), PIOConstants.ISO_8859_1)));
            baseService.export(response.getOutputStream(), getT());

            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, export store done, cast time = {}", this, endTime - startTime);
        }catch (Exception ex) {
            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, export store done, cast time = {}", this, endTime - startTime);
            logger.error("operaObj is = {}, export store is error, msg = {}", this, ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * 导入
     */
    private File file;//上传的文件
    private String fileFileName;//上传的文件名称
    private String fileContentType;//上传的文件类型
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
    public void importData() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, export store doing", this);

        if (!PIOConstants.EXCELFILETYPE.equals(fileContentType)) {
            ajaxReturn(false, "上传文件类型必需为.xls");
        }

        try {
            baseService.importData(file, this.getClass());
            ajaxReturn(true, "上传文件成功");
            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, export store done, cast time = {}", this, endTime - startTime);
        }catch (Exception ex) {
            ajaxReturn(true, "上传文件失败");
            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, export store done, cast time = {}", this, endTime - startTime);
            logger.error("operaObj is = {}, export store is error, msg = {}", this, ex.getMessage());
            ex.printStackTrace();
        }
    }

}
