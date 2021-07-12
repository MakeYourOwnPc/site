<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 07/07/2021
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>MYOC-PersonalBuild</title>
    <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="customcss/general.css"/>
</head>
<body>

<style>
    tr:nth-child(2n) {
        background: rgba(0,0,0,0.4);
    }
</style>

<script src="bootstrap/js/bootstrap.js" defer></script>
<script src="bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="jslibraries/jQuery.js"></script>
<script src="jslibraries/utilities.js"></script>
<div class="fullHeightFooter">
<div id="pageContenent">
    <div>
        <h1 style="padding: 10px;background-color: rgba(0,0,0,0.3); text-align: center">
            Your old builds
        </h1>
    </div>
    <div id="builds">

            <c:forEach items="${builds}" var="build">
                <div class="buildList" id="build${build.id}">
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
                    <table style="position: absolute;bottom: 0;">
                        <tbody>
                      <tr> <td style="width: 50%;height: 100%;">
                          <form action="./showBuild" style="height: 100%">
                            <input name="id" value="${build.id}" type="hidden">
                            <input type="submit" class="btn btn-success" style="height: 100%;width: 100%" value="Open">

                        </form>
                      </td>
                          <td style="width: 50%;height: 100%;">
                              <button class="btn btn-danger" style="height: 100%;width: 100%" onclick="deleteBuild(${build.id})">Delete</button>
                          </td>

                      </tr>
                        </tbody>
                    </table>
                </div>
            </c:forEach>

    </div>

</div>
</div>
<jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>

</body>
<script>
    function deleteBuild(id){
        $("#build"+id).addClass("pendingRemoval");
        let idBuild=id;
        $.ajax({
            url: "/MYOPSite_war_exploded/deleteBuild",
            type:"POST",
            data:"id="+id,
            success:function (){
                $(".pendingRemoval").remove();
                createToast("Success","Delete finalized")
            },
            failed:createToast("Error","Cannot Delete")
        })
    }
</script>
</html>
