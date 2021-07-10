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
    <jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
    <script src="./jslibraries/jQuery.js"defer></script>


           <!--  <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="..." class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>First slide label</h5>
                            <p>Some representative placeholder content for the first slide.</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="..." class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Second slide label</h5>
                            <p>Some representative placeholder content for the second slide.</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="..." class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Third slide label</h5>
                            <p>Some representative placeholder content for the third slide.</p>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div> -->
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
                        <input name="idBuild" value="${build.id}" type="hidden">
                        <input type="submit" class="btn fullBtn btn-success" value="Start ">
                    </form>
                </div>
            </c:forEach>
        </div>

    </div>




           <jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
    </body>
<script>

</script>
</html>