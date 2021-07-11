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
<div id="pageContenent">
    <div>
        <h1 style="padding: 10px;background-color: rgba(0,0,0,0.3); text-align: center">
            Welcome to our Site, Start to Build Your New Computer Starting from Our Choices
        </h1>
    </div>
    <div id="builds">
        <c:set var="length" value="${fn:length(builds)}"/>

        <c:forEach begin="0" end="length" var="i">
            <div class="box-container">
            <div class="buildList">
                <img src="${builds[i].imagePath}">
                <ul>
                    <li>${builds[i].type}</li>
                    <li>${builds[i].gpu}</li>
                    <li>${builds[i].cpu}</li>
                    <li>${builds[i].mobo}</li>
                    <li><ul>
                        <c:forEach items="${builds[i].memories}" var="memory">
                            ${memory}
                        </c:forEach></ul></li>
                    <li>${builds[i].pcCase}</li>
                    <li>${builds[i].psu}</li>
                    <li>${builds[i].maker}</li>
                </ul>
            </div>
                <div class="User-box">
                    <table>
                        <tbody>
                        <tr><td><h6>Order date</h6></td>
                            <td><span>${purchases[i].creationDate.year}-${purchases[i].creationDate.month}-${purchases[i].creationDate.day}</span></td></tr>
                        <tr><td><h6>Country</h6></td>
                            <td><span>${purchases[i].country}</span></td></tr>
                        <tr><td><h6>Postal Code</h6></td>
                            <td><span>${purchases[i].cap}</span></td></tr>
                        <tr><td><h6>City</h6></td>
                            <td><span>${purchases[i].city}</span></td></tr>
                        <tr><td><h6>Address</h6></td>
                            <td><span>${purchases[i].address}</span></td></tr>
                        <tr><td><h6>Telephone Number</h6></td>
                            <td><span>${purchases[i].cellphonenumber}</span></td></tr>
                        </tbody>
                    </table>

                </div>


    </div>
    </div>
    </c:forEach>

</div>




<jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</body>

</body>
</html>
