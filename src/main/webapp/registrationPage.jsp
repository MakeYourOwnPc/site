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
<body class="default">
<script src="bootstrap/js/bootstrap.js" defer></script>
<script src="bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="jslibraries/jQuery.js"></script>

<div>
    <form name="registration" action="/registration" method="put" onsubmit="validateData()">
        <table  class="registration-box">
            <tr><td>
            <label for="firstname">Firstname</label><br>
                <span class="alert-info hidden"> FirstName not Inserted</span>

            </td><td>
            <input type="text" name="firstname" id="firstname">
                </td></tr>
            <tr><td>
                 <label for="lastname">LastName</label><br>
                <span class="alert-info hidden"> LastName not Inserted</span>
                <td>
            <input type="text" name="lastname" id="lastname">
            </td></tr>
            <tr><td>
            <label for="email">Email</label><br>
                <span class="alert-info hidden"> Incorrect Email</span>
            <td>
            <input type="email" name="email" id="email">
            </td></tr>
            <tr><td>
            <label for="emailtest">Confirm Your Email</label><br>
                <span class="alert-info hidden">Not The Same Email</span>
            <td>
            <input type="email" name="emailtest" id="emailtest">
            </td></tr>
            <tr><td>
            <label for="password">Password</label><br>
            <td>
            <input type="password" name="password" id="password">
            </td></tr>
            <tr><td>
                <label for="password">Confirm Your Password</label><br>
                <span class="alert-info hidden">Not The Same Password</span>
            <td>
                <input type="password" name="passwordtest" id="passwordtest">
            </td></tr>
            <tr><td>
                <input type="submit" value="Register Now">
            </td></tr>
        </table>


    </form>
</div>
</body>
<script>
    function validateForm(){
        var submitable =true
       if( document.forms["registration"]["firstname"].value==""){
           submitable =false;
           document.getElementById("firstname").
       }
    }
</script>
</html>
