
<% response.addHeader("Set-Cookie", "Secure; SameSite=strict");%>

<html>
<head>
    <title>MYOC-YourData</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
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
        <input type="hidden" id="result" value="${result}">
        <h1>User Data</h1>
        <form name="registration" action="/MYOPSite_war_exploded/modifyUser" method="post" onsubmit="return validateData()">

            <table  class="User-box">
                <tr><td>
                    <label for="firstname">Firstname</label>
                </td><td>
                    <input type="text" name="firstName" id="firstname" value="${user.firstName}" disabled>
                    <span id="firstname-alert" class="alert-info " hidden> FirstName not Inserted</span>
                </td></tr>
                <tr><td>
                    <label for="lastname">LastName</label>
                    <td>
                        <input type="text" name="lastName" id="lastname" value="${user.lastName}" disabled>
                    <span  id="lastname-alert" class="alert-info " hidden> LastName not Inserted</span>
                </td></tr>
                <tr><td>
                    <label for="email">Email</label>
                    <td>
                        <input type="email" name="email" id="email" value="${user.email}"  disabled>
                    </td></tr>
                <tr style="display:none" id="newPasswordRow"><td>
                    <label for="newPassword" >New Password</label>
                    <td>
                        <input type="password" name="newPassword" id="newPassword" onkeyup="isPasswordMatching(value)"/>
                    <span id="newPassword-alert" class="alert-info" hidden>Invalid Password. Check Requirements.</span>
                </td></tr>
                <tr style="display:none" id="newPasswordTestRow"><td>
                    <label for="newPasswordTest" >New Password Confirm</label>
                    <td>
                        <input type="password" name="newPasswordTest" id="newPasswordTest">
                    <span id="newPasswordTest-alert" class="alert-info" hidden>Not The Same Password</span>
                    </td></tr>
                <tr><td>
                    <input class="btn active" value="Modify Data" onclick=toggleOverlayPassword() id="modify-data">
                    <input type="submit" class="btn active" id="applyChanges" value="Apply Changes" hidden>
                </td></tr>
            </table>

        </form>
        <div id="overlayFormPassword" class="overlayElement" style="display: none">
            <div class="centered-box">
                <div class="box-container">
                    <table style="width: 100%">
                        <tr style="vertical-align: middle">
                            <td><h1 id="updateTitle" style="float: left"></h1></td>
                            <td>
                                <button onclick="toggleOverlayPassword()" class="btn btn-danger" style="font-size: 26px;font-weight: bolder" >X</button>
                            </td>
                        </tr>
                    </table>
                    <tr><td>
                        <input type="password" placeholder="Insert Password" id="oldPassword" required>
                        <span id="oldpassword-alert" class="alert-info" hidden>Wrong Password</span>
                    </td></tr>
                    <tr><td>
                        <button id="submit-check" onclick="checkPassword()" class="btn active">Submit</button>
                    </td></tr>
                        </table>
                    </form>

                </div>
            </div>
        </div>
        <p id="passwordrequirements" style="text-align: left;display:none">
            The Password Must Contain:<br>
            -Between 8 And 16 Characters<br>
            -At Least One Special Character Between &%#.-_<br>
            -At Least An Uppercase And An Lowercase character<br>
            -At Least A Number
        </p>
    </div>
</div>
</div>

</body>

<script>
    $(document).ready(function(){
        let result = document.getElementById("result").value
        if(!result)
            createToast("Login Failed","Wrong Password")
    })
    function isPasswordMatching(password){
        let alertPass = document.getElementById("newPassword-alert")
        if(testRegexPassword(password))
            alertPass.hidden = true
        else
            alertPass.hidden = false
    }

    function checkPassword() {
        let xhttp = new XMLHttpRequest();
        let oldPassword = document.getElementById("oldPassword")
        let alert = document.getElementById("oldpassword-alert")
        let submit = document.getElementById("submit-check")
        let formName = document.getElementById("firstname")
        let formSurname = document.getElementById("lastname")
        let applyChanges = document.getElementById("applyChanges")
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let json = JSON.parse(this.responseText)
                console.log(this.responseText)
                if (json.attempts <= 0) {
                    alert.innerText = "Too many attempts. Try again in 5 minutes."
                    alert.hidden=false
                    submit.disabled = true
                }else if (json.result == false) {
                    alert.innerText = "Wrong Password";
                    alert.hidden = false;
                } else {
                    toggleOverlayPassword()
                    formName.disabled = false
                    formSurname.disabled = false
                    $("#newPasswordRow").show()
                    $("#newPasswordTestRow").show()
                    $("#modify-data").hide()
                    $("#passwordrequirements").show()
                    applyChanges.hidden = false
                }
            }
        };
            xhttp.open("POST", "/MYOPSite_war_exploded/matchPassword", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("oldPassword=" + oldPassword.value);
        }

        function validateData() {
            let submitable = true;
            let password = document.getElementById("newPassword");
            let passwordtest = document.getElementById("newPasswordTest");
            if (!document.getElementById("firstname").checkValidity) {
                submitable = false;
                document.getElementById("firstname-alert").hidden = false;
            } else document.getElementById("firstname-alert").hidden = true;
            if (!document.getElementById("lastname").checkValidity) {
                submitable = false;
                document.getElementById("lastname-alert").hidden = false;
            } else document.getElementById("lastname-alert").hidden = true;
            let passAlert = document.getElementById("newPassword-alert");
            if (password.value != "") {
                if (!testRegexPassword(password.value)) {
                    submitable = false;
                    passAlert.hidden = false;
                }
            } else
                passAlert.hidden = true;
            if (passwordtest.value != password.value) {
                submitable = false;
                document.getElementById("newPasswordTest-alert").hidden = false;
            } else document.getElementById("newPasswordTest-alert").hidden = true;

            return submitable;
        }
</script>
</html>
