<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.Chat.Modele.Message" %>
<jsp:useBean id="messages" class="com.Chat.Modele.GestionMessages" scope="session">
    <jsp:setProperty name="messages" property="servletContext" value="<%=request.getServletContext()%>"/>
</jsp:useBean>
<%
    String salon = request.getParameter("salon");
    String message = request.getParameter("message");
    String username = (String) session.getAttribute("username");

    if (messages.getMessageList(salon) == null) {
        messages.addSalon(salon);
    }
    if(messages.getUserList(salon).contains(username)) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            if (message != null || message != "") {
                Message msg = new Message(username, message);
                messages.addMessage(salon, msg);
            }
        }
    }

%>
