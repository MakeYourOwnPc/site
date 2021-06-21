<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
    <head>
        <title>MYOP-Admin</title>
        <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
        <link rel="stylesheet"  type="text/css" href="../bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet"  type="text/css" href="../customcss/general.css"/>
        <link rel="stylesheet" type="text/css" href="../customcss/Admin.css"/>
    </head>
    <body class="default">
        <script src="../bootstrap/js/bootstrap.js" defer></script>
        <script src="../bootstrap/popper.js" defer></script>
        <jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
        <script src="../jslibraries/jQuery.js"defer></script>

        <nav class="topbar" style="height:45px">
            <div id ="icon-container" onclick="hideSidebar()" style="height: inherit;float:left"><%@include file="../../icons/list-ul.svg"%> </div>
            <h2>Component</h2>
            <form action="getComponent" style="float: right">
                <input type="button" value="Cerca">
            </form>
        </nav>

        <nav class="sidebar collapsable" id="collapse">

            <a class="button" href="./gpus">Gpus</a>
            <a class="button" href="./cpus">Cpus</a>
            <a class="button" href="./memories">Memories</a>
            <a class="button" href="./cases">Cases</a>
            <a class="button" href="./psus">Psus</a>
            <a class="button" href="./users">Users</a>
        </nav>



    </body>
    <script>
     function hideSidebar(){
             $("#collapse").slideToggle();}

     function iconDimension(){
         document.getElementsByClassName("sidebar-toggler").childNodes[0].setAttribute("height","45px");
     }

     // Get the container element
     var btnContainer = document.getElementById("sidebar");

     // Get all buttons with class="btn" inside the container
     var btns = btnContainer.getElementsByClassName("button");

     // Loop through the buttons and add the active class to the current/clicked button
     for (var i = 0; i < btns.length; i++) {
         btns[i].addEventListener("click", function() {
             var current = document.getElementsByClassName("active");
             current[0].className = current[0].className.replace(" active", "");
             this.className += " active";
         });
     }
    </script>

</html>