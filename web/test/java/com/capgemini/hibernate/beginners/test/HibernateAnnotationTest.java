package com.capgemini.hibernate.beginners.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.heskuelita.data.entity.UserAnnotation;
import com.capgemini.heskuelita.data.util.HibernateUtil;

public class HibernateAnnotationTest {

    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateAnnotationTest.class);


    public HibernateAnnotationTest () {

        super ();
    }


    @BeforeAll
    public static void setup () {

        sessionFactory = HibernateUtil.getSessionAnnotationFactory();
    }

    @AfterAll
    public static void destroy () {

        sessionFactory.close ();
    }

    @Test
    @DisplayName ("Create New Records")
    public void m1 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info("Creating values to insert...");
            UserAnnotation[] values = new UserAnnotation[]{

                    new UserAnnotation ("Homer_", "123", "h@gmail","homer","simpson","ns","masculino","213"),
                    new UserAnnotation ("Marge_", "1234", "m@gamil","marge","simpson","ns","femenino","312"),
                    new UserAnnotation ("Bart_", "12345", "b@gmail","bart","simpson","ns","masculino","321"),
                    new UserAnnotation ("Lisa_", "123456", "l@gmail","lisa","simpson","ns","femenino","43"),
                    new UserAnnotation ("Maggie_", "1234567", "ma@gmail","maggie","simpson","ns","femenino","544")
            };

            // Save the data.
            for (UserAnnotation e : values) {

                logger.info (String.format ("Saving value %s", e.getUs ()));
                session.save(e);
                logger.info (String.format ("Value %s saved!", e.getUs ()));
            }
            tx.commit ();
            Assertions.assertTrue (values[0].getId () > 0, String.format ("Problems creating teh new user %s", values[0].getUs ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Finding all Users")
    public void m2 () {

        Session session = null;
        List<UserAnnotation> employees;

        try {

            logger.info ("Executing select all users.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            employees = (List)session.createCriteria (UserAnnotation.class).list ();

            logger.info ("Print all users info.");
            employees.forEach ( e -> logger.info (e.getUs ()));

            Assertions.assertFalse (employees.isEmpty (), "There are not users found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Update all Users")
    public void m3 () {

        final Session session;
        Transaction tx = null;
        List<UserAnnotation> employees;

        try {

            logger.info ("Updating all users.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            employees = (List)session.createCriteria (UserAnnotation.class).list ();

            logger.info ("Print all users info.");
            employees.forEach (e -> {

                logger.info (String.format ("Updating %s ", e.getUs ()));
                e.setUs (e.getUs ().toUpperCase ());
                session.save (e);
            });
            tx.commit ();

            Assertions.assertFalse (employees.isEmpty (), "There are not users found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName ("Delete all users")
    public void m4 () {

        final Session session;
        Transaction tx;
        List<UserAnnotation> values;

        try {

            logger.debug ("Delete all users.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Usuario").list ();

            Assertions.assertFalse (values.isEmpty (), "There are not users found!!!");

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Usuario").list ();
            Assertions.assertTrue (values.isEmpty (), "There are users found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }



}
