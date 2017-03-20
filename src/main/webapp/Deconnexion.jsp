<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.setAttribute("username", null);
    session.setAttribute("salon", null);
    if(session.getAttribute("username") == null)
        response.sendRedirect("/");
    
%>
