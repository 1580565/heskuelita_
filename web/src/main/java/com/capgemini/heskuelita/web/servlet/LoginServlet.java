
package com.capgemini.heskuelita.web.servlet;

import com.capgemini.heskuelita.data.entity.UserAnnotation;
import com.capgemini.heskuelita.data.impl.UserDaoHibernet;
import com.capgemini.heskuelita.data.util.HibernateUtil;
import com.capgemini.heskuelita.service.IUserSecurityService;
import com.capgemini.heskuelita.service.impl.UserSecurityServiceImpl;
import org.hibernate.SessionFactory;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet ("/login")
public class LoginServlet extends HttpServlet {


    private IUserSecurityService securityService;


    public LoginServlet () {

        super ();
    }

   @Override
    public void init (ServletConfig config) throws ServletException {

       SessionFactory manager = HibernateUtil.getSessionFactory();

        try {

            this.securityService = new UserSecurityServiceImpl(new UserDaoHibernet(manager));
        } catch (Exception e) {

            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserAnnotation user = new UserAnnotation();
        user.setUs(req.getParameter ("user"));
        user.setPw(req.getParameter ("password"));
        try {

            this.securityService.login (user);

            HttpSession session = req.getSession ();
            session.setAttribute ("user", user);

            resp.sendRedirect ("pagina.html");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect ("err.jsp");
        }
    }
}