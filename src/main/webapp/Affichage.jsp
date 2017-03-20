<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.Chat.Modele.Message" %>
<%@ page import="static javax.servlet.http.HttpServletResponse.SC_FOUND" %>
<%@ page import="static javax.servlet.http.HttpServletResponse.SC_NOT_MODIFIED" %>
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
    response.setHeader("Cache-Control", "public");
    response.setHeader("Pragma", "Pragma");
    String salon = request.getParameter("salon");
    String username = (String) session.getAttribute("username");
    Cookie cookie = null;
    Cookie[] cookies = request.getCookies();
    for(int i = 0; i< cookies.length; i++) {
        if (cookies[i].getName().equals("messageNumber")) {
            cookie = cookies[i];
        }
    }
    if (cookie == null) {
        cookie = new Cookie("messageNumber", Integer.toString(messages.getMessageNumber(salon)));
        response.addCookie(cookie);
    }
    out.println("<div class='message-status'>" +
            "Bonjour " +
            "<span class='username'>" + username + "</span>" +
            ", vous êtes sur le salon " + "<span class='salon'>" + salon + "</span>" +
            " contenant " + "<span class='nb-messages'>" + messages.getMessageNumber(salon) + "</span>" + " messages" +
            "</div>");
    out.println("<div class='messages-wrapper'>");
    for (Message msg : messages.getMessageList(salon)) {
        String message_class = " ";
        if (!msg.getUser().equals(username))
            message_class = " other-user ";
        out.println("<div class='received-message" + message_class + "z-depth-1'>" +
                "<span>" + msg.getUser() + "</span>" +
                "<p>" + msg.getMessage() + "</p>" +
                "</div>");
    }
    out.println("</div>");
    if ("GET".equalsIgnoreCase(request.getMethod())) {
        if (Integer.parseInt(cookie.getValue()) == messages.getMessageNumber(salon)) {
            //response.setStatus(SC_NOT_MODIFIED); //breaks auto-refresh
        }
    }
    cookie.setValue(Integer.toString(messages.getMessageNumber(salon)));
    response.addCookie(cookie);
%>
<script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="./js/message.js"></script>
</body>
</html>
