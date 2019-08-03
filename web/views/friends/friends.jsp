<%@ page import="modals.User" %>
<%@ page import="db.DBManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="repos.LastAccessTimeRepos" %>
<%@include file="/views/parts/nav.jsp"%>


<%--
  Created by IntelliJ IDEA.
  User: Владелец
  Date: 12.07.2019
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->

    <title>Friends</title>
    <link rel="stylesheet" href="/css/profile/profile.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous"></head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<%
User user = (User)session.getAttribute("USER");
ArrayList<User> friends = (ArrayList<User>)session.getAttribute("FRIEND");
ArrayList<User> nefriends = (ArrayList<User>)session.getAttribute("NEFRIEND");

%>
    <div class="container bg-light mt-3 shadow">
        <h2>Friend List</h2>
        <p>Type name in the input field to search the list for friends:</p>
        <input class="form-control" id="myInput" type="text" placeholder="Search.." name="friendname">
        <br>
        <ul class="list-group" id="myList">
            <%for (User f:friends){%>
            <a href="/profile?FRIEND=<%=f.getFullName()%>"><li class="list-group-item"><%=f.getFullName()%><%if( (new LastAccessTimeRepos()).isOnline(f)){%>
                <i class='fas fa-eye'></i>
            <%}%></li></a>
            <%}%>
            <%for (User f:nefriends){%>

            <a href="/profile?FRIEND=<%=f.getFullName()%>&NEFRIEND=<%=1%>"><li class="list-group-item" style="display: none;"><%=f.getFullName()%><%if( (new LastAccessTimeRepos()).isOnline(f)){%>
                <i class='fas fa-eye'></i>
                <%}%></li></a>
            <%}%>



        </ul>
    </div>




</body>
<script>
    $(document).ready(function(){
        $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#myList li").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</html>
