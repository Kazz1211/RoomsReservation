/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.controllers;

import com.mycompany.roomsreservation.service.DataProviderService;
import com.mycompany.roomsreservation.dto.SettingsDTO;
import com.mycompany.roomsreservation.entity.Reservation;
import com.mycompany.roomsreservation.service.ReservationListHelperService;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ja
 */
@Controller
@RequestMapping("/admin/")
public class AdminController {

    private final DataProviderService dataProviderService;
    private final ReservationListHelperService rlhs;

    @Autowired
    public AdminController(DataProviderService dataProviderService,
            ReservationListHelperService rlhs) {
        this.dataProviderService = dataProviderService;
        this.rlhs = rlhs;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/")
    public String adminPanel() {
        return "admin";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/accept/{id}")
    public String accept(@PathVariable("id") int id) {
        dataProviderService.accept(id);
        return "redirect:/admin/requests/1";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/reject/{id}")
    public String reject(@PathVariable("id") int id) {
        dataProviderService.delete(id);
        return "redirect:/admin/requests/1";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        dataProviderService.delete(id);
        return "redirect:/admin/list/1";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/requests/{page}", method = RequestMethod.GET)
    public String requests(@PathVariable("page") Integer page, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        int perPage = rlhs.checkAndSetPerPage(model, session);
        Pair<Date, Date> pair = rlhs.getDatesFromSession(session);
        Date start = pair.getKey(), end = pair.getValue();
        int pagesNumber = dataProviderService.countPages(perPage, dataProviderService.countRequests(start, end));
        if (pagesNumber >= page && page > 0) {
            List<Reservation> requests = dataProviderService.listRequestsPage(start, end, perPage, page);
            model.addAttribute("page", page);
            model.addAttribute("requests", requests);
            model.addAttribute("pagesNumber", pagesNumber);
            return "requests";
        } else {
            return "error_404";
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    public String requestsChanged(HttpServletRequest request,
            @ModelAttribute("settingsDto") @Valid SettingsDTO settingsDto,
            BindingResult result, Model model) {
        model.addAttribute("settingsDto", settingsDto);
        Date start = null, end = null;
        if (!result.hasErrors()) {
            Pair<Date, Date> pair = rlhs.parseDatesFromDto(request.getSession(), settingsDto);
            start = pair.getKey();
            end = pair.getValue();
        }
        List<Reservation> requests = dataProviderService.listRequestsPage(start, end, settingsDto.getPerPage(), 1);
        model.addAttribute("requests", requests);
        model.addAttribute("page", 1);
        model.addAttribute("pagesNumber",
                dataProviderService.countPages(settingsDto.getPerPage(),
                        dataProviderService.countRequests(start, end)));
        return "requests";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public String list(@PathVariable("page") Integer page, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        int perPage = rlhs.checkAndSetPerPage(model, session);
        Pair<Date, Date> pair = rlhs.getDatesFromSession(session);
        Date start = pair.getKey(), end = pair.getValue();
        int pagesNumber = dataProviderService.countPages(perPage, dataProviderService.countReservations(start, end));
        if (pagesNumber >= page && page > 0) {
            List<Reservation> reservations = dataProviderService.listReservationsPage(start, end, perPage, page);
            model.addAttribute("page", page);
            model.addAttribute("reservations", reservations);
            model.addAttribute("pagesNumber", pagesNumber);
            return "reservation_list";
        } else {
            return "error_404";
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String listChanged(HttpServletRequest request,
            @ModelAttribute("settingsDto") @Valid SettingsDTO settingsDto,
            BindingResult result, Model model) {
        model.addAttribute("settingsDto", settingsDto);
        Date start = null, end = null;
        if (!result.hasErrors()) {
            Pair<Date, Date> pair = rlhs.parseDatesFromDto(request.getSession(), settingsDto);
            start = pair.getKey();
            end = pair.getValue();
        }
        List<Reservation> reservations = dataProviderService.listReservationsPage(start, end, settingsDto.getPerPage(), 1);
        model.addAttribute("reservations", reservations);
        model.addAttribute("page", 1);
        model.addAttribute("pagesNumber",
                dataProviderService.countPages(settingsDto.getPerPage(),
                        dataProviderService.countReservations(start, end)));
        return "reservation_list";
    }

}
