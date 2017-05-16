/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.dao;

import com.mycompany.roomsreservation.entity.Reservation;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ja
 */
public class ReservationHibernateDAO extends HibernateDAO<Reservation> implements ReservationDAO {

    @Autowired
    public ReservationHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    @Transactional
    public Integer countFromDateToDate(Date start, Date end) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addAcceptedRestriction(criteria);
        addFromDateToDateRestriction(start, end, criteria);
        setCount(criteria);
        Integer count = ((Long) criteria.uniqueResult()).intValue();
        session.close();
        return count;
    }

    @Override
    @Transactional
    public List<Reservation> listFromDateToDate(Date start, Date end) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addAcceptedRestriction(criteria);
        addFromDateToDateRestriction(start, end, criteria);
        List<Reservation> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Reservation> listPageFromDateToDate(Date start, Date end, int pageSize, int pageNumber) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addAcceptedRestriction(criteria);
        addFromDateToDateRestriction(start, end, criteria);
        setPageParameters(pageSize, pageNumber, criteria);
        List<Reservation> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public List<Reservation> listReservations() {
        Session session = getSession();
        Criteria criteria = getSession().createCriteria(Reservation.class);
        addAcceptedRestriction(criteria);
        List<Reservation> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public List<Reservation> listReservationsPage(int pageSize, int pageNumber) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addAcceptedRestriction(criteria);
        setPageParameters(pageSize, pageNumber, criteria);
        List<Reservation> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public void saveReservation(Reservation r) {
        saveOrUpdate(r);
    }

    @Override
    public Reservation getReservation(int id) {
        return getById(id);
    }

    @Override
    public void deleteReservation(Reservation r) {
        delete(r);
    }

    @Override
    public Integer countReservations() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addAcceptedRestriction(criteria);
        setCount(criteria);
        Integer count = ((Long) criteria.uniqueResult()).intValue();
        session.close();
        return count;
    }

    @Override
    @Transactional
    public List<Reservation> listRequestsFromDateToDate(Date start, Date end) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addNotAcceptedRestriction(criteria);
        addFromDateToDateRestriction(start, end, criteria);
        List<Reservation> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Reservation> listRequestsPageFromDateToDate(Date start, Date end, int pageSize, int pageNumber) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addNotAcceptedRestriction(criteria);
        addFromDateToDateRestriction(start, end, criteria);
        setPageParameters(pageSize, pageNumber, criteria);
        List<Reservation> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Reservation> listRequests() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addNotAcceptedRestriction(criteria);
        List<Reservation> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public List<Reservation> listRequestsPage(int pageSize, int pageNumber) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addNotAcceptedRestriction(criteria);
        setPageParameters(pageSize, pageNumber, criteria);
        List<Reservation> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    @Transactional
    public Integer countRequestsFromDateToDate(Date start, Date end) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addNotAcceptedRestriction(criteria);
        addFromDateToDateRestriction(start, end, criteria);
        setCount(criteria);
        Integer count = ((Long) criteria.uniqueResult()).intValue();
        session.close();
        return count;
    }

    @Override
    @Transactional
    public Integer countRequests() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(Reservation.class);
        addNotAcceptedRestriction(criteria);
        setCount(criteria);
        Integer count = ((Long) criteria.uniqueResult()).intValue();
        session.close();
        return count;
    }

    private void setPageParameters(int pageSize, int pageNumber, Criteria criteria) {
        criteria.setMaxResults(pageSize).setFirstResult(pageSize * (pageNumber - 1));
    }

    private void setCount(Criteria criteria) {
        criteria.setProjection(Projections.rowCount());
    }

    private void addFromDateToDateRestriction(Date start, Date end, Criteria criteria) {
        criteria.add(Restrictions.or(Restrictions.between("startDate", start, end), Restrictions.between("endDate", start, end)));
    }

    private void addNotAcceptedRestriction(Criteria criteria) {
        criteria.add(Restrictions.eq("ifAccepted", Boolean.FALSE));
    }

    private void addAcceptedRestriction(Criteria criteria) {
        criteria.add(Restrictions.eq("ifAccepted", Boolean.TRUE));
    }
}
