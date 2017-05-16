/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.dao;

import com.mycompany.roomsreservation.entity.Room;
import java.util.List;

/**
 *
 * @author Ja
 */
public interface RoomDAO {
    public Room getForName(String name);
    public List<Room> listRooms();
    public List<Room> listRoomsPage(int pageSize, int pageNumber);
    public void saveRoom(Room r);
    public Room getRoom(int id);
    public void deleteRoom(Room r);
    public Integer countRooms();
}
