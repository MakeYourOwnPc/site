<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <title>MYOP-Admin</title>
    <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../customcss/general.css"/>
</head>
<body class="default">
<script src="../bootstrap/js/bootstrap.js" defer></script>
<script src="../bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="../jslibraries/jQuery.js"></script>

<nav class="topbar">

        <div id="icon-container sidebar-icon" onclick="hideSidebar()">
            <%@include file="../../icons/list-ul.svg" %>
        </div>
        <h2 id="selectedFormTitle"> Choose Element</h2>

        <div id="icon-container filter-icon" onclick="hideFormContainer()" >
            <%@include file="../../icons/filter_icon.svg" %>
        </div>

    <br>

</nav>

<nav class="sidebar collapsable" id="sidebar">

    <h2 class="button">Gpus</h2>
    <h2 class="button">Cpus</h2>
    <h2 class="button">Memories</h2>
    <h2 class="button">Cases</h2>
    <h2 class="button">Psus</h2>
    <h2 class="button">MotherBoards</h2>
    <h2 class="button">Users</h2>
</nav>
<form   id="searchForm" action="" onkeyup="submitForm()">
    <table id="searchFormContainer">
    </table>
</form>
<ul id="searchResult">

</ul>

<span>

</span>

</body>

<script>
    function hideSidebar() {
        $("#sidebar").slideToggle();
    }

    function hideFormContainer(){
        $("#searchFormContainer").slideToggle();
    }

    $(document).ready(function () {

        $(".sidebar").children(".button").click(function () {
            $(this).siblings("#activeForm").removeAttr("id");
            $(this).siblings(".active").removeClass("active");
            $(this).attr("id", "activeForm");
            $(this).addClass("active");

            changeForm($(this).text());
            hideSidebar();

        });
    });

    function changeForm(text) {

        $("#selectedFormTitle").text(text);

        var formHTML;
        var user = false;
        var power = true;
        switch (text) {
            case "Gpus":
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='gpus'>";
                break;
            case "Cpus":
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='cpus'>" +
                    "<tr><td><label for='CPUsocket' >Socket</label></td>" +
                    "<td><div class='form'><input type='text' id='CPUsocket' name='CPUsocket' value='CPUsocket'></td></tr>";
                break;

            case "Memories":
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='memories'>" +

                    "<tr><td><label for='RAMsocket' >Socket</label></td>" +
                    "<td><input type='text' id='RAMsocket' name='RAMsocket' value='RAMsocket'></td></tr>" +

                    "<tr><td><label for='Ram' >Ram</label></td>" +
                    "<td><input type='radio' id='Ram' name='mType' value='Ram'></td></tr>" +

                    "<tr><td><label for='MassStorage'>MassStorage</label></td>" +
                    "<td class='form'><input type='radio' id='MassStorage' name='mType' value='MassStorage'></td></tr>" +

                    "<tr><td><label for='amountMemories'>Amount Of Memories</label></td>" +
                    "<td><input type='number' id='amountMemories' name='amountOfMemories'></td></tr>";
                break;
            case "Cases":
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='cases'>" +
                    "<tr><td><label for='formFactorm'>Form Factor</label></td>" +
                    "<td><input type='text' id='formFactor' name='formFactor' value='formFactor'></td></tr>";
                power = false;
                break;
            case "Psus":
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='psus'>";
                break;
            case "Users":
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='users'>" +
                    "<tr><td><input type='text' id='firstName' name='firstName' value='First Name'></td>" +
                    "<td><input type='text' id='lastName' name='lastName' value='Last Name'></td></tr>";
                user = true;
                power = false;
                break;

            case "MotherBoards":
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='memories'>" +

                    "<tr><td><label for='CPUsocket'>CPU Socket</label></td>" +
                    "<td><input type='text' id='CPUsocket' name='CPUsocket' value='CPUSocket'></td></tr>" +

                    "<tr><td><label for='RAMsocket'>RAM Type</label></td>" +
                    "<td><input type='text' id='RAMsocket' name='RAMsocket' value='RAMSocket'></td></tr>" +

                    "<tr><td><label for='nRAMSockets'>RAM Slots</label></td>" +
                    "<td><input type='number' id='nRAMSockets' name='nRAMSockets'></td></tr>" +

                    "<tr><td><label for='nSATASockets'>SATA Sockets</label></td>" +
                    "<td><input type='number' id='nSATASockets' name='nSATASockets'></td></tr>" +

                    "<tr><td><label for='nNVMESockets'>NVME Sockets</label></td>" +
                    "<td><input type='number' id='nNVMESockets' name='nNVMESockets'></td></tr>";
                    break;
        }

        if (user) formHTML += "<tr><td class='label'><label for='id'>Object Id</label></td>" +
            "<td><input type='number' id='id' name='id' value='Object id'></td></tr>"

        else formHTML += "<tr><td><label for='power'>Power</label></td>" +
            "<td class='form'><input type='number' id='power' name='power'></td></tr>";

        formHTML += "<tr><td><label for='name'>Name</label></td><td><input type='text' id='name' name='name' value='Search Name'></td></tr>";
        $("#searchFormContainer").html(formHTML);
    }

    function submitForm() {
        var data = new FormData(document.getElementById("searchForm"));
        var output = Object.fromEntries(data.entries());
        output.filters = data.getAll("filters");
        console.log({object});

    }


</script>

</html>