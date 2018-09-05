package com.ywx.erp.dao.impl;

import com.ywx.erp.dao.BaseDao;
import com.ywx.erp.entity.DepDo;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    private Class<T> entityClass;

    public BaseDaoImpl() {
        //获取对象对应的父类的类型
        Type baseDaoClass = this.getClass().getGenericSuperclass();
        //转成带参数，即泛型的类型
        ParameterizedType pType = (ParameterizedType)baseDaoClass;
        //获取参数泛型类型数组
        Type[] types = pType.getActualTypeArguments();
        //由于我们的BaseDao<T>的泛型参数里只有一个类型T，因此数组的第一个元素就是类型T的实际上的类型
        entityClass = (Class<T>)types[0];
        logger.info("{} is NewInstance", entityClass.getName());
    }

    /**
     * 查询
     * @param t1,t2
     * @return list
     */
    @Override
    public List<T> list(T t1, T t2, Object obj) {
        DetachedCriteria dc = getDetachedCriteria(t1, t2, obj);
        return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
    }

    /**
     * 分页查询
     * @param t1,t2,obj
     * @param firstIndex    起始索引
     * @param rows          返回行数
     * @return
     */
    @Override
    public List<T> listByPage(T t1, T t2, Object obj, int firstIndex, int rows) {
        DetachedCriteria dc = getDetachedCriteria(t1, t2, obj);
        List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(dc, firstIndex, rows);
        return list;
    }

    /**
     * 根据查询条件统计总条目数
     * @param t1,t2,obj
     * @return
     */
    @Override
    public Long getCount(T t1, T t2, Object obj) {
        DetachedCriteria dc = getDetachedCriteria(t1, t2, obj);
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>)this.getHibernateTemplate().findByCriteria(dc);
        return null != list ? list.get(0) : null;
    }

    /**
     * 根据T设置查询条件
     * @param t1,t2,obj
     * @return
     */
    protected DetachedCriteria getDetachedCriteria(T t1, T t2, Object obj) {
        return null;
    }

    /**
     * 添加
     * @param t
     */
    @Override
    public void addDo(T t) {
        System.out.println("this = " + this);
        System.out.println("temp = " + this.getHibernateTemplate());
        System.out.println("t = " + t);
        this.getHibernateTemplate().save(t);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delDo(long id) {
        T t = getDo(id);
        this.getHibernateTemplate().delete(t);
    }

    /**
     * 获取
     * @param id
     * @return
     */
    @Override
    public T getDo(long id) {
//        return this.getHibernateTemplate().get(entityClass, uuid);
        return (T)this.getHibernateTemplate().load(entityClass, id);
    }

    /**
     * 如果ID是Stirng类型
     * @param id
     * @return
     */
    @Override
    public T getDo(String id) {
        return (T)this.getHibernateTemplate().load(entityClass, id);
    }

    /**
     * 更新
     * @param t
     */
    @Override
    public void updateDo(T t) {
        this.getHibernateTemplate().update(t);
    }
}
