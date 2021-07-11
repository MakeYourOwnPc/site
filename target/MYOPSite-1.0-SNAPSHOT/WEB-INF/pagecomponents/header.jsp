<%@ page import="Model.User.User" %>
<% User user= (User) session.getAttribute("user");%>
<script>
    document.onload= function (){
        if($("#failedLogin").val()==true) createToast("Login Error","Email or Password incorrect");
    }
    function loggedToEnterCart(){
        if($("#loggedHeader").val()=="")
            createToast("Not Logged In","Cannot Visualize Cart If Not Logged In")
        else
            window.location.href="/MYOPSite_war_exploded/showCart"
    }
</script>

<nav class="navbar navbar-expand-lg navbar-dark  header">
    <div class="container-fluid">
        <a class="navbar-brand" href="/MYOPSite_war_exploded/">Make Your Own Pc</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/MYOPSite_war_exploded/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/MYOPSite_war_exploded/build.jsp">Build</a>
                </li>
                <li class="nav-item dropdown ">
                    <a class="nav-link dropdown-toggle  <%if(user==null){%> disabled <%}%>" <%if(user==null){%> aria-disabled="true" <%}%> href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        User Managment
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <li><a class="dropdown-item" href="/MYOPSite_war_exploded/showBuilds">Saved Builds</a></li>
                        <li><a class="dropdown-item" href="/MYOPSite_war_exploded/oldOrders">Old Orders</a></li>
                        <li><a class="dropdown-item" href="/MYOPSite_war_exploded/showUser">User Data</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <%if(user!=null&& user.isAdmin()){%>
                        <li><a class="dropdown-item" href="/MYOPSite_war_exploded/admin">Admin Page</a></li>
                        <%}%>
                    </ul>
                </li>
            </ul>
            <button class="icon-container" onclick=" loggedToEnterCart()" style="height: 3rem">
                <%@include file="/icons/cart.svg"%>
                <input type="hidden" name="loggedHeader" id="loggedHeader" value="${user.email}">
            </button>
            <%if(user==null){%>
            <form class="d-flex" method="post" action="login">
                <input class="form-control" type="email" placeholder="Email" name="email">
                <input class="form-control" type="password" placeholder="Password" name="password">
                <button class="btn active" type="submit">Log In</button>
                <a class="btn optional" href="/MYOPSite_war_exploded/registrationPage.jsp">Register Now</a>
            </form>
            <%}else{%>
            <div>
            <span class="navbar-text"> Welcome <%=user.fullName()%></span>
                 <form action="/MYOPSite_war_exploded/logout" style="display: inline-block;height: 3rem;">
                   <button class="icon-container"  ><%@include file="/icons/exit_door.svg"%> </button>
                 </form>
            </div>

            <%}%>
        </div>
    </div>
    <%if (request.getAttribute("failedLogin")!=null&&(boolean)request.getAttribute("failedLogin")) {%>
    <input type="hidden" id="failedLogin" value="true">
    <%}else{%>
    <input type="hidden" id="failedLogin" value="false">
    <%}%>
</nav>