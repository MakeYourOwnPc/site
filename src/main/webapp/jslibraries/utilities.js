
function testRegexPassword(password){

    let regex=new RegExp("^(?=.*[a-z])(?=.*\\d)(?=.*[@#$%._-])(?=.*[A-Z]).{8,16}$");
    console.log(regex.test(password));
   return regex.test((password));
}
/*
function validateUser(){
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
}*/

function toggleOverlayModify() {
    $("#overlayFormModify").fadeToggle();
}
function toggleOverlayPassword() {
    $("#overlayFormPassword").fadeToggle();
}
function toggleOverlayBuild() {
    $("#overlayFormBuild").fadeToggle();
}
/*
function existingEmail(){
    let xhttp = new XMLHttpRequest();
    let emailalert = document.getElementById("email-alert");
    let submit = document.getElementById("submit-registration");
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText=="true"){
                emailalert.innerText="Email Already Present";
                emailalert.hidden=false;
                submit.disabled=true;
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
}*/

function createToast(title,message){
    let toastHtml;
    toastHtml="<div id=\"toast\" class=\"toast-box\">\n" +
        "    <div class=\"toast-header\">" +
        "<button onclick=\"closeToast()\" style=\"padding: ;margin: 6px;\" class=\"btn-danger btn\">X</button>" +
        "<h3 id=\"toastTitle\">"+title+"</h3></div>" +
        "    <div class=\"toast-body\"><span id=\"toastMessage\">"+message+"</span></div>\n" +
        "</div>";
    $("body").append(toastHtml);
    setTimeout(closeToast,5000)
}
function closeToast(){
    $("#toast").remove();
}

