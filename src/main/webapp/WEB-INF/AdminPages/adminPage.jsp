<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <title>MYOP-Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
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

    <div class="icon-container filter-icon" onclick="toggleFormContainer()">
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
    <h2 class="button">Builds</h2>
    <h2 class="button">Users</h2>
</nav>
<div class="rightBox">
    <form id="searchForm" >
        <table id="searchFormContainer">
        </table>

    </form>

    <button  class='btn active' onclick='submitForm()'>Search</button>
</div>
<table id="searchResult">
</table>
<span>
</span>
</body>

<script>

    var selectedElement;

    function hideSidebar() {
        $("#sidebar").slideToggle();
    }

    function toggleFormContainer() {
        $(".rightBox").slideToggle();
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
        selectedElement = text;
        $("#selectedFormTitle").text(text);
        $("#searchResult").removeClass(); /* without attributes removes all classes*/
        var formHTML;
        var user = false;
        var power = true;
        switch (text) {
            case "Builds":
                $("#searchResult").addClass("buildsTable");
                formHTML="<input type='hidden' id='requestedItem' name='requestedItem' value='builds'>"+
                    "<tr><td><label for='CPUname'>CPU Name</label></td><td><input type='text' id='CPUname' name='cpuName'></td></tr>" +
                    "<tr><td><label for='GPUname'>GPU Name</label></td><td><input type='text' id='GPUname' name='gpuName'></td></tr>" +
                    "<tr><td><label for='PSUname'>PSU Name</label></td><td><input type='text' id='PSUname' name='psuName'></td></tr>" +
                    "<tr><td><label for='MOBOname'>MOBO Name</label></td><td><input type='text' id='MOBOname' name='moboName'></td></tr>" +
                    "<tr><td><label for='CASEname'>CASE Name</label></td><td><input type='text' id='CASEname' name='caseName'></td></tr>"
                    break;
            case "Gpus":
                $("#searchResult").addClass("gpusTable");
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='gpus'>";
                break;
            case "Cpus":
                $("#searchResult").addClass("cpusTable");
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='cpus'>" +

                    "<tr><td><label for='CPUsocket' >Socket</label></td>" +
                    "<td><div class='form'><input type='text' id='CPUsocket' name='CPUsocket' ></td></tr>"+

                    "<tr><td><label for='integratedGpu' >Integrated Gpu</label></td>" +
                    "<td><div class='form'><input type='radio' id='integratedCPU' name='integratedGpu' value='true'></td></tr>" +
                    "<tr><td><label for='integratedGpu' >No Integrated Gpu</label></td>" +
                "<td><div class='form'><input type='radio' id='integratedGpu' name='integratedGpu' value='false'></td></tr>"+
                "<tr><td><label for='integratedGpu' >Ignore Integrated Gpu</label></td>" +
                "<td><div class='form'><input type='radio' id='integratedGpu' name='integratedGpu' value='' checked></td></tr>";
                break;

            case "Memories":
                $("#searchResult").addClass("memoriesTable");
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='memories'>" +

                    "<tr><td><label for='MEMsocket' >Memory Socket</label></td>" +
                    "<td><input type='text' id='MEMsocket' name='MEMsocket'></td></tr>" +

                    "<tr><td><label for='Ram' >Ram</label></td>" +
                    "<td><input type='radio' id='Ram' name='mType' value='Ram'></td></tr>" +

                    "<td><label for='All' >All</label></td>" +
                    "<td><input type='radio' id='All' name='mType' checked value=''></td></tr>" +

                    "<tr><td><label for='MassStorage'>MassStorage</label></td>" +
                    "<td class='form'><input type='radio' id='MassStorage' name='mType' value='MassStorage'></td></tr>" +

                    "<tr><td><label for='amountMemories'>Amount Of Memories</label></td>" +
                    "<td><input type='number' id='amountMemories' name='amountOfMemories'></td></tr>";
                break;
            case "Cases":
                $("#searchResult").addClass("casesTable");
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='cases'>" +
                    "<tr><td><label for='formFactorm'>Form Factor</label></td>" +
                    "<td><input type='text' id='formFactor' name='formFactor' ></td></tr>";
                power = false;
                break;
            case "Psus":
                $("#searchResult").addClass("psusTable");
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='psus'>"+
                    "<tr><td><label for='power'>Power</label></td>" +
                    "<td class='form'><input type='number' id='power' name='power'></td></tr>";
                break;
            case "Users":
                $("#searchResult").addClass("usersTable");
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='users'>" +
                    "<tr><td><label for='email'>Email</label></td>" +
                    "<td><input type='text' id='email' name='email' ></td></tr>";
                user = true;
                power = false;
                break;

            case "MotherBoards":
                $("#searchResult").addClass("motherboardsTable");
                formHTML = "<input type='hidden' id='requestedItem' name='requestedItem' value='motherboards'>" +

                    "<tr><td><label for='CPUsocket'>CPU Socket</label></td>" +
                    "<td><input type='text' id='CPUsocket' name='CPUsocket'></td></tr>" +

                    "<tr><td><label for='RAMsocket'>RAM Type</label></td>" +
                    "<td><input type='text' id='RAMsocket' name='RAMsocket' ></td></tr>" +

                    "<tr><td><label for='nRAMSockets'>RAM Slots</label></td>" +
                    "<td><input type='number' id='nRAMSockets' name='nRAMSockets'></td></tr>" +

                    "<tr><td><label for='nSATASockets'>SATA Sockets</label></td>" +
                    "<td><input type='number' id='nSATASockets' name='nSATASockets'></td></tr>" +

                    "<tr><td><label for='nNVMESockets'>NVME Sockets</label></td>" +
                    "<td><input type='number' id='nNVMESockets' name='nNVMESockets'></td></tr>"+

                    "<tr><td><label for='formFactor'>Form Factor</label></td>" +
                    "<td><select type='number' id='formFActor' name='formFactor'>" +
                    "<option></option>" +
                    "<option value='mini-itx'>Mini-ITX</option>" +
                    "<option value='micro-atx'>Micro-ATX</option>" +
                    "<option value='atx'>ATX</option>" +
                    "</select></td></tr>";
                break;
        }




        formHTML += "<tr><td><label for='name'>Name</label></td><td><input type='text' id='name' name='name'></td></tr>" ;

        $("#searchFormContainer").html(formHTML);
        submitForm();
    }

    function submitForm() {

        let xhttp = new XMLHttpRequest();
        let formDATA = $("#searchForm").serialize();
        $("#searchResult").html("");

        xhttp.onreadystatechange = function () {

            if (this.readyState == 4 && this.status == 200) {
                console.log (this.responseText);

                var results = JSON.parse(this.responseText);



                switch (selectedElement) {
                    case "Gpus":

                        results.forEach(gpuTabler);
                        break;
                    case "Cpus":

                        results.forEach(cpuTabler);
                        break;
                    case "Memories":
                        results.forEach(memoryTabler);
                        break;
                    case "Builds":

                        results.forEach(buildTabler);
                        break;
                    case "MotherBoards":

                        results.forEach(moboTabler);
                        break;
                    case "Cases":

                        results.forEach(pcCaseTabler);
                        break;
                    case "Psus":

                        results.forEach(psusTabler);
                        break;
                    case"Users":

                        results.forEach(userTabler);
                        break;
                    default:$("#searchResult").html("Cannot visualize");
                }
            }
        };
        xhttp.open("POST", "/MYOPSite_war_exploded/adminpage", true);

        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        xhttp.send(formDATA);
        console.log(formDATA);


    }

    function userTabler(value){
        var row;
        row = "<tr>"+
            "<td>" + value.firstName + "</td>" +
            "<td>" + value.lastName + "</td>" +
            "<td>" + value.email + "</td>" ;
        if (value.admin)
            row += "<td>Yes</td>";
        else row += "<td>No</td>";
            row+="</tr>";
        $("#searchResult").append(row);

    }
    function gpuTabler(value) {
        var row;
        row = "<tr>"+
        "<td>" + value.name + "</td>" +
        "<td>" + value.id + "</td>" +
            "<td>" + value.consumption + "</td>" +
         "<td>" + value.price + "</td>" +
            "<td>" + value.stock + "</td>" +
            "<td><img src=-'" + value.imagePath + "'></td>" +
            "</tr>";
        $("#searchResult").append(row);
    }

    function cpuTabler(value) {
        var row;
        row = "<tr>"+
        "<td>" + value.name + "</td>" +
        "<td>" + value.id + "</td>" +
        "<td>" + value.socket + "</td>"
        if (value.integratedgpu)
            row += "<td>Yes</td>"
        else row += "<td>No</td>"

        row += "<td>" + value.consumption + "</td>" +
            "<td>" + value.price + "</td>" +
            "<td>" + value.stock + "</td>" +
            "<td><img src=-'" + value.imagePath + "'></td>" +
            "</tr>"
        $("#searchResult").append(row);
    }

    function memoryTabler(value) {
        var row;
        row = "<tr>"
            + "<td>" + value.name + "</td>" +
            +"<td>" + value.id + "</td>" +
            +"<td>" + value.socket + "</td>"
        if (value.mType)
            row += "<td>Mass Storage</td>"
        else row += "<td>Ram</td>"

        row += "<td>" + value.amountMemories + "</td>" +
            "<td>" + value.consumption + "</td>" +
            "<td>" + value.price + "</td>" +
            "<td>" + value.stock + "</td>" +
            "<td><img src=-'" + value.imagePath + "'></td>" +
            "</tr>"
        $("#searchResult").append(row);
    }
    function moboTabler(value) {
        var row;
        row = "<tr>"+
            "<td>" + value.name + "</td>" +
            "<td>" + value.id + "</td>" +
            "<td>" + value.cpuSocket + "</td>" +
            "<td>" + value.ramSocket + "</td>" +
            "<td>" + value.amountSlotNvme + "</td>" +
            "<td>" + value.amountSlotSata + "</td>" +
         "<td>" + value.amountSlotRam + "</td>" +
            "<td>" + value.formFactor + "</td>" +
            "<td>" + value.consumption + "</td>" +
            "<td>" + value.price + "</td>" +
            "<td>" + value.stock + "</td>" +
            "<td><img src=-'" + value.imagePath + "'></td>" +
            "</tr>"
        $("#searchResult").append(row);
    }

    function pcCaseTabler(value) {
        var row;
        row = "<tr>"+
            "<td>" + value.name + "</td>" +
            "<td>" + value.id + "</td>" +
            "<td>" + value.formFactor + "</td>" +
            "<td>" + value.price + "</td>" +
            "<td>" + value.stock + "</td>" +
            "<td><img src=-'" + value.imagePath + "'></td>" +
            "</tr>"
        $("#searchResult").append(row);
    }

    function buildTabler(value) {
        var row;
        row = "<tr>"+
            "<td>" + value.id + "</td>" +
            "<td>" + value.mobo + "</td>" +
            "<td>" + value.gpu + "</td>" +
            "<td>" + value.cpu + "</td>"+
             "<td>" + value.pcCase + "</td>";
            for(let i in value.memories){
                row+= i + "<br>";
            }
        if (value.suggested)
            row += "<td>Yes</td>"
        else row += "<td>No</td>"

            row+= "<td><td>" + value.type + "</td></td>" +
                "<td><td>" + value.maker + "</td></td>" +
            "</tr>"
        $("#searchResult").append(row);
    }

    function psusTabler(value) {
        var row;
        row = "<tr>"+
            "<td>" + value.name + "</td>" +
            "<td>" + value.id + "</td>" +
            "<td>" + value.power + "</td>"+
        "<td>" + value.stock + "</td>" +

            "<td><img src=-'" + value.imagePath + "'></td>" +
            "</tr>"
        $("#searchResult").append(row);
    }




</script>

</html>