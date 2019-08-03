package servlets.Friends;


import modals.User;
import repos.FriendRepos;
import repos.NotificationsRepos;
import repos.UserRepos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addfriend")
public class AddFriend extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        NotificationsRepos notrep = new NotificationsRepos();

        User user = (User) session.getAttribute("USER");
        User friend = UserRepos.findbyId(Long.parseLong(req.getParameter("id")));

        if(Integer.parseInt(req.getParameter("text"))==0){
            notrep.addNotification(user,friend);
        }

        else if(Integer.parseInt(req.getParameter("text"))==1){
            FriendRepos.addFriends(user,friend);
            FriendRepos.addFriends(friend,user);
            notrep.removeNotification(user,friend);
        }
    }
}
