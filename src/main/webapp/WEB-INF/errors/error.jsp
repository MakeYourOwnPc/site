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
   String requestUri=(String) request.getAttribute("javax.servlet.error.request_uri");
%>
<html>
<head>
    <title>MYOP-<%=statusCode%></title>
    <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="/MYOPSite_war_exploded/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/MYOPSite_war_exploded/customcss/general.css"/>
</head>
<script src="bootstrap/js/bootstrap.js" defer></script>
<script src="bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="jslibraries/jQuery.js"></script>
<script src="jslibraries/utilities.js"></script>
<body>
<div class="fullHeightFooter">
<h1>Error Type:<br><%=exceptionType%></h1>
<h1>Error Code:<br><%=statusCode%></h1>
<h1>Servlet who gived the error:<br><%=servletName%></h1>
<h1>The Uri who caused the error:<br><%=requestUri%></h1>
<jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</div>
</body>
</html>
