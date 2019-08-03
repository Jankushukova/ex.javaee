<%--
  Created by IntelliJ IDEA.
  User: Владелец
  Date: 09.07.2019
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
  <head>
    <title>Login</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


  </head>
  <body >
  <%
    System.out.println("in index");
    if(session.getAttribute("USER")!=null){
      response.sendRedirect("/profile");
      return;
    }

  %>
  <div class="d-flex justify-content-center mt-5">
    <div  class="container align-content-center shadow bg-light p-3 rounded-lg w-25 mt-5" style="border-bottom: 2px solid #f06060;;">
    <form action="/login" method="post" class="was-validated">
      <%if(request.getParameter("error")!=null){%>
      <div class="alert alert-danger alert-dismissible">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Error!</strong> Something bad happened!</div>
      <%}%>
      <div class="form-group">
        <label for="uname" class="text-light">Username:</label>
        <input type="text" class="form-control" id="uname" placeholder="Enter username" name="username" required>
        <div class="valid-feedback">Valid.</div>
        <div class="invalid-feedback">Please fill out this field.</div>
      </div>
      <div class="form-group">
        <label for="pwd" class="text-light">Password:</label>
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password" required>
        <div class="valid-feedback">Valid.</div>
        <div class="invalid-feedback">Please fill out this field.</div>
      </div>
      <a href="/views/auth/register.jsp" class="form-group form-check">
        <label>
          Create account?
        </label>
      </a>
      <button type="submit" class="btn btn-light" style="background: #f06060; color: white;">Submit</button>
    </form>
  </div>
  </div>
  </body>
  <!--===============================================================================================-->

</html>
