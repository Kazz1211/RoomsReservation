/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.dao;

import com.mycompany.roomsreservation.entity.Arrangement;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ja
 */
public class ArrangementHibernateDAO extends HibernateDAO<Arrangement> implements ArrangementDAO {

    @Autowired
    public ArrangementHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Transactional
    @Override
    public Arrangement getForName(String name) {
        Session session = getSession();
        Arrangement a = (Arrangement) session.
                createCriteria(Arrangement.class).
                add(Restrictions.like("arrangementName", name)).
                uniqueResult();
        session.close();
        return a;
    }

    @Override
    public List<Arrangement> listArrangements() {
        return list();
    }

    @Override
    public List<Arrangement> listArrangementsPage(int pageSize, int pageNumber) {
        return listPage(pageSize, pageNumber);
    }

    @Override
    public void saveArrangement(Arrangement a) {
        saveOrUpdate(a);
    }

    @Override
    public Arrangement getArrangement(int id) {
        return getById(id);
    }

    @Override
    public void deleteArrangement(Arrangement a) {
        delete(a);
    }

    @Override
    public Integer countArrangements() {
        return count();
    }

    

}
