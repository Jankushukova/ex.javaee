package servlets;

import modals.Post;
import modals.User;
import repos.FriendRepos;
import repos.LikesRepos;
import repos.PostRepos;
import repos.UserRepos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet("/news")
public class News extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<User> friends = FriendRepos.getFriends((User)req.getSession().getAttribute("USER"));
        ArrayList<Post> posts = new ArrayList<>();
        for(User friend:friends){
            for(Post post:PostRepos.getAllPosts(friend)){
                posts.add(post);
            }
        }
        Collections.shuffle(posts);
        req.setAttribute("POSTS",posts);
        req.setAttribute("likesrep",new LikesRepos());


        req.getRequestDispatcher("/views/news/news.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
