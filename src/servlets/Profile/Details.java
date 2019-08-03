package servlets.Profile;

import modals.Comment;
import modals.Post;
import modals.User;
import repos.CommentsRepos;
import repos.PostRepos;
import repos.UserRepos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/details")
public class Details extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("POST",PostRepos.findbyId(Long.parseLong(req.getParameter("POST"))));
        req.setAttribute("user", UserRepos.findbyId(Long.parseLong(req.getParameter("USER")))  );


        req.getRequestDispatcher("/views/profile/details.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("in details post");
        String text= req.getParameter("post");
//        System.out.print(text);
        User user = UserRepos.findbyId(Long.parseLong(req.getParameter("USER")));
        Post post = PostRepos.findbyId(Long.parseLong(req.getParameter("POST")));

        if(!req.getParameter("type").equals("comment")){
            PostRepos.updatePost(post,text);
        }
        else{
//            System.out.println("changing comment");
            Comment com = CommentsRepos.findbyId(Long.parseLong(req.getParameter("COMMENT")));
            CommentsRepos.changeComment(com,text);
        }
        resp.sendRedirect("/details?POST=" + post.getId() + "&USER=" + user.getId());


    }
}
