package com.mycompany.roomsreservation.entity;
// Generated 2017-04-25 17:15:42 by Hibernate Tools 4.3.1

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Rooms generated by hbm2java
 */
@Entity
@Table(name = "ROOMS")
public class Room implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID_ROOM")
    private int idRoom;
    @Column(name = "ROOM_NAME")
    private String roomName;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Room() {
    }

    public Room(int idRoom) {
        this.idRoom = idRoom;
    }

    public Room(int idRoom, String roomName) {
        this.idRoom = idRoom;
        this.roomName = roomName;
    }

    public int getIdRoom() {
        return this.idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

}
