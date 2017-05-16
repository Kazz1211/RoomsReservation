<%-- 
    Document   : admin
    Created on : 2016-06-18, 12:24:42
    Author     : Ja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel administratora</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/main.css"/>">
    </head>
    <body>
        <div class='logo'></div><br />
        <div class='cont'>
            <div class='title'>
                <h1>Strona główna</h1>
            </div>
            <jsp:include page="/WEB-INF/includes/navigation.jsp" />
            <div class='con'>
                <h2>Możesz: </h2>
                <h2>1. Przejrzeć oczekujące i zaakceptowane rezerwacje<br /></h2>
                <h2>2. Usuwać rezerwacje<br /></h2>
                <h2>3. Akceptować oczekujące rezerwacje<br /></h2>
            </div>
        </div>
    </body>
</html>
