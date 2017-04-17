package com.trackmycalorie.dao.impl;

import com.trackmycalorie.dao.api.crud.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import java.io.Serializable;

public class BaseDaoImpl<T extends Serializable, PK extends Serializable> implements Dao<T, PK> {

    private final Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    public BaseDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) {
        getSession().persist(entity);
    }

    @Override
    public T save(T entity) {
        return (T) getSession().merge(entity);
    }

    @Override
    public void remove(T entity) {
        getSession().delete(entity);
    }

    @Override
    public T find(PK pk) {
        T entity = getSession().get(entityClass, pk);
        if (entity == null) {
            throw new NoResultException(String.format("No result found for {} with key {}", entityClass.getName(), pk));
        }
        return getSession().get(entityClass, pk);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
