package servlets.Comment;

import modals.Comment;
import modals.Post;
import modals.User;
import repos.CommentsRepos;
import repos.PostRepos;
import repos.UserRepos;
import utilities.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/comment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("in comments get");
        req.setAttribute("POST",PostRepos.findbyId(Long.parseLong(req.getParameter("POST"))));
        req.setAttribute("user", UserRepos.findbyId(Long.parseLong(req.getParameter("USER")))  );
   req.getRequestDispatcher("/views/profile/details.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("comment_text");
        User user = UserRepos.findbyId(Long.parseLong(req.getParameter("USER")));
        Post post = PostRepos.findbyId(Long.parseLong(req.getParameter("POST")));
        if(InputValidator.validate(text)){
            Comment comment = new Comment(null, post,(User)req.getSession().getAttribute("USER"),text);
            CommentsRepos.addComment(comment);
        }
        resp.sendRedirect("/comment?POST=" + post.getId() + "&USER=" + user.getId());
    }
}
