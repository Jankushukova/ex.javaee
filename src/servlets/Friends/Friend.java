package servlets.Friends;

import db.DBManager;
import modals.Post;
import modals.User;
import repos.FriendRepos;
import repos.UserRepos;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/friend")
public class Friend extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        ArrayList<User> friends = FriendRepos.getFriends((User)session.getAttribute("USER"));
        ArrayList<User> nefriends = new ArrayList<>();
        for(User u:UserRepos.getAllUsers()){
            if(FriendRepos.neFriend((User)session.getAttribute("USER"),u)){
                nefriends.add(u);
            }
        }


        session.setAttribute("FRIEND",friends);
        session.setAttribute("NEFRIEND",nefriends);
        req.getRequestDispatcher("/views/friends/friends.jsp").forward(req,resp);

    }


}
