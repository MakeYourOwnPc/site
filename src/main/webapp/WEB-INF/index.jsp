<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <title>Make Your Own Pc</title>
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
    <div class="fullHeightFooter">

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

                </ul>
                    <form action="./showBuild" style="bottom: 0;position: absolute;width: 100%;">
                        <input name="id" value="${build.id}" type="hidden">
                        <input type="submit" class="btn fullBtn btn-success" value="Start ">
                    </form>
                </div>
            </c:forEach>
        </div>

    </div>




           <jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
    </div>
    </body>
<script>

</script>
</html>