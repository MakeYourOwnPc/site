<%@ page import="Model.Build.Build" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Purchase.Purchase" %>
<%@ page import="Model.Build.BuildNames" %>
<%@ page import="Model.Memory.Memory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 07/07/2021
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MYOC-Old Orders</title>
    <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
    <link rel="stylesheet" href="./bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="./customcss/general.css"/>
</head>
<body>
<script src="./bootstrap/js/bootstrap.js" defer></script>
<script src="./bootstrap/popper.js" defer></script>
<script src="jslibraries/utilities.js"></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="./jslibraries/jQuery.js"defer></script>
<input type="hidden" id="new" value="${purchased}">
<div id="pageContenent">
    <div>
        <h1 style="padding: 10px;background-color: rgba(0,0,0,0.3); text-align: center">
            Old Orders
        </h1>
    </div>
    <div class="buildContainer" style="display: flex">
  <%ArrayList<BuildNames> builds= (ArrayList<BuildNames>) request.getAttribute("builds");
  ArrayList<Purchase> purchases= (ArrayList<Purchase>) request.getAttribute("purchases");
  int i=0;
  for(BuildNames build:builds){%>



            <div class="box-container">
            <div class="buildList">
                <img src="<%=build.getImagePath()%>">
                <ul>
                    <li><%=build.getType()%></li>
                    <li><%=build.getGpu()%></li>
                    <li><%=build.getCpu()%></li>
                    <li><%=build.getMobo()%></li>
                    <li><ul>
                        <%for(String memory:build.getMemories()){%>
                            <li><%=memory%></li>
                        <%}%></ul></li>
                    <li><%=build.getPcCase()%></li>
                    <li><%=build.getPsu()%></li>
                </ul>
            </div>
                <div class="User-box">
                    <table>
                        <tbody>
                        <tr><td><h6>Order date</h6></td>
                            <td><span><%=purchases.get(i).getCreationDate()%></span></td></tr>
                        <tr><td><h6>Country</h6></td>
                            <td><span><%=purchases.get(i).getCountry()%></span></td></tr>
                        <tr><td><h6>Postal Code</h6></td>
                            <td><span><%=purchases.get(i).getCap()%></span></td></tr>
                        <tr><td><h6>City</h6></td>
                            <td><span><%=purchases.get(i).getCity()%></span></td></tr>
                        <tr><td><h6>Address</h6></td>
                            <td><span><%=purchases.get(i).getAddress()%></span></td></tr>
                        <tr><td><h6>Telephone Number</h6></td>
                            <td><span><%=purchases.get(i).getCellphonenumber()%></span></td></tr>
                        </tbody>
                    </table>

                </div>


    </div>

    <%}%>
    </div>
    </div>

</div>




<jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</body>

<script>
    window.onload(function (){
        if($("#new").val()=="true")
            createToast("Success","Purchase")
    })
</script>
</html>
