<%-- 
    Document   : login
    Created on : 2016-06-18, 11:27:20
    Author     : Ja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zaloguj</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/main.css"/>">
        <script src="<c:url value="/resources/js/valid_login.js"/>"></script>
    </head>
    <body>
        <div class='logo'></div><br>
        <div class='cont'>
            <div class='title'>
                <h1>Zaloguj</h1>
            </div>
            <jsp:include page="/WEB-INF/includes/navigation.jsp" />
            <c:set var="loginUrl"><c:url value="/login"/></c:set>
            <form name='log' action='${loginUrl}' method='post'">
                <c:if test="${not empty message}">
                    <div class="error">${message}</div>
                </c:if>
                Login:<br>
                <input type='text' name='username'><br>
                Haslo:<br>
                <input type='password' name='password' ><br>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type='submit' onclick="return valid_login()" value='Zaloguj'>
                <input type='hidden' name='js' value='false'> <br>
            </form>
        </div>
    </body>
</html>