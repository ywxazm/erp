package com.ywx.erp.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.ywx.erp.common.WriteDate;
import com.ywx.erp.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * 分页查询部门
     */
    public void listByPage() {
        try {
            logger.debug("operaObj is = {}, query listByPage param is t = {}, tt = {}, obj = {}, page = {}, rows = {}", this, t, tt, obj, page, rows);
            List<T> list = baseService.listByPage(t, tt, obj, (page - 1) * rows, rows);
            Long count = baseService.getCount(t, tt, obj);       //统计总条目数
            HashMap<String, Object> map = new HashMap<>();
            map.put("rows", list);
            map.put("total", count);
            String str = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
            write(str);
        } catch (Exception e) {
            logger.error("operaObj is = {}, query listByPage is error, info = {}", this, e.getMessage());
        }
    }

    /**
     * 添加部门
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
        logger.debug("operaObj is = {}, delDo() doing, uuid = {}", id);
        try {
            baseService.delDo(id);
            write(ajaxReturn(true, "删除成功"));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, delDo is error, msg = {}", this, ex.getMessage());
            write(ajaxReturn(false, "删除失败"));
        }
    }

    /**
     * 根据ID去获取部门信息
     */
    public void getDo() {
        logger.debug("operaObj is = {}, getDo() doing, uuid = {}", this, id);
        try {
            T t = baseService.getDo(id);
            write(JSONObject.toJSONString(t));
        }catch (Exception ex) {
            logger.error("operaObj is = {}, getDo is error, msg = {}", this, ex.getMessage());
        }
    }

    /**
     * 更新部门信息
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
}
