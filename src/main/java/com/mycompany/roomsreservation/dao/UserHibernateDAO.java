/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.dao;

import com.mycompany.roomsreservation.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Ja
 */
public class UserHibernateDAO extends HibernateDAO<User> implements UserDAO {

    @Autowired
    public UserHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User findForUsername(String username) {
        Session session=getSession();
        User u = (User)session.
                createCriteria(User.class).
                add(Restrictions.like("username", username)).
                uniqueResult();
        session.close();
        return u;
    }
    @Override
    public void saveUser(User u) {
        this.saveOrUpdate(u);
    }
}
