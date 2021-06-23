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
    <form name="registration" action="/registration" method="post" onsubmit="return validateData()">
        <table  class="registration-box">
            <tr><td>
            <label for="firstname">Firstname</label><br>


            </td><td>
            <input type="text" name="firstname" id="firstname" required>
                <span id="firstname-alert" class="alert-info hidden"> FirstName not Inserted</span>
                </td></tr>
            <tr><td>
                 <label for="lastname">LastName</label><br>
                <td>
            <input type="text" name="lastname" id="lastname" required>
                <span  id="lastname-alert" class="alert-info hidden"> LastName not Inserted</span>

            </td></tr>
            <tr><td>
            <label for="email">Email</label><br>

            <td>
            <input type="email" name="email" id="email" onfocusout="existingEmail()" required>
                <span id="email-alert" class="alert-info hidden" > Incorrect Email</span>
            </td></tr>
            <tr><td>
            <label for="emailtest">Confirm Your Email</label><br>

            <td>
            <input type="email" name="emailtest" id="emailtest" required>
                <span id="emailtest-alert"class="alert-info hidden">Not The Same Email</span>
            </td></tr>
            <tr><td>
            <label for="password">Password</label><br>
            <td>
            <input id="password" type="password" name="password" id="password" required>
                <span id="password-alert" class="alert-info hidden" >Password Incorrect</span>
            </td></tr>
            <tr><td>
                <label for="passwordtest">Confirm Your Password</label><br>

            <td>
                <input type="password" name="passwordtest" id="passwordtest" required>
                <span id="passwordtest-alert" class="alert-info hidden" >Not The Same Password</span>
            </td></tr>
            <tr><td>
                <input type="submit" value="Register Now">
            </td></tr>
        </table>


    </form>
</div>
</body>
<script>
    function validateData(){
        var submitable =true;
        var emailvalid=true;
        var passwordvalid=true;

        var email=document.getElementById("email");
        var emailtest=document.getElementById("emailtest");

        var password=document.getElementById("password");
        var passwordtest=document.getElementById("passwordtest");

       if(!document.getElementById("firstname").checkValidity){
           submitable =false;
           document.getElementById("firstname-alert").classList.remove("hidden");

       }
       else document.getElementById("firstname-alert").classList.add("hidden");

        if(!document.getElementById("lastname").checkValidity){
            submitable =false;
            document.getElementById("lastname-alert").classList.remove("hidden");

        }
        else document.getElementById("lastname-alert").classList.add("hidden");

        if(!email.checkValidity){
            submitable =false;
            emailvalid=false;
            document.getElementById("email-alert").classList.remove("hidden");

        }
        else document.getElementById("email-alert").classList.add("hidden");

        if(emailvalid&&email.value!=emailtest.value) {
            submitable = false;
            document.getElementById("emailtest-alert").innerText="Incorrect Email";
            document.getElementById("emailtest-alert").classList.remove("hidden");
        }
        else document.getElementById("emailtest-alert").classList.add("hidden");

        if(!document.getElementById("password").checkValidity){
            submitable =false;
            passwordvalid=false;
            document.getElementById("password-alert").classList.remove("hidden");

        }
        else document.getElementById("password-alert").classList.add("hidden");

        if(passwordvalid&&passwordtest.value!=password.value){
            submitable =false;
            document.getElementById("passwordtest-alert").classList.remove("hidden");
        }
        else document.getElementById("passwordtest-alert").classList.add("hidden");

        return submitable;
    }

    function existingEmail(){
        var xhttp = new XMLHttpRequest();
        var emailalert =document.getElementById("email-alert");
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseText=="true"){
                    emailalert.innerText="Email Already Taken";
                    emailalert.classList.remove("hidden");
                }
                else
                    emailalert.classList.add("hidden");
            }
        };
        xhttp.open("POST", "/MYOPSite_war_exploded/emailispresent", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("email="+document.getElementById("email").value);
    }
</script>
</html>
