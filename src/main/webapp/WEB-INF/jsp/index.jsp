<%-- 
    Document   : index
    Created on : 2016-06-18, 11:17:00
    Author     : Ja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Strona główna</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/main.css"/>">
    </head>
    <body>
        <div class='logo'></div><br />
        <div class='cont'>
            <div class='title'>
                <h1>Strona główna</h1>
            </div>
            <jsp:include page="/WEB-INF/includes/navigation.jsp" />
            <div id='class'><h2>Jeśli chcesz zarezerwować salę na konferencję trafiłeś w odpowiednie miejsce!</h2>
                <h2>Dane kontaktowe:<br /><br />
                </h2>
                <h3>tel. +48 111111111<br />
                    ul. Tamta 1, 00-000 Warszawa<br />
                    ktos@gmail.com <br />
                </h3>
            </div>
        </div>
    </body>
</html>
