<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Oups... Looks like something went wrong!</h1>
    <%--<h2>Exception occured at: </h2><fmt:formatDate value="${exception.date}" pattern="yyyy-MM-dd" />--%>
    <h2>Exception Message   : </h2>${exception.message}
    <p>
        Come back after a few minutes and check again!
    </p>
</body>
</html>
