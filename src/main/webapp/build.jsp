<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 05/07/2021
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int id;
    try {
        id = (int) request.getAttribute("id");
    }catch (RuntimeException e){id=0;}/*per necessità della servlet di salvataggio è stata fatta la conversione in 0 di eventuali valori null*/

%>
<html>
<head>
    <title>MYPO-Building</title>
    <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="./customcss/general.css"/>

    <style>
        .outOfStock{
            color: red;
        }
        .outOfStock:before{content:"Out Of Stock  "}
    </style>
</head>
<body>

<input type="hidden" id="oldMobo" value='${mobo}'>
<input type="hidden" id="oldGpu" value='${gpu}'>
<input type="hidden" id="oldCpu" value='${cpu}'>
<span id="oldMemories" style="display: none">${memories}</span>
<input type="hidden" id="oldPsu" value='${psu}'>
<input type="hidden" id="oldPcCase" value='${pcCase}'>
<input type="hidden" id="idBuild" value='<%=id%>'>
<input type="hidden" id="refererAdmin" value='${referer}'>
<input type="hidden" id="isAdmin" value='${user.admin}'>
<input type="hidden" id="oldBuildType" value="'${type}'">


<script src="./bootstrap/js/bootstrap.js" defer></script>
<script src="./bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="./jslibraries/jQuery.js"></script>
<script src="./jslibraries/Build.js"></script>
<script src="jslibraries/utilities.js"></script>

<div class="fullHeightFooter">
<div id="overlayFormBuild" class="overlayElement" style="display: none">
    <div class="inner-window">
        <div class="box-container">
            <table style="width: 100%">
                <tr>
                    <td><h1 id="ComponentTitle" style="float: left"></h1></td>
                    <td>
                        <button onclick="toggleOverlayBuild()" class="btn btn-danger"
                                style="font-size: 26px;font-weight: bolder; float:right;">X</button>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><label for="productName"> Search By Name</label></td>
                    <td><input id="productName" type="text" name="name" onkeyup="submitForm()" ></td>
                </tr>
                <tr>
                    <td><label for="compatible">Check By Compatible Component</label></td>
                    <td><input type="checkbox" name="compatible" id="compatible" onclick="submitForm()"></td>
                </tr>
                <tr id="massStorageOption" style="display: none">
                    <td>
                        <label for="sata">SATA</label>
                        <input type="radio" name="socket" id="sata" value="sata" onclick="submitForm()" checked>
                    </td>
                    <td>
                        <label for="nvme">NVME</label>
                        <input type="radio" name="socket" id="nvme" onclick="submitForm()" value="sata">
                    </td>
                </tr>

            </table>
            <table class="User-box searchResult" id="searchResultBuild">
                <thead></thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
</div>

<div id="pageContenent">
    <div>
        <h1 id="" style="padding: 10px;background-color: rgba(0,0,0,0.3); text-align: center">
            Build Your Computer
        </h1>
    </div>
    <div id="builds">

           <label for="gpu">Select GPU</label>
        <input type="text" id="gpu" onclick="selectGPU()" readonly>
           <label for="cpu">Select CPU</label>
        <input type="text" id="cpu" onclick="selectCPU()" readonly>
             <label for="mobo">Select MotherBoard</label>
        <input type="text" id="mobo" onclick="selectMOBO()"readonly>
             <label for="ram">Select RAM</label>
        <input type="text" id="ram" onclick="selectRAM()"readonly>
            <label for="massStorage1">Select the First Mass Storage</label>
        <input type="text" id="massStorage1" onclick="selectMassStorage(1)"readonly>
             <label for="massStorage2">Select the Second Mass Storage</label>
        <input type="text" id="massStorage2" value="NONE" onclick="selectMassStorage(2)"readonly>
             <label for="massStorage3">Select the Third Mass Storage</label>
        <input type="text" id="massStorage3" value="NONE" onclick="selectMassStorage(3)"readonly>
            <label for="pcCase">Select the Pc Case</label>
        <input type="text" id="pcCase" onclick="selectPcCase()"readonly>
             <label for="psu">Select the Psu</label>
        <input type="text" id="psu" onclick="selectPsu()"readonly>
        <label for="buildType">Build Type</label>
        <select  id="buildType">
            <option value="Gaming">Gaming</option>
            <option value="Office">Office</option>
            <option value="Terminal">Terminal</option>
            <option value="Workstation">Workstation</option>
        </select>

    </div>
    <table>
        <tbody id="submitButtons">
        <tr>
            <td><label for="endPrice">Price</label></td>
            <td>
                <h2 id="endPrice"></h2>
            </td>
            <td><label for="purchase">Ready To Purchase</label></td>
            <td>
                <button id="purchase" class="btn btn-success" onclick="return purchase()">Purchase</button>
            </td>
            <form id="purchaseForm" method="post" action="/MYOPSite_war_exploded/addToCart" enctype="application/x-www-form-urlencoded">
                <input id="purchaseBuildJson" type="hidden" name="build">
            </form>

        </tr>
        <tr>
            <td><label for="saveBuild">Save Build?</label></td>
            <td id="saveButtonPlace">
                <button id="saveBuild" class="btn active" style="transition: 1s" onclick="saveBuild()">SAVE</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>

    <jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</div>


</body>


<script>
    $(document).ready(function () {
        $("#builds input").val("");/* autocomplete=off è inutile quindi meglio*/
        retrievePrecedentBuild()
        adminModifyBuild()
        if($("#isAdmin").val()=="true")
         addSuggestedButtons()
        updateSpecification()
    });
    function addSuggestedButtons(){
        let formButton="<tr><td><label for=suggested>Suggested</label></td>" +
            "<td><input type=radio id=suggested name=suggested value='true'></td> " +
            "<td><label for=notSuggested>Not Suggested</label></td>" +
            "<td><input type=radio id=notSuggested name=suggested value='false' checked></td></tr>";
        $("#submitButtons").prepend(formButton);

    }
    function adminModifyBuild(){
        let referFlag=$("#refererAdmin").val();
        if(!referFlag.includes("admin"))
            return;
        $("#purchase").remove();
        $("#purchaseForm").remove();
        $("#saveBuild").remove();
        let formButton="<form action='/MYOPSite_war_exploded/saveBuild' method='post' onclick='return updateBuildJson()'>" +
            "<input type='hidden' name='build' id='sendBuildJson' value=''>" +
            "<input type='hidden' name='referer' value='admin'>" +
            "<input id='saveBuild' type='submit' value='Save' class='btn active'></form>";
        $("#saveButtonPlace").html(formButton);

    }
    function updateBuildJson(){
        if(!checkValidity())
            return false
        else{
            $("#sendBuildJson").val(stringifyBuild())
            return true;
        }
    }

    function retrievePrecedentBuild() {

        let oldmobo=$("#oldMobo").val()
        let oldGpu=$("#oldGpu").val()
        let oldCpu=$("#oldCpu").val()
        let oldMemories=$("#oldMemories").text()
        let oldPsu=$("#oldPsu").val()
        let oldPcCase=$("#oldPcCase").val()
        if(oldmobo===""||oldGpu===""||oldCpu===""||oldMemories===""||oldPsu===""||oldPcCase===""){
            return;
        }
        mobo = JSON.parse(oldmobo);

        $("#mobo").val(mobo.name);
        gpu = JSON.parse(oldGpu);
        $("#gpu").val(gpu.name);
        cpu = JSON.parse(oldCpu);
        $("#cpu").val(cpu.name);
        let memories=JSON.parse(oldMemories);
        ram=memories.find(elem=>elem.mType==false);
        $("#ram").val(ram.name);
        let massStorages=memories.filter(elem=>elem.mType==true)
        massStorage1 = massStorages[0];
        $("#massStorage1").val(massStorage1.name);
        massStorage2 = massStorages[1];
        if(massStorage2!=null){
            $("#massStorage2").val(massStorage2.name);
        }
        massStorage3 = massStorages[2];
        if(massStorage3!=null) {
            $("#massStorage3").val(massStorage3.name)
        }
        psu = JSON.parse(oldPsu);
        $("#psu").val(psu.name);
        pcCase = JSON.parse(oldPcCase);
        $("#pcCase").val(pcCase.name);
        idBuild=$("#idBuild").val();
        if(idBuild=="null") idBuild=0;
        else
            idBuild=parseInt(idBuild,10)
    }

    function stringifyBuild(){
        let suggested;
        if(idBuild === undefined)
            idBuild=0;
        try {
            suggested = !!$("#suggested").prop("checked");
        }catch (e){suggested=false};

        let build = {
            mobo: mobo.id,
            gpu:0,
            cpu: cpu.id,
            pcCase: pcCase.id,
            psu: psu.id,
            type: $("#buildType").val(),
            memories: [ram.id,massStorage1.id],
            suggested:suggested,
            id:idBuild
        }
        if(gpu!=undefined){
            build.gpu=gpu.id;
        }
        if(massStorage2!=null){
            build.memories.push(massStorage2.id);
        }
        if(massStorage3!=null){
            build.memories.push(massStorage3.id);
        }
        return JSON.stringify(build);
    }


    function purchase(){
        if($("#loggedHeader").val()==""){
            createToast("Not Logged In","Cannot Purchase The Build Without Being Logged In");
            saveBuild();
            return false;
        }
        checkValidity()
        if(!submitable) return;
        $("#purchaseBuildJson").val(stringifyBuild())
        document.getElementById("purchaseForm").submit();
    }

    function saveBuild() {
        checkValidity()
        if(!submitable) return;

        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {

            if (this.readyState == 4 && this.status == 200) {

                console.log("server response " +this.responseText);
                idBuild=parseInt(this.responseText)
                $("#saveBuild").text("SAVED").removeClass("active").addClass("btn-success");
                setTimeout(function(){$("#saveBuild").text("SAVE").addClass("active").removeClass("btn-success")},3000);

            } else if (this.readyState == 4 && this.status == 404) {
                console.log("error");
                createToast("Error","Cannot Save")
            }
        };

        xhttp.open("POST", "/MYOPSite_war_exploded/saveBuild", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        let sentData = stringifyBuild();
        console.log(sentData);

        xhttp.send("build="+sentData+"&referer=");
    }



</script>

</html>
