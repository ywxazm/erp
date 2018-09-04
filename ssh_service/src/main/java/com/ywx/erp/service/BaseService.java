package com.ywx.erp.service;

import java.io.OutputStream;
import java.util.List;

public interface BaseService<T> {

    List<T> list(T t1, T t2, Object obj);

    List<T> listByPage(T t1, T t2, Object obj, int first, int rows);

    Long getCount(T t1, T t2, Object obj);

    void addDo(T t);

    void delDo(long id);

    T getDo(long id);

    T getDo(String id);

    void updateDo(T t);

    void export(OutputStream os, T t) throws Exception;

}
