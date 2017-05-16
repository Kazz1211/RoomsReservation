<%-- 
    Document   : reserve
    Created on : 2016-06-18, 11:26:28
    Author     : Ja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rezerwuj</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/main.css"/>">
        <script src="<c:url value="/resources/js/valid_reservation.js"/>"></script>
    </head>
    <body>
        <div class='logo'></div><br />
        <div class='cont'>
            <div class='title'>
                <h1>Rezerwuj</h1>
            </div>
            <jsp:include page="/WEB-INF/includes/navigation.jsp" />
            <form:form id="reserve" method="POST" action="/RoomsReservation/reserve" modelAttribute="reservationDto">
                <c:if test="${pageContext.request.method=='POST'}"><form:errors path="*" cssClass="error"/><br/></c:if>
                    <fieldset>
                        <span class="label">Imię i nazwisko(*):</span><br/>
                    <form:input type='text' path='fullName' id="fullname" cssErrorClass="error"/><br/>
                    <span class="label">Email(*):</span><br/>
                    <form:input type='email' path='email' id="email" cssErrorClass="error"/><br/>
                </fieldset>
                <fieldset>
                    <legend>Dane sali:</legend>
                    <span class="label">Sala(*):</span><br/>
                    <form:select path='idRoom' cssErrorClass="error">
                        <c:forEach var="room" items="${rooms}">
                            <option value='${room.idRoom}'>${room.roomName}</option>
                        </c:forEach> 
                    </form:select><br/>
                    <span class="label">Układ Stołów(*):</span><br/>
                    <form:select path='idArrangement' cssErrorClass="error">
                        <c:forEach var="arrangement" items="${arrangements}">
                            <option value='${arrangement.idArrangement}'>${arrangement.arrangementName}</option>
                        </c:forEach>
                    </form:select><br/>
                    <span class="label">Data od:</span><br/>
                    <form:input type='text' value="yyyy-mm-dd" path='startDate' id="startDate" cssErrorClass="error"/><br/>
                    <span class="label">Data do:</span><br/>
                    <form:input type='text' value="yyyy-mm-dd" path='endDate' id="endDate" cssErrorClass="error"/><br/>
                </fieldset><br/>
                <fieldset>
                    <legend>Dane Firmy:</legend>
                    <span class="label">Nazwa(*):</span><br/>
                    <form:input type='text' path='company' id="company" cssErrorClass="error"/><br/>
                </fieldset><br/>
                <input type='submit' onclick="return valid_reservation()" value='Rezerwuj'/>
                <input type='hidden' name='js' value='false'/>
            </form:form>
            <br/>
            <div class='down'>(*) - pola wymagane <br/>
                 </div><br/>
        </div>
    </body>
</html>