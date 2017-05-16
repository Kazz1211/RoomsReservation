<%-- 
    Document   : admin
    Created on : 2016-06-18, 12:24:42
    Author     : Ja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zgłoszenia</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/main.css"/>">
    </head>
    <body>
        <div class='logo'></div><br />
        <div class='cont'>
            <div class='title'>
                <h1>Zgłoszenia</h1>
            </div>
            <jsp:include page="/WEB-INF/includes/navigation.jsp" />
            <div class='tablecon'>
                <form:form method="POST" action="/RoomsReservation/admin/requests" modelAttribute="settingsDto">
                    <span class="label">Ilość na stronę</span>
                    <form:input type='number' min='1' path='perPage' value='${settingsDto.perPage}'/>
                    <c:if test="${pageContext.request.method=='POST'}"><form:errors path="perPage" cssClass="error"/></c:if>
                        <span class="label">Od</span>
                    <form:input type='text' path='from'/>
                    <span class="label">Do</span>
                    <form:input type='text' path='to'/>
                    <input type='submit' value='Zatwierdź'>
                </form:form><br>
                <div class="table">
                    <div class="headerrow">
                        <span class="cell">Id</span>
                        <span class="cell">Imię i nazwisko</span>
                        <span class="cell">email</span>
                        <span class="cell">Sala</span>
                        <span class="cell">układ</span>
                        <span class="cell">od</span>
                        <span class="cell">do</span>
                        <span class="cell">nazwa firmy</span>
                        <span class="cell"></span>
                        <span class="cell"></span>
                    </div>
                    <c:forEach var="request" items="${requests}">
                        <div class="row">
                            <span class="cell">${request.idReservation}</span>
                            <span class="cell">${request.fullName}</span>
                            <span class="cell">${request.email}</span>
                            <span class="cell">${request.room.roomName}</span>
                            <span class="cell">${request.arrangement.arrangementName}</span>
                            <span class="cell">${request.startDate}</span>
                            <span class="cell">${request.endDate}</span>
                            <span class="cell">${request.company}</span>
                            <span class="cell"><a href="/RoomsReservation/admin/accept/${request.idReservation}">Akceptuj</a></span>
                            <span class="cell"><a href="/RoomsReservation/admin/reject/${request.idReservation}">Odrzuć</a></span>
                        </div>    
                    </c:forEach>         
                </div>
                <div class="pagesnav">
                    <c:if test="${page > 1}">
                        <a href="/RoomsReservation/admin/requests/${page-1}">Poprzednia</a>
                    </c:if>
                    <select onchange="location.href = this.value">
                        <option value=''></option>
                        <c:forEach var="i" begin="1" end="${pagesNumber}">
                            <option value='/RoomsReservation/admin/requests/${i}'>${i}</option>
                        </c:forEach> 
                    </select>
                    <c:if test="${page < pagesNumber}">
                        <a href="/RoomsReservation/admin/requests/${page+1}">Następna</a>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
