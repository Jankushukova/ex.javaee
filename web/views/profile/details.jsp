<%@ page import="javafx.geometry.Pos" %>
<%@ page import="modals.Post" %>
<%@ page import="repos.LikesRepos" %>
<%@ page import="modals.User" %>
<%@ page import="repos.CommentsRepos" %>
<%@ page import="modals.Comment" %>
<%@ page import="repos.PostRepos" %><%--
  Created by IntelliJ IDEA.
  User: Владелец
  Date: 27.07.2019
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/views/parts/nav.jsp"%>

<html>
<head>
    <title>Details</title>
<%--    <link rel="stylesheet" href="/css/profile/profile.css">--%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous"></head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
    #previous {
        overflow: hidden;
        /*background-color: #333;*/
    }

    #body {
        padding: 16px;
    }

    .sticky {
        position: fixed;
        top: 0;
        /*width: 100%;*/
    }

    .sticky + #body {
        padding-top: 100px;
    }
    i:hover{
        cursor: pointer;
    }

    a {
        text-decoration: none;
        display: inline-block;
        padding: 8px 16px;
    }



    .previous {
        background-color: #f1f1f1;
        color: black;
    }


    .round {
        border-radius: 50%;
    }
</style>
</head>
<%
    Post post = (Post)request.getAttribute("POST");
    User user = (User)request.getAttribute("user");
%>
<body>
<a style="margin: 50px;" href="/profile" class="previous round" id="previous">&#8249;</a>

    <div class="container bg-light" id="body">
        <div class="row d-flex justify-content-center mt-5" id="lenta">
            <div class="card bg-light p-3 mt-3 w-100">
                <div class="row">
                    <h2><%=post.getTitle()%></h2>
                    <i onclick="configPost(<%=post.getId()%>)" class="fas fa-cogs ml-2" ></i>
                    <i onclick="editPost()" style="visibility: hidden" class="far fa-edit ml-2" id="edit"></i>
                    <i onclick="removePost(<%=post.getId()%>,<%=user.getId()%>)" style="visibility: hidden" class="far fa-trash-alt ml-2" id="delete"></i>

                </div>
                <a href="/profile?FRIEND=<%=user.getFullName()%>"><h5><%=PostRepos.getUserofPost(post).getFullName()%></h5></a>

                <h5><%=post.getDate()%></h5>
                <div class="fakeimg" style="height:200px;">Image</div>

                    <div id="posttext">
                        <p id="text" ><%=post.getText()%></p>

                    </div>
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
                        <i class="far fa-comment" id="comment"><%=commentsize%></i>
                        <%}else if(commentsize>0){%>
                        <i class="fas fa-comment" id="comment"><%=commentsize%></i>
                        <%}%>
                    </div>
                </div>












                <div class="d-flex justify-content-center mt-5 " id="collap" >
                    Add comment
                </div>

                <div class="content mb-3" style="display: none;">
                    <div class="container">
                        <form action="/comment?POST=<%=post.getId()%>&USER=<%=user.getId()%>" method="post">

                            <div class="row">

                                <textarea id="subject" name="comment_text" placeholder="Write something.." style="height:200px"></textarea>
                            </div>
                            <div class="row">
                                <input type="submit" value="Submit">
                            </div>
                        </form>
                    </div>
                </div>


























                    <%for(Comment comment:
                            CommentsRepos.getAllComments(post)){%>
                        <div class="row d-flex justify-content-center mt-5" id="<%=comment.getId()%>">
                            <div class="card bg-light p-3 mt-3 w-100">
                                <div class="row">
                                    <h3><%=comment.getOwner().getFullName()%></h3>
                                    <i onclick="configComment(<%=comment.getId()%>)" class="fas fa-cogs ml-2" ></i>
                                    <i onclick="editComment(<%=comment.getId()%>)" style="visibility: hidden" class="far fa-edit ml-2" id="editcomment"></i>
                                    <i onclick="removeComment(<%=comment.getId()%>)" style="visibility: hidden" class="far fa-trash-alt ml-2" id="deletecomment"></i>

                                </div>
                                <div id="commenttext">
                                    <h6><%=comment.getDescription()%></h6>
                                </div>

                            </div>
                        </div>
                    <%}%>

            </div>


        </div>
    </div>

</body>
<script>
    var coll = document.getElementById("collap");
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




    function removePost(id,ufn){
        $.ajax({
            url: '/removepost',
            data: {
                id:id,
                ufn:ufn
            },
            type: 'post',
            success: function(){
                alert("Post is successfully deleted");
                document.getElementById("body").style.display = "none";


            },
            error: function(err){
            }
        });
    }


    function configPost(post){
        var edit = document.getElementById("edit").style.visibility;
        var remove = document.getElementById("delete");
        if (edit.style.visibility === "visible" && remove.style.visibility == "visible") {
            edit.style.visibility = "hidden";
            remove.style.visibility = "hidden";
        } else {
            edit.style.visibility = "visible";
            remove.style.visibility = "visible";
        }


    }
    function editPost() {
        var div = document.getElementById("posttext");
        div.innerHTML = "<form action='/details?POST=<%=post.getId()%>&USER=<%=user.getId()%>' method='post'><textarea name = 'post'></textarea><button type='submit' style='background: #f06060; color: white;'>Save changes</button></form>";

    }




    function removeComment(id,ufn){
        $.ajax({
            url: '/removecomment',
            data: {
                id:id,
                ufn:ufn
            },
            type: 'post',
            success: function(resp){
                alert("Comment is successfully deleted");
                document.getElementById(id).innerHTML = "";
                if(resp==0){
                    document.getElementById("comment").className = "far fa-comment";

                }
                else if(resp>0){
                    document.getElementById("comment").className = "fas fa-comment";

                }
                document.getElementById("comment").innerText = resp;


            },
            error: function(err){
            }
        });
    }


    function configComment(comment){
        document.getElementById("editcomment").style.visibility = "visible";
        document.getElementById("deletecomment").style.visibility = "visible";


    }
    function editComment(comment) {
        var div = document.getElementById("commenttext");
        div.innerHTML = "<form action='/details?POST=<%=post.getId()%>&COMMENT="+comment+"&type=comment&USER=<%=user.getId()%>' method='post'><textarea name = 'post'></textarea><button type='submit' style='background: #f06060; color: white;'>Save changes</button></form>";

    }




    window.onscroll = function() {myFunction()};

    var navbar = document.getElementById("previous");
    var sticky = navbar.offsetTop;

    function myFunction() {
        if (window.pageYOffset >= sticky) {
            navbar.classList.add("sticky")
        } else {
            navbar.classList.remove("sticky");
        }
    }







    $(document).ready(function(){
        $('.toast').toast('show');
    });
</script>
</html>

