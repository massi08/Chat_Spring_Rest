<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="/css/back_office.css"/>
    <title> Connexion </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
</head>
<body>
<a href="/admin.jsp">Page Admin</a>
<h4>Les messages</h4>
<div class="messages-wrapper z-depth-1">
    <%String usert = (String)session.getAttribute("username");%>
    <c:forEach var="message" items="${messagesList}">
        <div class="${(user != message.getUser()) ? 'received-message other-user z-depth-1' : 'received-message z-depth-1'}">
            <span>${message.getUser()} </span>
            <p> ${message.getMessage()} </p>
        </div>
    </c:forEach>
</div>
</body>
</html>
