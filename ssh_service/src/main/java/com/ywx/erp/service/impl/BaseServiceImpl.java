package com.ywx.erp.service.impl;

import com.ywx.erp.common.PIOConstants;
import com.ywx.erp.common.PIOUtil;
import com.ywx.erp.dao.BaseDao;
import com.ywx.erp.service.BaseService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    protected static final Logger logger = LoggerFactory.getLogger(DepServiceImpl.class);

    private BaseDao<T> baseDao;
    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<T> list(T t1, T t2, Object obj) {
        return baseDao.list(t1, t2, obj);
    }

    @Override
    public List<T> listByPage(T t1, T t2, Object obj, int first, int rows) {
        return baseDao.listByPage(t1, t2, obj, first, rows);
    }

    @Override
    public Long getCount(T t1, T t2, Object obj) {
        return baseDao.getCount(t1, t2, obj);
    }

    @Override
    public void addDo(T t) {
        baseDao.addDo(t);
    }

    @Override
    public void delDo(long id) {
        baseDao.delDo(id);
    }

    @Override
    public T getDo(long id) {
        return baseDao.getDo(id);
    }

    @Override
    public T getDo(String id) {
        return baseDao.getDo(id);
    }

    @Override
    public void updateDo(T t) {
        baseDao.updateDo(t);
    }

    @Override
    public void export(OutputStream os, T t) throws Exception {
        HSSFWorkbook wk = new HSSFWorkbook();               //创建excel文件
        HSSFSheet sheet = wk.createSheet(PIOConstants.SHEETNAME);
        List<T> list = baseDao.list(t, null, null);

        PIOUtil.export(sheet, list, t.getClass());
        PIOUtil.closeStream(os, wk);
    }

    @Override
    public void importData(File file, String fileFileName, Class clazz) throws IOException, ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        PIOUtil.importData(file, fileFileName, clazz);
    }
}
