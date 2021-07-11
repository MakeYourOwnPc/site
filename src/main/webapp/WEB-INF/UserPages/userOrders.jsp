<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <c:forEach items="${builds}" var="build">
            <div class="buildList">
                <img src="${build.imagePath}">
                <ul>
                    <li>${build.type}</li>
                    <li>${build.gpu}</li>
                    <li>${build.cpu}</li>
                    <li>${build.mobo}</li>
                    <li><ul>
                        <c:forEach items="${build.memories}" var="memory">
                            ${memory}
                        </c:forEach></ul></li>
                    <li>${build.pcCase}</li>
                    <li>${build.psu}</li>
                    <li>${build.maker}</li>

                </ul>
                <form action="./showBuild">
                    <input name="id" value="${build.id}" type="hidden">
                    <input type="submit" class="btn fullBtn btn-success" value="Start ">
                </form>
            </div>
        </c:forEach>
    </div>

</div>




<jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</body>

</body>
</html>
