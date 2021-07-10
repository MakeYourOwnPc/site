<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.addHeader("Set-Cookie", "Secure; SameSite=strict");%>

<html>
<head>
    <title>MYOC-YourCart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="customcss/general.css"/>
</head>
<body>
<script src="bootstrap/js/bootstrap.js" defer></script>
<script src="bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="jslibraries/jQuery.js"></script>
<script src="jslibraries/utilities.js"></script>

<div id="pageContenent">
    <div>
        <h1 style="padding: 10px;background-color: rgba(0,0,0,0.3); text-align: center">
            Your Shopping Cart
        </h1>
    </div>
    <div class="halfPage">

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
            </div>
            <div class="halfPage">
                <form id="purchaseForm"><table><tbody>
                <tr> <td><label for="telephoneNumber">Insert Telephone Number</label></td></tr>
                <tr> <td><input type="tel" id="telephoneNumber" name="tel"></td></tr>
                <tr> <td><label for="address">Insert Address</label></td></tr>
                <tr> <td><input type="text" id="address" name="address"></td></tr>
                <tr> <td><label for="city">Insert City</label></td></tr>
                <tr> <td> <input type="text" id="city" name="city"></td></tr>
                <tr> <td> <label for="CAP">Insert CAP</label></td></tr>
                <tr><td> <input type="text" id="CAP" name="cap"></td></tr>
                <tr><td> <label for="country">Select Your Country</label></td></tr>
                <tr><td> <select id="country"></select></td></tr>
                    <c:forEach var="country" items="${contries}">
                        <option value="${country.id}"> ${country.name}</option>
                    </c:forEach></td></tr>
                <tr><td></td></tr>
                <tr><td><input type="submit" value="Complete Purchase"></td></tr>
                </tbody>
                </table>
                </form>

            </div>

        </div>


</div>
<jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</body>
<script>

</script>

</html>
