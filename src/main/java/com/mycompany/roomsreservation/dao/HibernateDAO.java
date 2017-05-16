/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.dao;

import com.mycompany.roomsreservation.entity.Room;
import com.mycompany.roomsreservation.entity.User;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

/**
 *
 * @author Ja
 * @param <T>
 */
public abstract class HibernateDAO<T> {

    private final SessionFactory sessionFactory;

    private final Class<? extends T> type;

    public HibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    @Transactional
    public T getById(int id) {
        Session session = getSession();
        T t= (T) session.get(type, id);
        session.close();
        return t;
    }

    @Transactional
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    @Transactional
    public void delete(T entity) {
        Session session = getSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    @Transactional
    public List<T> list() {
        Session session = getSession();
        List<T> list = session.createCriteria(type).list();
        session.close();
        return list;
    }

    @Transactional
    public Integer count() {
        Session session = getSession();
        Integer count = (Integer) session.createCriteria(type).setProjection(Projections.rowCount()).uniqueResult();
        session.close();
        return count;
    }

    @Transactional
    public List<T> listPage(int pageSize, int pageNumber) {
        Session session = getSession();
        List<T> list = session.createCriteria(type).setMaxResults(pageSize).setFirstResult(pageSize*(pageNumber-1)).list();
        session.close();
        return list;
    }
}
