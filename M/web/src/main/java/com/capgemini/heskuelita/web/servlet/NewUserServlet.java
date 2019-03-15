package com.capgemini.heskuelita.web.servlet;


import com.capgemini.heskuelita.data.entity.UserAnnotation;
import com.capgemini.heskuelita.data.impl.UserDaoHibernet;
import com.capgemini.heskuelita.data.util.HibernateUtil;
import com.capgemini.heskuelita.service.IUserSecurityService;
import com.capgemini.heskuelita.service.impl.UserUserSecurityServiceImpl;
import org.hibernate.SessionFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/NewUser")
public class NewUserServlet extends HttpServlet {

    private IUserSecurityService securityService;

    public NewUserServlet(){
        super();
    }

    @Override
    public void init (ServletConfig config) throws ServletException {

        SessionFactory manager = HibernateUtil.getSessionFactory();


        try {

            this.securityService = new UserUserSecurityServiceImpl(new UserDaoHibernet(manager));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserAnnotation user = new UserAnnotation();
        user.setName (req.getParameter("first_name"));
        user.setPassword (req.getParameter ("password"));
        user.setEmail(req.getParameter("email"));
        user.setUs_apellido("hola");
        user.setUs_nombre("hola");
        user.setUs_doc("123");
        user.setUs_fnac("asd");
        user.setUs_gen("33");


        try {

            this.securityService.NewUser (user);

            HttpSession session = req.getSession ();
            session.setAttribute ("user", user);

            resp.sendRedirect ("home.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect ("err.jsp");
        }

    }
}
