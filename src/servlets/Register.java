package servlets;

import db.DBManager;
import modals.Post;
import modals.User;
import repos.LastAccessTimeRepos;
import repos.UserRepos;
import utilities.InputValidator;
import utilities.PasswordEncoder;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/register")
public class Register extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/").forward(req, resp);
        if(req.getParameter("error")!=null){
            req.getRequestDispatcher("/register.jsp?error=1").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager.connect();
        PrintWriter out = resp.getWriter();
        String fullname = req.getParameter("first_name") + " " + req.getParameter("last_name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String emailAddress = req.getParameter("email");
        String phoneNumber = req.getParameter("phone");
        String address = req.getParameter("address");
        String date = req.getParameter("birthday");

        if( InputValidator.validate(username) && InputValidator.validate(password) && InputValidator.validate(fullname)  && InputValidator.validate(emailAddress)  && InputValidator.validate(phoneNumber)  && InputValidator.validate(address) && InputValidator.validate(date)){
//            System.out.println("Creating user");
            User user = new User(null,fullname,username, PasswordEncoder.getMd5(password),emailAddress,phoneNumber,address,date);
            if(user !=null){
                UserRepos.addUser(user);
                LastAccessTimeRepos.addAccess((new UserRepos()).findbyName(user.getFullName()));

            }
            resp.sendRedirect("/register");
        }
        else{
            resp.sendRedirect("/register?error=1");


        }




    }
}
