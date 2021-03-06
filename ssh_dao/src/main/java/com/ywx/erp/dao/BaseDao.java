package com.ywx.erp.dao;

import java.util.List;

public interface BaseDao<T> {

    List<T> list(T t1, T t2, Object obj);

    List<T> listByPage(T t1, T t2, Object obj, int firstIndex, int rows);

    Long getCount(T t1, T t2, Object obj);

    void addDo(T t);

    void delDo(int id);

    T getDo(int id);

    void updateDo(T t);

}
