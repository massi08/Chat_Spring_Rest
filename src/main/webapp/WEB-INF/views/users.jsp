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
<c:choose>
    <c:when test="${isUserListAlreadyPresent}">
        <h4>L'utilisateur <span class="added-user">${addedUser}</span> est déjà dans la liste.</h4>
        <h4>La liste d'utilisateur :</h4>
    </c:when>
    <c:otherwise>
        <h4>L'utilisateur ${addedUser} a bien été ajouté.</h4>
        <h4>La nouvelle liste d'utilisateur :</h4>
    </c:otherwise>
</c:choose>
<div class="users-list">
    <c:forEach var="user" items="${usersList}">
        <div class="user">
            <span> ${user} </span>
        </div>
    </c:forEach>
</div>
</body>
</html>
