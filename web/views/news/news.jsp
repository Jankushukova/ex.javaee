<%@ page import="modals.User" %>
<%@ page import="db.DBManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modals.Post" %>
<%@ page import="java.util.Collections" %>
<%@ page import="repos.PostRepos" %>
<%@ page import="repos.LikesRepos" %>
<%@ page import="repos.CommentsRepos" %><%--
  Created by IntelliJ IDEA.
  User: Владелец
  Date: 20.07.2019
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/parts/nav.jsp"%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/profile/profile.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous"></head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<%
User user = (User)session.getAttribute("USER");
ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("POSTS");
%>
<div class="container" >
        <div id="demo" class="carousel slide p-0" data-ride="carousel">

    <!-- Indicators -->
            <ul class="carousel-indicators">
                <li data-target="#demo" data-slide-to="0" class="active"></li>
                <li data-target="#demo" data-slide-to="1"></li>
                <li data-target="#demo" data-slide-to="2"></li>
            </ul>

            <!-- The slideshow -->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="/images/news/1.jpg" alt="Los Angeles" width="1100" height="500">
                </div>
                <div class="carousel-item">
                    <img src="/images/news/2.jpg" alt="Chicago" width="1100" height="500">
                </div>

            </div>

    <!-- Left and right controls -->
            <a class="carousel-control-prev" href="#demo" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#demo" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>
        </div>
    <%for (Post post:posts){
    User usofpst = PostRepos.getUserofPost(post);
    %>
    <div class="row d-flex justify-content-center mt-5" id="lenta">

        <div class="p-3 w-100 shadow bg-light rounded-lg" style="border-bottom: 2px solid #f06060;">
            <a href="/details?POST=<%=post.getId()%>&USER=<%=usofpst.getId()%>" style="color: inherit;">
                <h2><%=post.getTitle()%></h2></a>

            <h5><%=usofpst.getFullName()%></h5>
            <h5><%=post.getDate()%></h5>

            <div class="fakeimg" style="height:200px;">Image</div>
            <%--                <p>Some text..</p>--%>
            <p><%=post.getText()%></p>
            <div class="row">
                <div class="colomn">
                    <%
                        int likesize = LikesRepos.getLikedBy(post).size();
                        if (likesize == 0) {%>
                    <a onclick="likeCar(<%=post.getId()%>,<%=user.getId()%>)" ><i id = "<%=post.getId()%>" class="far fa-heart"><%=likesize%></i></a>
                    <%} else if(likesize>0){%>
                    <a onclick="likeCar(<%=post.getId()%>, <%=user.getId()%>)" ><i id = "<%=post.getId()%>" class="fas fa-heart"><%=likesize%></i></a>

                    <%}%>
                </div>


                <div class="colomn ml-2">
                    <%
                        int commentsize = CommentsRepos.getAllComments(post).size();

                        if(commentsize==0){
                    %>
                    <a href="/details?POST=<%=post.getId()%>&USER=<%=user.getId()%>"><i class="far fa-comment"><%=commentsize%></i></a>
                    <%}else if(commentsize>0){%>
                    <a href="/details?POST=<%=post.getId()%>&USER=<%=user.getId()%>"><i class="fas fa-comment"><%=commentsize%></i></a>
                    <%}%>
                </div>
            </div>
        </div>


    </div>
    <%} %>
</div>

</body>
<script>


    function likeCar(id, ufn){
        $.ajax({
            url: '/like',
            data: {
                id: id,
                ufn: ufn,
            },
            type: 'get',
            success: function(resp){
                var size  = resp;
                if(size==0){
                    document.getElementById(id).className = "far fa-heart";
                    document.getElementById(id).innerText = size;

                }
                else if(size>0){
                    document.getElementById(id).className = "fas fa-heart";
                    document.getElementById(id).innerText = size;

                }
            },
            error: function(err){
            }
        });
    }
</script>
</html>
