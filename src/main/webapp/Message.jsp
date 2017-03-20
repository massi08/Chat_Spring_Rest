<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Chat.Modele.Message" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.Chat.Modele.GestionMessages" %>

<jsp:useBean id="messages" class="com.Chat.Modele.GestionMessages" scope="session">
    <jsp:setProperty name="messages" property="servletContext" value="<%=request.getServletContext()%>"/>
</jsp:useBean>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/interface.css"/>
</head>
<body class="frame-body">
<%
    if (response.getStatus() == 500) {
        response.sendRedirect("index.html");
    }
    response.setHeader("refresh", "5; url=Message.jsp");
    String salon = "";
    String message = "";
    message = request.getParameter("usermessage");
    salon = (String) session.getAttribute("salon");
    if (salon == null) {
        salon = "";
        message = "";
    }
    String username = (String) session.getAttribute("username");
%>
<jsp:include page="Stockage.jsp">
    <jsp:param name="message" value="<%=message%>"/>
    <jsp:param name="salon" value="<%=salon%>"/>
</jsp:include>

<jsp:forward page="Affichage.jsp">
    <jsp:param name="salon" value="<%=salon%>"/>
    <jsp:param name="username" value="<%=username%>"/>
</jsp:forward>

<script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="./js/message.js"></script>
</body>
</html>