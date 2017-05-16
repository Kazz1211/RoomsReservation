/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.service;

import com.mycompany.roomsreservation.dto.ReservationDTO;
import com.mycompany.roomsreservation.dao.ArrangementDAO;
import com.mycompany.roomsreservation.dao.ArrangementHibernateDAO;
import com.mycompany.roomsreservation.dao.ReservationDAO;
import com.mycompany.roomsreservation.dao.ReservationHibernateDAO;
import com.mycompany.roomsreservation.dao.RoomDAO;
import com.mycompany.roomsreservation.dao.RoomHibernateDAO;
import com.mycompany.roomsreservation.entity.Arrangement;
import com.mycompany.roomsreservation.entity.Reservation;
import com.mycompany.roomsreservation.entity.Room;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ja
 */
@Service
public class DataProviderService {

    private final ArrangementDAO arrangementDao;
    private final ReservationDAO reservationDao;
    private final RoomDAO roomDao;

    @Autowired
    public DataProviderService(ArrangementHibernateDAO arrangementDao,
            ReservationHibernateDAO reservationDao,
            RoomHibernateDAO roomDao) {
        this.arrangementDao = arrangementDao;
        this.reservationDao = reservationDao;
        this.roomDao = roomDao;
    }

    public void addReservation(ReservationDTO reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setFullName(reservationDto.getFullName());
        reservation.setEmail(reservationDto.getEmail());
        Date start = reservationDto.getStartDate();
        Date end = reservationDto.getEndDate();
        reservation.setStartDate(start);
        reservation.setEndDate(end);
        reservation.setCompany(reservationDto.getCompany());
        reservation.setIfAccepted(Boolean.FALSE);
        reservation.setRoom(roomDao.getRoom(reservationDto.getIdRoom()));
        reservation.setArrangement(arrangementDao.getArrangement(reservationDto.getIdArrangement()));
        reservationDao.saveReservation(reservation);
    }

    public List<Room> getRooms() {
        return roomDao.listRooms();
    }

    public void accept(Integer id) {
        Reservation r = reservationDao.getReservation(id);
        r.setIfAccepted(Boolean.TRUE);
        reservationDao.saveReservation(r);
    }

    public void delete(Integer id) {
        reservationDao.deleteReservation(reservationDao.getReservation(id));
    }

    public boolean checkIfRoomExists(int id) {
        return roomDao.getRoom(id) != null;
    }

    public List<Arrangement> getArrangements() {
        return arrangementDao.listArrangements();
    }

    public boolean checkIfArrangementExists(int id) {
        return arrangementDao.getArrangement(id) != null;
    }

    public int countPages(int pageSize, int size) {
        return (size / pageSize + 1);
    }

    public List<Reservation> listReservationsPage(Date start, Date end, int pageSize, int pageNumber) {
        if (start != null && end != null) {
            return reservationDao.listPageFromDateToDate(start, end, pageSize, pageNumber);
        } else {
            return reservationDao.listReservationsPage(pageSize, pageNumber);
        }
    }

    public List<Reservation> listRequestsPage(Date start, Date end, int pageSize, int pageNumber) {
        if (start != null && end != null) {
            return reservationDao.listRequestsPageFromDateToDate(start, end, pageSize, pageNumber);
        } else {
            return reservationDao.listRequestsPage(pageSize, pageNumber);
        }
    }

    public Integer countRequests(Date start, Date end) {
        if (start != null && end != null) {
            return reservationDao.countRequestsFromDateToDate(start, end);
        } else {
            return reservationDao.countRequests();
        }
    }

    public Integer countReservations(Date start, Date end) {
        if (start != null && end != null) {
            return reservationDao.countFromDateToDate(start, end);
        } else {
            return reservationDao.countReservations();
        }
    }
}
