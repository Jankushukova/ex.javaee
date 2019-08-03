package filters;

import db.DBManager;
import modals.Post;
import modals.User;
import repos.LastAccessTimeRepos;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/profile")
public class UserFilter implements Filter{
    public void init(FilterConfig fConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            System.out.println("start filtering");
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            String currentPath = req.getServletPath();
            HttpSession session = req.getSession();
            if (session.getAttribute("USER") == null) {
                resp.sendRedirect("/");
                return;

            }
            else{
                LastAccessTimeRepos.setLastAccess((User)session.getAttribute("USER"));
                if (currentPath.equals("\\views\\auth\\index.jsp") || currentPath.equals("\\views\\auth\\register.jsp") ) {
                    resp.sendRedirect("/profile");
                    return;
                }
            }
        chain.doFilter(request, response);
    }
    public void destroy() {

    }



}
