<%@ page import="Model.User.User" %>
<% User user= (User) session.getAttribute("user");
%>

<nav class="navbar navbar-expand-lg navbar-dark  header">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Make Your Own Pc</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-md-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/MYOPSite_war_exploded/build">Build</a>
                </li>
                <li class="nav-item dropdown ">
                    <a class="nav-link dropdown-toggle  <%if(user==null){%> disabled <%}%>" <%if(user==null){%> aria-disabled="true" <%}%> href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        User Managment
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <li><a class="dropdown-item" href="#">Saved Builds</a></li>
                        <li><a class="dropdown-item" href="#">Old Orders</a></li>

                        <li><hr class="dropdown-divider"></li>
                        <%if(user!=null&& user.isAdmin()){%>
                        <li><a class="dropdown-item" href="/MYOPSite_war_exploded/admin">Admin Page</a></li>
                        <%}%>
                    </ul>
                </li>
            </ul>
            <%if(user==null){%>
            <form class="d-flex" method="post" action="login">
                <input class="form-control" type="email" placeholder="Email" name="email">
                <input class="form-control" type="password" placeholder="Password" name="password">
                <button class="btn active" type="submit">Log In</button>
                <a class="btn optional" href="/MYOPSite_war_exploded/registrationPage.jsp">Register Now</a>
            </form>
            <%}else{%>
            <span class="navbar-text"> Welcome <%=user.fullName()%>
                 <form action="/MYOPSite_war_exploded/logout" style="display: inline-block;height: 3rem;">
                   <button class="icon-container"  ><%@include file="/icons/exit_door.svg"%> </button>
                 </form>
            </span>
            <%}%>
        </div>
    </div>
    <%if (request.getAttribute("loginError")!=null&&(boolean)request.getAttribute("loginError")) {%>
    <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
            <strong class="me-auto">Login Error</strong>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
            Email Or Password Is Incorrect
        </div>
    </div>

    <%}%>
</nav>