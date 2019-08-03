<%@include file="/views/parts/nav.jsp"%>

<%@ page import="java.io.PrintWriter" %>
<%@ page import="modals.User" %>
<%@ page import="javafx.geometry.Pos" %>
<%@ page import="modals.Post" %>
<%@ page import="db.DBManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="repos.LikesRepos" %>
<%@ page import="repos.FriendRepos" %>
<%@ page import="repos.LastAccessTimeRepos" %>
<%@ page import="repos.CommentsRepos" %>

<%--
  Created by IntelliJ IDEA.
  User: Владелец
  Date: 10.07.2019
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>


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
                                    User user = (User)session.getAttribute("USER") ;
                                    User friend = (User)request.getAttribute("FRIEND");
                                    ArrayList<Post> posts = (ArrayList<Post>) request.getAttribute("POSTS");
                                    ArrayList<User> notifications = (ArrayList<User>) request.getAttribute("NOTIFICATIONS");
                                    if(friend!=null){
                                        user = friend;
                                    }
//                                    System.out.println(friend.getFullName());

                                %>
                                <div class="p-3 w-100" >
                                    <div class="row d-flex justify-content-center ">
                                        <h1 class="col-75 text-light"><%=user.getFullName()%></h1>
                                        <%if( LastAccessTimeRepos.isOnline(user)){%>
                                        <h6 class="col-25 fon text-light">Online</h6>
                                        <%}%>
                                    </div>


                                    <div class="d-flex justify-content-center mt-5">

                                        <div class="flip-card ">
                                            <div class="flip-card-inner">
                                                <div class="flip-card-front">
                                                    <img src="../../images/profile/1.jpg" alt="Avatar" style="width:300px;height:300px;">
                                                </div>
                                                <div class="flip-card-back text-light">
                                                    <p><%=user.getEmailaddress()%></p>
                                                    <p><%=user.getAddress()%></p>
                                                    <p><%=user.getPhoneNumber()%></p>
                                                    <p><%=user.getDate()%></p>

                                                </div>

                                            </div>
                                        </div>

                                    </div>

                                </div>
    <div class="container" >
            <%if(friend==null){%>
        <div class="d-flex justify-content-center mt-5 " id="collapsible" style="color: white;">
            Add post
        </div>

            <div class="content mb-3">
                <div class="container">
                    <form action="/profile" method="post">
                        <div class="row">
                            <label for="fname">Title</label>
                            <input type="text" id="fname" name="post_title" placeholder="News title..">
                        </div>


                        <div class="row">

                            <textarea id="subject" name="post_text" placeholder="Write something.." style="height:200px"></textarea>
                        </div>
                        <div class="row">
                            <input type="submit" value="Submit">
                        </div>
                    </form>
                </div>
            </div>
            <%if(notifications.size()>0 && friend==null){
                for(User f:notifications){%>
            <div class="container">

                <div class="toast" data-autohide="false">
                    <div class="toast-header">
                        <р6 class="mr-auto text-primary"></р6>
                        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast">&times;</button>
                    </div>
                    <div class="toast-body">
                        <p><%=f.getFullName()%> wants to add you to friends</p>
                        <button onclick="addfriend(1, <%=f.getId()%>)" type="button" class="btn btn-primary" data-dismiss="toast">Accept request</button>

                        <%--                                <button onclick="addfriend(1, <%=f.getId()%>)" type="button" class="btn btn-primary">Accept request</button>--%>

                    </div>
                </div>
            </div>

            <%}
            }%>
            <%} if(FriendRepos.neFriend((User)session.getAttribute("USER"),friend)){
                System.out.println("add to friend");
                System.out.println(FriendRepos.neFriend((User)session.getAttribute("USER"),friend));

            %>
            <button id="addfriend" onclick="addfriend(0,<%=friend.getId()%>)" class="collapsible mt-5">Add to friends</button>


            <%}%>






        <%for (Post post:posts){%>

                <div class="p-3 w-100 mb-2 shadow bg-light rounded-lg" style="border-bottom: 2px solid #f06060;">
                    <a href="/details?POST=<%=post.getId()%>&USER=<%=user.getId()%>" style="color: inherit;">
                        <h2><%=post.getTitle()%></h2></a>
                <h5><%=post.getDate()%></h5>
                <div class="fakeimg" style="height:200px;">Image</div>
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



        <%} %>

    </div>

</body>
<script>
    var coll = document.getElementById("collapsible");
    var i;

        coll.addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        });

    function likeCar(id,ufn){
        $.ajax({
            url: '/like',
            data: {
                id: id,
                ufn:ufn,

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




    function addfriend(text,id){
        $.ajax({
            url: '/addfriend',
            data: {
                text:text,
                id:id,
            },
            type: 'post',
            success: function(){
               document.getElementById("addfriend").style.display = "none";
            },
            error: function(err){
            }
        });
    }







    $(document).ready(function(){
        $('.toast').toast('show');
    });
</script>

</html>

