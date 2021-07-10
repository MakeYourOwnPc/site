<%--
  Created by IntelliJ IDEA.
  User: Esterno
  Date: 10/07/2021
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<% response.addHeader("Set-Cookie", "Secure; SameSite=strict");%>

<html>
<head>
  <title>MYOC-Registration</title>
  <meta name="viewport" content="width=device-witdht, initial-scale=1.0"/>
  <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="customcss/general.css"/>
</head>
<body>
<script src="bootstrap/js/bootstrap.js" defer></script>
<script src="bootstrap/popper.js" defer></script>
<jsp:include page="/WEB-INF/pagecomponents/header.jsp"></jsp:include>
<script src="jslibraries/jQuery.js"></script>
<script src="jslibraries/utilities.js"></script>


<div class="centered-box">
  <div class="box-container fullHeightList">
    <h1>Log In</h1>
    <form name="registration" action="/MYOPSite_war_exploded/" method="post">
      <table  class="User-box">
        <tr><td>
          <label for="firstname">Firstname</label>
        </td><td>
          <input type="text" name="firstname" id="firstname"  required>
          <span id="firstname-alert" class="alert-info " hidden> FirstName not Inserted</span>
        </td></tr>
        <tr><td>
          <label for="lastname">LastName</label>
          <td>
            <input type="text" name="lastname" id="lastname"  required>
        <tr>
          <td></td>
          <td>
            <span  id="lastname-alert" class="alert-info " hidden> LastName not Inserted</span>
          </td>
        </tr>


        </td></tr>
        <tr><td>
          <label for="email">Email</label>

          <td>
            <input type="email" name="email" id="email" onfocusout="existingEmail()"  required>
            <span id="email-alert" class="alert-info " hidden> Incorrect Email</span>
          </td></tr>
        <tr>
        <tr><td>
          <label for="password">Password</label>
          <td>
            <input id="password" type="password" name="password" id="password" required>
            <span id="password-alert" class="alert-info " hidden>Password Not Inserted</span>
          </td></tr>
        <tr>
          <td>
          </td>
        </tr>
        <tr><td>
          <input class="btn active" type="submit" value="Register Now" id="submit-registration">
        </td></tr>
      </table>

    </form>

  </div>

</div>
<jsp:include page="/WEB-INF/pagecomponents/footer.jsp"></jsp:include>
</body>

<script>



</script>
</html>
