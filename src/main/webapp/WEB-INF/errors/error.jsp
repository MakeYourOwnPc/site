<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 22/06/2021
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   Throwable exceptionType = (Throwable) request.getAttribute("javax.servlet.error.exception");
   Integer statusCode=(Integer) request.getAttribute("javax.servlet.error.status_code");
   String servletName= (String) request.getAttribute("javax.servlet.error.servlet_name");
   String requestUri=(String) request.getAttribute("javax.servlet.error.request_uri")
%>
<html>
<head>
    <title>MYOP-<%=statusCode%></title>
</head>
<body>
<h1><%=exceptionType%></h1>
<h1><%=statusCode%></h1>
<h1><%=servletName%></h1>
<h1><%=requestUri%></h1>
</body>
</html>
