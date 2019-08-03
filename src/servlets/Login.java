package servlets;

import modals.User;
import repos.UserRepos;
import utilities.InputValidator;
import utilities.PasswordEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("USER")!=null){
            req.getSession().removeAttribute("USER");
        }
        req.getRequestDispatcher("/views/auth/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("login");
        String login = req.getParameter("username");
        String password = req.getParameter("password");

        User user = null;
        if(login==null ||password==null){
            resp.sendRedirect("/?error=1");

        }
        if(InputValidator.validate(login) && InputValidator.validate(password)){
            for(User u:UserRepos.getAllUsers()){
                if(  (u.getUsername().equals(login))&&(u.getPassword().equals(PasswordEncoder.getMd5(password)))  ){
                    user = u;
                    break;
                }
            }
        }
        else{
            resp.sendRedirect("/?error=1");

        }
        if(user!=null){
            HttpSession session = req.getSession(true);
            session.setAttribute("USER", user);


            resp.sendRedirect("/");
        }
        else{
            resp.sendRedirect("/?error=1");

        }

    }
}
