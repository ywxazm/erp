package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.ywx.erp.common.BaseConstants;
import com.ywx.erp.common.PIOConstants;
import com.ywx.erp.common.WriteDate;
import com.ywx.erp.entity.EmpDo;
import com.ywx.erp.service.BaseService;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements WriteDate{

    static final Logger logger = LoggerFactory.getLogger(BaseAction.class);

    protected BaseService<T>  baseService;
    protected void setBaseService(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    //常量定义
        protected static final String ROWS = "rows";
        protected static final String TOTAL = "total";
        private static final String USER = "user";
        protected static final String ADDSUCCESS= "ADD SUCCESS";
        protected static final String ADDFAIL = "ADD FAIL";
        private static final String UPDATASUCCESS = "UPDATA SUCCESS";
        private static final String UPDATAFAIL = "UPDATA FAIL";
        private static final String DELSUCCESS = "DEL SUCCESS";
        private static final String DELFAIL = "DEL FAIL";
        private static final String UPLOADSUCCESS = "UPLOAD SUCCESS";
        private static final String UPLOADFAIL = "UPLOAD FAIL";
        private static final String UPLOADMSG = "UPLOAD FILE MUST TYPE IS .xls";

        //接收数据
        protected T t;
        protected T tt;
        protected Object obj;
        protected Integer id;
        protected int page;     //第几页
        protected int rows;     //每页显示条目数
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
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 查询t
     */
    public void list() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, query list param is t = {}, tt = {}, obj = {}", this, t, tt, obj);
        try {
            List<T> list = baseService.list(t, tt, obj);
            //fastJson参数用于关联查询时，关闭循环引用
            String str = JSONObject.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
            write(str);

            logger.debug("operaObj is = {}, list() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("operaObj is = {}, query list is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, list() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 分页查询
     */
    public void listByPage() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, query listByPage param is t = {}, tt = {}, obj = {}, page = {}, rows = {}",
                this, t, tt, obj, page, rows);
        try {
            List<T> list = baseService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = baseService.getCount(t, tt, obj);       //统计总条目数
            HashMap<String, Object> map = new HashMap<>();
            map.put(ROWS, list);
            map.put(TOTAL, count);
            String json = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            write(json);

            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.error("operaObj is = {}, query listByPage is error, info = {}", this, e.getMessage());
            logger.debug("operaObj is = {}, listByPage() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 添加
     */
    public void addDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, addDo doing, addDo = {}", this,  t);

        try {
            baseService.addDo(t);
            write(ajaxReturn(BaseConstants.TRUE, ADDSUCCESS));
            logger.debug("operaObj is = {}, addDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            write(ajaxReturn(BaseConstants.FALSE, ADDFAIL));
            logger.error("operaObj is = {}, addDo() is error,  msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, list cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 删除部门
     */
    public void delDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, delDo() doing, id = {}", this, id);
        try {
            baseService.delDo(id);
            write(ajaxReturn(BaseConstants.TRUE, DELSUCCESS));
            logger.debug("operaObj is = {}, delDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.error("operaObj is = {}, delDo is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, delDo() cast time = {}", this, System.currentTimeMillis() - startTime);
            write(ajaxReturn(BaseConstants.FALSE, DELFAIL));
        }
    }

    /**
     * 根据ID去获取
     */
    public void getDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, getDo() doing, id = {}", this, id);
        try {
            T t = baseService.getDo(id);
            write(JSONObject.toJSONString(t));
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, getDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 更新
     */
    public void updateDo() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, updateDo() doing, Do = {}", this, t);
        try {
            baseService.updateDo(t);
            write(ajaxReturn(BaseConstants.TRUE, UPDATASUCCESS));
            logger.debug("operaObj is = {}, updateDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }catch (Exception ex) {
            write(ajaxReturn(BaseConstants.FALSE, UPDATAFAIL));
            logger.error("operaObj is = {}, updateDo is error, msg = {}", this, ex.getMessage());
            logger.debug("operaObj is = {}, updateDo() cast time = {}", this, System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 获取当前用户
     */
    protected EmpDo getLoginUser() {
        return (EmpDo)ServletActionContext.getContext().getSession().get(USER);
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
            case BaseConstants.DEPACTION:           fileName = PIOConstants.DEPFILRNAME; break;
            case BaseConstants.EMPACTION:           fileName = PIOConstants.EMPFILRNAME; break;
            case BaseConstants.GOODSACTION:         fileName = PIOConstants.GOODSFILRNAME; break;
            case BaseConstants.GOODSTYPEACTION:     fileName = PIOConstants.GOODSTYPEFILRNAME; break;
            case BaseConstants.ORDERDETAILACTION:   fileName = PIOConstants.ORDERDETAILFILRNAME; break;
            case BaseConstants.ORDERSACTION:        fileName = PIOConstants.ORDERFILRNAME; break;
            case BaseConstants.STOREACTION:         fileName = PIOConstants.STOREFILRNAME; break;
            case BaseConstants.STOREDETAILACTION:   fileName = PIOConstants.STOREODETAILFILRNAME; break;
            case BaseConstants.STOREOPERACTION:     fileName = PIOConstants.STOREOPERFILRNAME; break;
            case BaseConstants.SUPPLIERACTION:      fileName = PIOConstants.SUPPLIERFILRNAME; break;
            default:                                logger.debug("export type error, typeCode = {}", actionName);
        }

        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader(PIOConstants.ContentDisposition, PIOConstants.PARAM01.concat(
                                new String(fileName.getBytes(), PIOConstants.ISO_8859_1)));
            baseService.export(response.getOutputStream(), getT());

            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, export store done, cast time = {}", this, endTime - startTime);
        }catch (Exception ex) {
            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, export store done, cast time = {}", this, endTime - startTime);
            logger.error("operaObj is = {}, export store is error, msg = {}", this, ex.getMessage());
        }
    }

    /**
     * 导入
     */
    private File file;//上传的文件
    private String fileFileName;//上传的文件名称(必需要是这个名字，file是自定义的)
    private String fileContentType;//上传的文件类型
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public String getFileContentType() {
        return fileContentType;
    }
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
    public String getFileFileName() {
        return fileFileName;
    }
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
    public void importData() {
        Long startTime = System.currentTimeMillis();
        logger.debug("operaObj is = {}, export store doing， the fileFileName = {}", this, fileFileName);

        if (!PIOConstants.EXCELFILETYPE.equals(fileContentType)) {
            write(ajaxReturn(BaseConstants.FALSE, UPLOADMSG));
        }

        try {
            baseService.importData(file, fileFileName, this.getClass());
            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, export store done, cast time = {}", this, endTime - startTime);
            write(ajaxReturn(BaseConstants.TRUE, UPLOADSUCCESS));
        }catch (Exception ex) {
            Long endTime = System.currentTimeMillis();
            logger.debug("operaObj is = {}, export store done, cast time = {}", this, endTime - startTime);
            logger.error("operaObj is = {}, export store is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(BaseConstants.TRUE, UPLOADFAIL));
        }
    }

}
