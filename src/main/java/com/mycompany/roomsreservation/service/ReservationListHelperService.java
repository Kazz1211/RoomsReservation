/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.service;

import com.mycompany.roomsreservation.dto.SettingsDTO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.util.Pair;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 *
 * @author Ja
 */
@Service
public class ReservationListHelperService {
    private final SimpleDateFormat format;

    @Autowired
    public ReservationListHelperService(SimpleDateFormat format) {
        this.format = format;
    }
    
    
    public int checkAndSetPerPage(Model model, HttpSession session) {
        SettingsDTO settingsDto = new SettingsDTO();
        if (session.getAttribute("perPage") != null) {
            settingsDto.setPerPage((Integer) session.getAttribute("perPage"));
        } else {
            settingsDto.setPerPage(50);
        }
        model.addAttribute("settingsDto", settingsDto);
        return settingsDto.getPerPage();
    }

    public Pair<Date,Date> parseDatesFromDto(HttpSession session, SettingsDTO settingsDto) {
        Date start, end;
        try {
            start = format.parse(settingsDto.getFrom());
            end = format.parse(settingsDto.getTo());
        } catch (ParseException ex) {
            start=null;
            end=null;
        }
        session.setAttribute("from", start);
        session.setAttribute("to", end);
        return new Pair(start, end);
    }

    public Pair<Date,Date> getDatesFromSession(HttpSession session) {
        Date start=null, end=null;
        if (session.getAttribute("from") != null && session.getAttribute("to") != null) {
            start = (Date) session.getAttribute("from");
            end = (Date) session.getAttribute("to");
        }
        return new Pair(start,end);
    }
}
