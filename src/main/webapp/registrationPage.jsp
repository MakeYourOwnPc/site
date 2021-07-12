<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 22/06/2021
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<% response.addHeader("Set-Cookie", "Secure; SameSite=strict");%>
<%String referer=request.getHeader("referer");%>

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
<script src="jslibraries/utilities.js"></script>



<div class="fullHeightFooter">
<div class="centered-box">
    <div class="box-container fullHeightList">
    <h1>Registration Form</h1>
    <form name="registration" action="/MYOPSite_war_exploded/registration" method="post" onsubmit="return validateData()">
        <input type="hidden" name="referer" value="<%=referer%>">
        <table  class="User-box">
            <tr><td>
            <label for="firstname">Firstname</label>
            </td><td>
            <input type="text" name="firstname" id="firstname"  required>
                <span id="firstname-alert" class="alert-info " hidden> FirstName not Inserted</span>
                </td></tr>
            <tr><td>
                 <label for="lastname">LastName</label>
                <td>
            <input type="text" name="lastname" id="lastname"  required>
                <tr>
            <td></td>
            <td>
                <span  id="lastname-alert" class="alert-info " hidden> LastName not Inserted</span>
            </td>
        </tr>


            </td></tr>
            <tr><td>
            <label for="email">Email</label>

            <td>
            <input type="email" name="email" id="email" onfocusout="existingEmail()"  required>
                <span id="email-alert" class="alert-info " hidden> Incorrect Email</span>
            </td></tr>
            <tr><td>
            <label for="emailtest">Confirm Email</label>

            <td>
            <input type="email" name="emailtest" id="emailtest"  required>
                <span id="emailtest-alert" class="alert-info " hidden>Not The Same Email</span>
            </td></tr>
            <tr><td>
            <label for="password">Password</label>
            <td>
            <input id="password" type="password" name="password" id="password" onkeyup="testPassword(value)"  required>
                <span id="password-alert" class="alert-info " hidden>Password Not Inserted</span>
            </td></tr>
            <tr><td>
                <label for="passwordtest">Confirm Password</label>

            <td>
                <input type="password" name="passwordtest" id="passwordtest" required>
                <span id="passwordtest-alert" class="alert-info" hidden>Not The Same Password</span>
            </td></tr>
            <tr>
               <td>
               </td>
            </tr>
            <tr><td>
                <input class="btn active" type="submit" value="Register Now" id="submit-registration">
            </td></tr>
        </table>

    </form>
        <p style="text-align: left">
            The Password Must Contain:<br>
            -Between 8 And 16 Characters<br>
            -At Least One Special Character Between &%#.-_<br>
            -At Least An Uppercase And An Lowercase character<br>
            -At Least A Number
        </p>
    </div>
</div>
</div>
<jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</body>

<script>


    function validateData(){
        let submitable = true;
        let emailvalid=true;
        let passwordvalid=true;

        let email=document.getElementById("email");
        let emailtest=document.getElementById("emailtest");

        let password=document.getElementById("password");
        let passwordtest=document.getElementById("passwordtest");

       if(!document.getElementById("firstname").checkValidity){
           submitable =false;
           document.getElementById("firstname-alert").hidden=false;

       }
       else document.getElementById("firstname-alert").hidden=true;

        if(!document.getElementById("lastname").checkValidity){
            submitable =false;
            document.getElementById("lastname-alert").hidden=false;

        }
        else document.getElementById("lastname-alert").hidden=true;

        if(!email.checkValidity){
            submitable =false;
            emailvalid=false;
            document.getElementById("email-alert").hidden=false;

        }
        else document.getElementById("email-alert").hidden=true;

        if(emailvalid&&email.value!=emailtest.value) {
            submitable = false;
            document.getElementById("emailtest-alert").innerText="Incorrect Email";
            document.getElementById("emailtest-alert").hidden=false;
        }
        else document.getElementById("emailtest-alert").hidden=true;
        let passAlert= document.getElementById("password-alert");
        if(!document.getElementById("password").checkValidity){
            submitable =false;
            passwordvalid=false;
            passAlert.innerText="Password Not Inserted";
            passAlert.hidden=false;
        }
        else passAlert.hidden=true;

        if(passwordvalid&&passwordtest.value!=password.value){
            submitable =false;
            document.getElementById("passwordtest-alert").hidden=false;
        }
        else document.getElementById("passwordtest-alert").hidden=true;

        return submitable;
    }

    function existingEmail(){
        let xhttp = new XMLHttpRequest();
        let emailalert = document.getElementById("email-alert");
        let submit = document.getElementById("submit-registration");
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseText=="true"){
                    emailalert.innerText="Email Already Present";
                    submit.disabled=true;
                    emailalert.hidden=false;
                    console.log("email rejected");
                }
                else
                    submit.disabled=false;
                    emailalert.hidden=true;
            }
        };
        xhttp.open("POST", "/MYOPSite_war_exploded/emailispresent", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("email="+document.getElementById("email").value);
    }
    function testPassword(password){

        let regex=new RegExp("^(?=.*[a-z])(?=.*\\d)(?=.*[@#$%._-])(?=.*[A-Z]).{8,16}$");
        let passAlert= document.getElementById("password-alert");
        console.log(regex.test(password));
        if(!regex.test(password)){
            passAlert.innerText="Incompatible Password Check The Requirements"
            passAlert.hidden=false;
        }
        else
            passAlert.hidden=true;
    }
</script>
</html>
