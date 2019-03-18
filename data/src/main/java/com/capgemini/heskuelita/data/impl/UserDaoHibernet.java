
package com.capgemini.heskuelita.data.impl;


import com.capgemini.heskuelita.data.DataException;
import com.capgemini.heskuelita.data.IUserDao;
import com.capgemini.heskuelita.data.entity.UserAnnotation;
import com.capgemini.heskuelita.data.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class UserDaoHibernet implements IUserDao {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (UserDaoHibernet.class);


    public UserDaoHibernet(SessionFactory sessionFactory) {

        super ();

        this.sessionFactory = sessionFactory;
    }


    public static void setup () {

        sessionFactory = HibernateUtil.getSessionFactory ();
    }


    public static void destroy () {

        sessionFactory.close ();
    }

    @Override
    public UserAnnotation login(String userName, String password) {

        // Get a session.
        Session session = null;
        final String    filter1 = userName;
        final String filter2 = password;
        UserAnnotation us = null;

        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();
            logger.info (String.format ("Finding companies by id and country [%s, %s] using criteria object.", filter1, filter2));
            Criterion criterion1 = Restrictions.like ("us_nom", filter1);
            Criterion criterion2 = Restrictions.like ("us_pw", filter2);
            LogicalExpression andExp = Restrictions.and (criterion1, criterion2);
            List<UserAnnotation> list = (List<UserAnnotation>) session.createCriteria (UserAnnotation.class).
                    add (andExp).list ();

            if(!list.isEmpty()){
                for (UserAnnotation e : list){
                    us = new UserAnnotation();

                    us.setUs(e.getUs());

                }

            }

            logger.info (String.format ("Companies by id and country [%s, %s] using criteria object executed!", filter1, filter2));

        } catch (Exception ex) {

            String m = String.format ("Problems executing login %s", ex.getMessage ());
            logger.error (m);

        }

        if (us == null) {
            throw new DataException ("Usuario " + userName + " desconocido");
        }

        return  us;
    }


    @Override
    public  void NewUser(String user_name, String password, String email, String nombre, String apellido, String fnac, String gen, String doc){

        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info(String.format("Creating value to insert... %s , %s , %s",  user_name ,  password , email));
            UserAnnotation pt = new UserAnnotation (user_name,password,email, nombre,apellido,fnac, gen,doc);

            // Save the data.
            logger.info (String.format ("Saving value %s", pt.getUs()));
            session.save (pt);
            logger.info (String.format ("Value %s saved!", pt.getUs()));

            tx.commit ();

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();

        }

    }
}