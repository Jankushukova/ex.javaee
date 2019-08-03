package servlets.Profile;

import db.DBManager;
import modals.Post;
import modals.User;
import repos.*;
import utilities.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet("/profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User friend = null;
        if(req.getParameter("FRIEND")!=null){
            friend = UserRepos.findbyName(req.getParameter("FRIEND"));
                if(friend!=null){
                    req.setAttribute("FRIEND",friend);
                    req.setAttribute("POSTS", PostRepos.getAllPosts(friend));
            }
        }
        else {
            req.setAttribute("NOTIFICATIONS", NotificationsRepos.getNotifications((User) session.getAttribute("USER")));
            req.setAttribute("POSTS", PostRepos.getAllPosts((User) session.getAttribute("USER")));
        }

        req.setAttribute("NEFRIEND",req.getAttribute("NEFRIEND"));
        req.getRequestDispatcher("/views/profile/profile.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("in profile post");

        String text = req.getParameter("post_text");
        String title = req.getParameter("post_title");
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("USER");

        if(InputValidator.validate(title) && InputValidator.validate(text)){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            PostRepos.addPost(new Post(null,user,title,text,dtf.format(localDate) ));
        }
        resp.sendRedirect("/profile");

    }
}
