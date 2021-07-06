<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 05/07/2021
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MYPO-Building</title>
    <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="./customcss/general.css"/>
</head>
<body>
<script src="./bootstrap/js/bootstrap.js" defer></script>
<script src="./bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="./jslibraries/jQuery.js"defer></script>
<script src="./jslibraries/Build.js"defer></script>


<div id="overlayForm" class="overlayElement" style="display: none">
    <div class="inner-window">
        <div class="box-container">
            <table style="width: 100%">
                <tr>
                    <td><h1 id="updateTitle" style="float: left"></h1></td>
                    <td>
                        <button onclick="toggleOverlay()" class="btn btn-danger" style="font-size: 26px;font-weight: bolder">X</button>
                    </td>
                </tr>
            </table>
            <table>
                <tr><td> <label for="productName"> Search By Name</label></td>
                    <td> <input id="productName" type="text" name="name"></td></tr>
                <tr><td><label for="compatible">Check By Compatible Component</label></td>
                    <td> <input type="checkbox" name="compatible" id="compatible" onclick="submitForm()"></td></tr>
                <tr id="massStorageOption">
                    <td>
                        <label for="sata">SATA</label>
                        <input type="radio" name="socket" id="sata" value="sata" checked>
                    </td>
                    <td>
                        <label for="nvme">NVME</label>
                        <input type="radio" name="socket" id="nvme" value="sata">
                    </td>
                </tr>

            </table>
                <table class="registration-box" id="searchResult">
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
        <select type="select" id="gpu" onclick="selectGPU()"></select>
        <label for="cpu">Select CPU</label>
        <select type="select" id="cpu" onclick="selectCPU()"></select>
        <label for="mobo">Select MotherBoard</label>
        <select type="select" id="mobo" onclick="selectMOBO()"></select>
        <label for="ram">Select MotherBoard</label>
        <select type="select" id="ram" onclick="selectRAM()"></select>
        <label for="massStorage1">Select the First Mass Storage</label>
        <select type="select" id="massStorage1" onclick="selectMassStorage(1)"></select>
        <label for="massStorage2">Select the Second Mass Storage</label>
        <select type="select" id="massStorage2" onclick="selectMassStorage(2)">
            <option value="none">NONE</option></select>
        <label for="massStorage3">Select the Third Mass Storage</label>
        <select type="select" id="massStorage3" onclick="selectMassStorage(3)">
            <option value="none">NONE</option>
        </select>
        <label for="pcCase">Select the Pc Case</label>
        <select type="select" id="pcCase" onclick="selectPcCase()"></select>
        <label for="psu">Select the Psu</label>
        <select type="select" id="psu" onclick="selectPsu()"></select>
    </div>

    <jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</div>

</body>
</html>
