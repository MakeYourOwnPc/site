<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 22/06/2021
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MYOC-Registration</title>
    <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="customcss/general.css"/>
</head>
<body>
<script src="bootstrap/js/bootstrap.js" defer></script>
<script src="bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="jslibraries/jQuery.js"></script>

<div class="registration-box">
    <form action="/registration" method="put">
        <label for="firstname">Firstname</label>
        <input type="text" name="firstname" id="firstname">

        <label for="lastname">Lastname</label>
        <input type="text" name="lastname" id="lastname">

        <label for="email">Email</label>
        <input type="email" name="email" id="email">

        <label for="emailtest">Confirm Your Email</label>
        <input type="email" name="emailtest" id="emailtest">

        <label for="password">Password</label>
        <input type="password" name="password" id="password">

        <label for="password">Confirm Your Password</label>
        <input type="password" name="passwordtest" id="passwordtest">


    </form>
</div>
</body>
</html>
