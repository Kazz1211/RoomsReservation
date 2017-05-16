/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.roomsreservation.dto;

import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Ja
 */
public class ReservationDTO {

    @NotBlank(message = "Pole imię i nazwisko nie może być puste")
    @Size(min = 5, message = "Pole imię i nazwisko musi mieć długość co najmniej 5")
    @Length(max = 40)
    private String fullName;
    @NotBlank(message = "Email nie może być pusty")
    @Length(max = 30)
    @Size(min = 5, message = "Email musi mieć długość co najmniej 5")
    @Email(message = "Nieprawidłowy format")
    private String email;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Future(message="Data początkowa musi być w przyszłości")
    private Date startDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Future(message="Data końcowa musi być w przyszłości")
    private Date endDate;
    @NotBlank(message="Firma nie może być pusta")
    @Length(max = 30)
    private String company;
    @NotNull(message="Pole sala nie może być puste")
    @Min(value=1, message="Nie ma takiej sali")
    private Integer idRoom;
    @NotNull(message="Pole układ nie może być puste")
    @Min(value=1, message="Nie ma takiego układu")
    private Integer idArrangement;

    @AssertTrue(message = "Data końcowa nie może być przed początkową")
    public boolean isEndAfterStart() {
        return ifSecondDateNotBeforeFirst(startDate, endDate);
    }

    private boolean ifSecondDateNotBeforeFirst(Date first, Date second) {
        if(first!=null && second != null) return !second.before(first);
        else return true;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getIdArrangement() {
        return idArrangement;
    }

    public void setIdArrangement(Integer idArrangement) {
        this.idArrangement = idArrangement;
    }


}
