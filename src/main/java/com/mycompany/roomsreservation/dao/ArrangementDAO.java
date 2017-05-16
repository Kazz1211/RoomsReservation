/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.dao;

import com.mycompany.roomsreservation.entity.Arrangement;
import java.util.List;

/**
 *
 * @author Ja
 */
public interface ArrangementDAO {
    public Arrangement getForName(String name);
    public List<Arrangement> listArrangements();
    public List<Arrangement> listArrangementsPage(int pageSize, int pageNumber);
    public void saveArrangement(Arrangement a);
    public Arrangement getArrangement(int id);
    public void deleteArrangement(Arrangement a);
    public Integer countArrangements();
}
