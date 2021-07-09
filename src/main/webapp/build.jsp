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
        id = (int) session.getAttribute("id");
    }catch (RuntimeException e){id=0;}

%>
<html>
<head>
    <title>MYPO-Building</title>
    <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="./customcss/general.css"/>
</head>
<body>
<input type="hidden" id="oldMobo" value='${mobo}'>
<input type="hidden" id="oldGpu" value='${gpu}'>
<input type="hidden" id="oldCpu" value='${cpu}'>
<span id="oldMemories" style="display: none">${memories}</span>
<input type="hidden" id="oldPsu" value='${psu}'>
<input type="hidden" id="oldPcCase" value='${pcCase}'>
<input type="hidden" id="idBuild" value='<%=id%>'>


<script src="./bootstrap/js/bootstrap.js" defer></script>
<script src="./bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="./jslibraries/jQuery.js"></script>
<script src="./jslibraries/Build.js"></script>


<div id="overlayForm" class="overlayElement" style="display: none">
    <div class="inner-window">
        <div class="box-container">
            <table style="width: 100%">
                <tr>
                    <td><h1 id="ComponentTitle" style="float: left"></h1></td>
                    <td>
                        <button onclick="toggleOverlay()" class="btn btn-danger"
                                style="font-size: 26px;font-weight: bolder; float:right;">X</button>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><label for="productName"> Search By Name</label></td>
                    <td><input id="productName" type="text" name="name" onkeyup="submitForm()"></td>
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
            <table class="User-box" id="searchResultBuild">
                <thead></thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
</div>

<div id="pageContenent">
    <div>
        <h1 style="padding: 10px;background-color: rgba(0,0,0,0.3); text-align: center">
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

    </div>
    <table>
        <tbody>
        <tr>
            <td><label for="endPrice">Price</label></td>
            <td>
                <h2 id="endPrice"></h2>
            </td>
            <td><label for="purchase">Ready To Purchase</label></td>
            <td>
                <button id="purchase" class="btn btn-success">Purchase</button>
            </td>

        </tr>
        <tr>
            <td><label for="saveBuild">Save Build?</label></td>
            <td>
                <button id="saveBuild" class="btn active" style="transition: 1s" onclick="saveBuild()">SAVE</button>
            </td>
        </tr>
        </tbody>
    </table>

    <jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</div>

</body>
<script>
    $(document).ready(function () {
        retrievePrecedentBuild()
        updateSpecification()
    });

    function retrievePrecedentBuild() {
        mobo = JSON.parse($("#oldMobo").val());
        $("#mobo").val(mobo.name);
        gpu = JSON.parse($("#oldGpu").val());
        $("#gpu").val(gpu.name);
        cpu = JSON.parse($("#oldCpu").val());
        $("#cpu").val(cpu.name);
        let memories=JSON.parse($("#oldMemories").text());
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
        psu = JSON.parse($("#oldPsu").val());
        $("#psu").val(psu.name);
        pcCase = JSON.parse($("#oldPcCase").val());
        $("#pcCase").val(pcCase.name);
        idBuild=$("#idBuild").val();
        if(idBuild=="null") idBuild=0;
    }

    function saveBuild() {
        checkDisableSubmit();
        if(!submitable)
            return;
        if(idBuild==undefined||idBuild==null)
            idBuild=0;

        let build = {
            mobo: mobo.id,
            gpu: gpu.id,
            cpu: cpu.id,
            pcCase: pcCase.id,
            psu: psu.id,
            type: "",
            memories: [ram.id,massStorage1.id, massStorage2.id, massStorage3.id],
            suggested:false,
            id:idBuild
        }
        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {

            if (this.readyState == 4 && this.status == 200) {

                console.log("server response " +this.responseText);
                idBuild=parseInt(this.responseText)
                $("#saveBuild").text("SAVED").removeClass("active").addClass("btn-success");
                setTimeout(function(){$("#saveBuild").text("SAVE").addClass("active").removeClass("btn-success")},3000);

            } else if (this.readyState == 4 && this.status == 404) {
                console.log("error");
                $("#searchResultBuild").append("Saving Error");
            }
        };

        xhttp.open("POST", "/MYOPSite_war_exploded/saveBuild", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        let sentData = JSON.stringify(build);
        console.log(sentData);
        xhttp.send("build="+sentData);

    }
    function checkDisableSubmit(){
        updateSpecification()
        if(!submitable){
            $("#saveBuild").removeClass("active").addClass("disabled-btn");
        }
        else
        $("#saveBuild").removeClass("disabled-btn").addClass("active");

    }
</script>

</html>
