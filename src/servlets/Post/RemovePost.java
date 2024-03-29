package servlets.Post;

import modals.Post;
import repos.CommentsRepos;
import repos.PostRepos;
import repos.UserRepos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/removepost")
public class RemovePost extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = PostRepos.findbyId(Long.parseLong(req.getParameter("id")));
        PostRepos.removePost(post);
        PrintWriter out = resp.getWriter();
        out.print(CommentsRepos.getAllComments(post).size());

    }
}
