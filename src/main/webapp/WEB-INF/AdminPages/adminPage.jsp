<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <title>MYOP-Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="./customcss/general.css"/>
</head>
<body class="default">
<script src="./bootstrap/js/bootstrap.js" defer></script>
<script src="./bootstrap/popper.js" defer></script>

<script src="./jslibraries/jQuery.js"></script>
<script src="./jslibraries/utilities.js"></script>
<script src="./jslibraries/adminTabler.js"></script>
<script src="./jslibraries/adminForms.js"></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<style>
    tr:nth-child(2n) {
        background: rgba(0,0,0,0.4);
    }
    @media screen and (max-width: 700px){
        tr:nth-child(2n) {
            background: rgba(0,0,0,0);
        }
        td:nth-child(2n) {
            background: rgba(0,0,0,0.4);
        }

    }
</style>

<nav class="topbar">
    <div class="icon-container sidebar-icon" onclick="hideSidebar()">
        <%@include file="../../icons/list-ul.svg" %>
    </div>
    <h2 id="selectedFormTitle"> Choose Element</h2>



    <button id ="insertButton" onclick="prepareFormInsert()" class='btn active' style="float:right;" hidden>Add Element</button>
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
    <h2 class="button">Purchases</h2>
    <h2 class="button">Countries</h2>
</nav>
<div class="rightBox" style="display: none">
    <form id="searchForm" >
        <table id="searchFormContainer">
        </table>
    </form>


    <button class='btn active' onclick='submitForm()'>Search</button>
</div>

<div>
    <table id="searchResultItem" class="searchResult">
    </table>
</div>

<div id="overlayFormModify" class="overlayElement" style="display: none">
    <div class="centered-box">
        <div class="box-container">
            <table style="width: 100%">
                <tr style="vertical-align: middle">
                    <td><h1 id="updateTitle" style="float: left"></h1></td>
                    <td>
                        <button onclick="toggleOverlayModify()" class="btn btn-danger" style="font-size: 26px;font-weight: bolder" >X</button>
                    </td>
                </tr>
            </table>
            <form id="updateForm" action="/MYOPSite_war_exploded/admin/modifyDB" method="post" enctype="multipart/form-data">
                <table class="User-box" id="updateFormBox">
                </table>
                <div id="imageInput"></div>

            <table id="buttonSpace" style="width: 100%">
            </table>
            </form>

        </div>
    </div>
</div>
<div id="overlayMakeSure" class="overlayElement" style="display: none">
    <div class="centered-box">
        <div class="box-container">
            <table style="width: 100%">
                <tr style="vertical-align: middle">
                    <td><h1 style="float: left">Are you sure?</h1></td>
                    <td>
                        <button onclick="toggleOverlayMakeSure()" class="btn btn-danger" style="font-size: 26px;font-weight: bolder" >X</button>
                    </td>
                </tr>
            </table>
            <table>
                <tbody>
            <tr><td>
                <form action="/MYOPSite_war_exploded/admin/modifyDB" method="POST">
                <input id='idToDelete' type='hidden' name='id'>
                <input type='hidden' name='option' value='delete'>
                <input id='itemToDelete' type='hidden' name='requestedItem'>
                <input type="submit" class='btn btn-danger' value='Yes'>
                </form>
            </td>
                <td>
                    <button onClick='toggleOverlayMakeSure()' class='btn active'>No</button>
                </td></tr>
                </tbody>
            </table>
        </div>
    </div>
</div>


</body>


<script>

    function toggleOverlayMakeSure(id){
        $("#overlayMakeSure").fadeToggle();
        var item;
        switch(selectedElement){
            case "Gpus":
                item = "gpus"
                break
            case "Cpus":
                item = "cpus"
                break
            case "Psus":
                item = "psus"
                break
            case "MotherBoards":
                item = "motherboards"
                break
            case "Memories":
                item = "memories"
                break
            case "Cases":
                item = "cases"
                break
            case "Builds":
                item = "builds"
                break
            case "Users":
                item = "users"
                break
            case "Countries":
                item = "countries"
                break
        }
        $("#itemToDelete").val(item)
        $("#idToDelete").val(id)
    }

    let selectedElement;



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



    function submitForm() {

        let xhttp = new XMLHttpRequest();
        let formDATA = $("#searchForm").serialize();
        $('tr.removable').remove();

        if(selectedElement=="Users"){
            submitFormUsers(formDATA);
            return;
        }

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);

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
                        case "Purchases":
                            results.forEach(purchaseTabler);
                            break;
                    case "Countries":
                        results.forEach(countryTabler);
                        break;

                    default:
                        $("#searchResultItem").html("Cannot visualize");
                }

            }
        };
        xhttp.open("POST", "/MYOPSite_war_exploded/itemsLister", true);

        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        xhttp.send(formDATA);
        console.log(formDATA);

    }

    function submitFormUsers(formData) {
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var results = JSON.parse(this.responseText);
                console.log(this.responseText)
                results.forEach(userTabler);
            }
        }
        xhttp.open("POST", "/MYOPSite_war_exploded/admin/usersLister", true);

        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        xhttp.send(formData);
        console.log(formData);
    }




    function viewItem(id) {

        let formData = $("#" + id).serialize()
        console.log(formData);
        $.ajax({
            url: "./admin/showItem",
            type: 'POST',
            data: formData,
            beforeSend: function (x) {
                x.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            },
            success: function (data) {
                console.log(data);
                let item = JSON.parse(data);

                prepareFormUpdate(item)
            },
            failed:function (data){
                createToast("Error","Cannot Retrieve Item")},
            cache: false,
            contentType: false,
            processData: false
        });
    }



    function checkProductName(){
        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log("ItemIsPresent "+this.responseText);
                if(this.responseText.includes("itemIsPresent")){
                    console.log("ItemIsPresent "+this.responseText);
                    $("#name-alert").attr('hidden', false);
                    document.getElementById("submitDBUpdate").disabled=true;
                    return false;
                }else if(this.responseText.includes("fileIsPresent")){
                    console.log("ItemIsPresent "+this.responseText);
                    document.getElementById("submitDBUpdate").disabled=true;
                    createToast("Error","Image Name Already Present,Change Image Name");
                    return false;
                }else {
                    $("#name-alert").attr('hidden', true);
                    document.getElementById("submitDBUpdate").disabled = false;
                    return true;
                }
            }
        };
        xhttp.open("POST", ".\/admin\/itemIsPresent", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        let requestSend="name="+document.getElementById("productName").value+"&id="+document.getElementById("id").value+
            "&requestedItem="+document.getElementById("requestedItemUpdate").value+"&path="+document.getElementById("image").value;
        xhttp.send(requestSend);
    }






</script>

</html>