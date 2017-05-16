<%-- 
    Document   : gallery
    Created on : 2016-06-18, 11:26:56
    Author     : Ja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Galeria</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/main.css"/>">
    </head>
    <body>
        <div class='logo'></div><br />
        <div class='cont'>
            <div class='title'>
                <h1>Galeria</h1>
            </div>
            <jsp:include page="/WEB-INF/includes/navigation.jsp" />
            <script type="text/javascript" language="JavaScript">
                function showPhoto(p1, p2, p3)
                {
                    document.getElementById(p2).style.display = "none";
                    document.getElementById(p3).style.display = "none";
                    document.getElementById(p1).style.display = "block";
                }
            </script>
            <p>
                <a href="#" onclick="showPhoto('photo1', 'photo2', 'photo3');">1</a>
                <a href="#" onclick="showPhoto('photo2', 'photo1', 'photo3');">2</a>
                <a href="#" onclick="showPhoto('photo3', 'photo2', 'photo1');">3</a>
            </p>

            <div id='photo1' >
                <a href="#" onclick="showPhoto('photo3', 'photo2', 'photo1');">Poprzedni</a>
                <img src="<c:url value="/resources/gallery/1.jpg"/>"/>
                <a href="#" onclick="showPhoto('photo2', 'photo1', 'photo3');">Następny</a>
            </div>
            <div id='photo2' >
                <a href="#" onclick="showPhoto('photo1', 'photo2', 'photo3');">Poprzedni</a>
                <img src="<c:url value="/resources/gallery/2.jpg"/>"/>
                <a href="#" onclick="showPhoto('photo3', 'photo1', 'photo2');">Następny</a>
            </div>
            <div id='photo3' >
                <a href="#" onclick="showPhoto('photo2', 'photo3', 'photo1');">Poprzedni</a>
                <img src="<c:url value="/resources/gallery/3.jpg"/>"/>
                <a href="#" onclick="showPhoto('photo1', 'photo2', 'photo3');">Następny</a>
            </div>
        </div>
    </body>
</html>
