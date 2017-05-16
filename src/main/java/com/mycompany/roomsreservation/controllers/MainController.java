/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.controllers;

import com.mycompany.roomsreservation.service.DataProviderService;
import com.mycompany.roomsreservation.dto.ReservationDTO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Ja
 */
@Controller
@RequestMapping("/")
public class MainController {

    private final DataProviderService dataProviderService;

    @Autowired
    public MainController(DataProviderService dataProviderService) {
        this.dataProviderService = dataProviderService;
    }

    @RequestMapping("/")
    public String main() {
        return "index";
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.GET)
    public String reserve(Model model) {
        model.addAttribute("arrangements", dataProviderService.getArrangements());
        model.addAttribute("rooms", dataProviderService.getRooms());
        model.addAttribute("reservationDto", new ReservationDTO());
        return "reserve";
    }

    @Secured("ROLE_GUEST")
    @RequestMapping("/login")
    public String login(Model model,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("message", "Niepoprawne dane logowania");
        }
        if (logout != null) {
            model.addAttribute("message", "Wylogowano poprawnie");
        }
        return "login";
    }

    @RequestMapping("/gallery")
    public String gallery() {
        return "gallery";
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public String addReservationPost(HttpServletRequest request,
            @ModelAttribute("reservationDto") @Valid ReservationDTO reservationDto,
            BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (dataProviderService.checkIfArrangementExists(reservationDto.getIdArrangement())) {
                if (dataProviderService.checkIfRoomExists(reservationDto.getIdRoom())) {
                    dataProviderService.addReservation(reservationDto);
                    return "add";
                }
            }
        }
        model.addAttribute("arrangements", dataProviderService.getArrangements());
        model.addAttribute("rooms", dataProviderService.getRooms());
        model.addAttribute("reservationDto", reservationDto);
        return "reserve";
    }
}
