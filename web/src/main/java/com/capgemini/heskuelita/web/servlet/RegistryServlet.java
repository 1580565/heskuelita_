package com.capgemini.heskuelita.web.servlet;


import com.capgemini.heskuelita.data.entity.UserAnnotation;
import com.capgemini.heskuelita.data.impl.UserDaoHibernet;
import com.capgemini.heskuelita.data.util.HibernateUtil;
import com.capgemini.heskuelita.service.IUserSecurityService;
import com.capgemini.heskuelita.service.impl.UserSecurityServiceImpl;
import org.hibernate.SessionFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Registry")
public class RegistryServlet extends HttpServlet {

    private IUserSecurityService securityService;

    public RegistryServlet(){
        super();
    }

    @Override
    public void init (ServletConfig config) throws ServletException {

        SessionFactory manager = HibernateUtil.getSessionFactory();


        try {

            this.securityService = new UserSecurityServiceImpl(new UserDaoHibernet(manager));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserAnnotation user = new UserAnnotation();
        user.setUs(req.getParameter("us"));
        user.setPw(req.getParameter ("password"));
        user.setEmail(req.getParameter("email"));
        user.setApellido(req.getParameter("last_name"));
        user.setNombre(req.getParameter("first_name"));
        user.setDoc(req.getParameter("document"));
        user.setFnac(req.getParameter("birthday"));
        user.setGen(req.getParameter("gender"));


        try {

            this.securityService.NewUser (user);

            HttpSession session = req.getSession ();
            session.setAttribute ("user", user);

            resp.sendRedirect ("index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect ("err.jsp");
        }

    }
}
