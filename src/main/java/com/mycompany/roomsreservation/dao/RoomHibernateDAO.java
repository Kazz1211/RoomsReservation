/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.dao;

import com.mycompany.roomsreservation.entity.Room;
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
public class RoomHibernateDAO extends HibernateDAO<Room> implements RoomDAO {

    @Autowired
    public RoomHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Transactional
    @Override
    public Room getForName(String name) {
        Session session = getSession();
        Room r = (Room) session.
                createCriteria(Room.class).
                add(Restrictions.like("roomName", name)).
                uniqueResult();
        session.close();
        return r;
    }

    @Override
    public List<Room> listRooms() {
        return list();
    }

    @Override
    public List<Room> listRoomsPage(int pageSize, int pageNumber) {
        return listPage(pageSize, pageNumber);
    }

    @Override
    public void saveRoom(Room r) {
        saveOrUpdate(r);
    }

    @Override
    public Room getRoom(int id) {
        return getById(id);
    }

    @Override
    public void deleteRoom(Room r) {
        delete(r);
    }

    @Override
    public Integer countRooms() {
        return count();
    }
}
