/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.dao;

import com.mycompany.roomsreservation.entity.Reservation;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ja
 */
public interface ReservationDAO {
    public Integer countFromDateToDate(Date start, Date end);
    public Integer countRequestsFromDateToDate(Date start, Date end);
    public Integer countRequests();
    public List<Reservation> listFromDateToDate(Date start, Date end);
    public List<Reservation> listPageFromDateToDate(Date start, Date end, int pageSize, int pageNumber);
    public List<Reservation> listReservations();
    public List<Reservation> listReservationsPage(int pageSize, int pageNumber);
    public List<Reservation> listRequestsFromDateToDate(Date start, Date end);
    public List<Reservation> listRequestsPageFromDateToDate(Date start, Date end, int pageSize, int pageNumber);
    public List<Reservation> listRequests();
    public List<Reservation> listRequestsPage(int pageSize, int pageNumber);
    public void saveReservation(Reservation r);
    public Reservation getReservation(int id);
    public void deleteReservation(Reservation r);
    public Integer countReservations();
}
