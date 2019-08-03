package servlets.Post;

import db.DBManager;
import modals.Post;
import modals.User;
import repos.FriendRepos;
import repos.LikesRepos;
import repos.PostRepos;
import repos.UserRepos;

import javax.imageio.metadata.IIOMetadataNode;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session =request.getSession();
        User user = (User)session.getAttribute("USER");
        User page_user = UserRepos.findbyId(Long.parseLong(request.getParameter("ufn")));
        String id = request.getParameter("id");

        Post p = PostRepos.findbyId(Long.parseLong(id));
           if(p!=null){
                    if (!LikesRepos.getLikedBy(p).contains(user.getId())) {
                        LikesRepos.addLikedBy(p,user);
                    }
                    else{
                            LikesRepos.removeLikedBy(p,user);
                    }
                }
        PrintWriter out = response.getWriter();
        out.print(LikesRepos.getLikedBy(p).size());


    }
}
