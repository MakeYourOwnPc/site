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
<style>
    tr:nth-child(2n) {
        background: rgba(0,0,0,0.4);
    }
</style>

<div id="pageContenent">
    <div>
        <h1 style="padding: 10px;background-color: rgba(0,0,0,0.3); text-align: center">
            Your Shopping Cart
        </h1>
    </div><c:choose>
    <c:when test="${build.id!=0}">
        <div id="cartContainer">
         <div class="halfPage">
            <div class="buildList">

            <img src="./images/${build.imagePath}">
            <ul>
                <li>${build.type}</li>
                <li>${build.gpu}</li>
                <li>${build.cpu}</li>
                <li>${build.mobo}</li>
                <li><ul>
                    <c:forEach items="${build.memories}" var="memory">
                       <li>${memory}</li>
                    </c:forEach></ul></li>
                <li>${build.pcCase}</li>
                <li>${build.psu}</li>


            </ul>
            </div>
         </div>
            <div class="halfPage">
                <table><tbody>
                <tr> <td><label for="telephoneNumber">Insert Telephone Number</label></td></tr>
                <tr> <td><input type="tel" id="telephoneNumber" name="cellphone" maxlength="13" required></td></tr>
                <tr> <td><label for="address">Insert Address</label></td></tr>
                <tr> <td><input type="text" id="address" name="address"maxlength="40" required></td></tr>
                <tr> <td><label for="city">Insert City</label></td></tr>
                <tr> <td> <input type="text" id="city" name="city" maxlength="40" required></td></tr>
                <tr> <td> <label for="CAP">Insert CAP</label></td></tr>
                <tr><td> <input type="text" id="CAP" name="cap"maxlength="6" required></td></tr>
                <tr><td> <label for="country">Select Your Country</label></td></tr>
                <tr><td> <select id="country" name="country" required>
                    <c:forEach var="country" items="${countries}">
                        <option value="${country.id}"> ${country.name}</option>
                    </c:forEach></select></td></tr>
                <tr><td></td></tr>
                <tr><td><button onclick="purchase()" class="btn active">Complete Purchase</button></td></tr>
                </tbody>
                </table>


            </div>

        </div>

    </c:when>
    <c:when test="${build.id==0}">

        <h1>Empty Cart</h1>
    </c:when>
</c:choose>


</div>
<jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</body>
<script>
    function purchase(){
        if(document.getElementById("address").checkValidity()||document.getElementById("telephoneNumber").checkValidity()||
            document.getElementById("city").checkValidity()||document.getElementById("CAP").checkValidity()||document.getElementById("country").checkValidity()){
           let formData={
                'address':$("#address").val(),
                'cellphone':$("#telephoneNumber").val(),
                'city':$("#city").val(),
                'cap':$("#CAP").val(),
                'country':$("#country").val(),
            }
            formData= JSON.stringify(formData);
            $.ajax({
                contentType:"application/x-www-form-urlencoded",
                data: "purchase="+formData,
                url: "/MYOPSite_war_exploded/makePurchase",
                type: 'POST',
                success:function (){
                    createToast("Success","Purchase Complete");
                    setTimeout(window.location.replace("/MYOPSite_war_exploded/oldOrders"),5000);
                },
                failed:function (){
                  createToast("Error","Cannot Complete Purchase")
                }
            })
            }else{
            createToast("Error","Not All Fields Complete")
        }

    }

</script>


</html>
