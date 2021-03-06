package com.ywx.erp.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BaseService<T> {

    List<T> list(T t1, T t2, Object obj);

    List<T> listByPage(T t1, T t2, Object obj, int first, int rows);

    Long getCount(T t1, T t2, Object obj);

    void addDo(T t);

    void delDo(int id);

    T getDo(int id);

    void updateDo(T t);

    void export(OutputStream os, T t) throws Exception;

    void importData(File file, String fileFileName, Class clazz) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
