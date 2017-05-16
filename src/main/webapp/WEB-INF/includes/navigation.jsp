<%-- any content can be specified here e.g.: --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>


<nav class='nav'>
    <a href="/RoomsReservation/">Główna</a><br>
    <a href="/RoomsReservation/reserve">Zarezerwuj</a><br>
    <a href="/RoomsReservation/gallery">Galeria</a><br>
    <sec:authorize access="hasRole('ROLE_GUEST')">
        <a href="/RoomsReservation/login">Zaloguj</a><br>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="/RoomsReservation/admin/">Panel administratora</a><br>
        <a href="/RoomsReservation/admin/requests/1">Lista zgłoszeń</a><br>
        <a href="/RoomsReservation/admin/list/1">Lista rezerwacji</a><br>
        <a href="javascript:formSubmit()">Wyloguj</a><br>
        <c:url value="/logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>
    </sec:authorize>
</nav> 