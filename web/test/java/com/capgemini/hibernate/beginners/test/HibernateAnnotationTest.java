package com.capgemini.hibernate.beginners.test;

import java.util.Date;
import java.util.List;

import com.capgemini.heskuelita.data.IUserDao;
import com.capgemini.heskuelita.data.impl.UserDaoHibernet;
import com.capgemini.heskuelita.service.IUserSecurityService;
import com.capgemini.heskuelita.service.SecurityException;
import com.capgemini.heskuelita.service.impl.UserSecurityServiceImpl;
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

    private static final Logger logger = LoggerFactory.getLogger(HibernateAnnotationTest.class);

    private IUserSecurityService securityService;

    public HibernateAnnotationTest() {

        super();
    }


    @BeforeAll
    public static void setup() {

        sessionFactory = HibernateUtil.getSessionAnnotationFactory();
    }

    @AfterAll
    public static void destroy() {

        sessionFactory.close();
    }

    @Test
    @DisplayName("Create New Records")
    public void m1() {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Set the data to save.
            logger.info("Creating values to insert...");
            UserAnnotation[] values = new UserAnnotation[]{

                    new UserAnnotation("Raul01", "123", "raul@gmail", "raul", "sequeira", "11/05/1996", "masculino", "21113"),
                    new UserAnnotation("Marcos02", "1234", "marcos@hotmail", "marcos", "inca", "22/05/1995", "masculino", "31112"),
                    new UserAnnotation("Jose02", "12345", "jose@gmail", "jose", "eva", "12/08/1996", "masculino", "31121"),
                    new UserAnnotation("Jessica03", "123456", "jessi@yahoo", "jessica", "ludeña", "05/10/1999", "femenino", "41113"),
                    new UserAnnotation("Amalia04", "1234567", "ama@gmail", "amalia", "jaime", "12/08/1992", "femenino", "54411")
            };

            // Save the data.
            for (UserAnnotation e : values) {

                logger.info(String.format("Saving value %s", e.getUs()));
                session.save(e);
                logger.info(String.format("Value %s saved!", e.getUs()));
            }
            tx.commit();
            Assertions.assertTrue(values[0].getId() > 0, String.format("Problems creating teh new user %s", values[0].getUs()));

        } catch (Exception ex) {

            logger.error(ex.getMessage());
            tx.rollback();
            Assertions.assertFalse(Boolean.TRUE, "Problems executing the test.");

        } finally {
            session.close();
        }
    }

    @Test
    @DisplayName("Finding all Users")
    public void m2() {

        Session session = null;
        List<UserAnnotation> employees;

        try {

            logger.info("Executing select all users.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession();

            employees = (List) session.createCriteria(UserAnnotation.class).list();

            logger.info("Print all users info.");
            employees.forEach(e -> logger.info(e.getUs()));

            Assertions.assertFalse(employees.isEmpty(), "There are not users found!!!");

        } catch (Exception e) {

            logger.error(e.getMessage());
            Assertions.assertFalse(Boolean.TRUE, "Problems executing the test.");

        } finally {
            session.close();
        }
    }

    @Test
    @DisplayName("Update all Users")
    public void m3() {

        final Session session;
        Transaction tx = null;
        List<UserAnnotation> employees;

        try {

            logger.info("Updating all users.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            employees = (List) session.createCriteria(UserAnnotation.class).list();

            logger.info("Print all users info.");
            employees.forEach(e -> {

                logger.info(String.format("Updating %s ", e.getUs()));
                e.setUs(e.getUs().toUpperCase());
                session.save(e);
            });
            tx.commit();

            Assertions.assertFalse(employees.isEmpty(), "There are not users found!!!");

        } catch (Exception e) {

            logger.error(e.getMessage());
            Assertions.assertFalse(Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName("Delete all users")
    public void m4() {

        final Session session;
        Transaction tx;
        List<UserAnnotation> values;

        try {

            logger.debug("Delete all users.");
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            values = (List) session.createQuery("From Usuario").list();

            Assertions.assertFalse(values.isEmpty(), "There are not users found!!!");

            values.forEach(e -> session.delete(e));
            tx.commit();

            values = (List) session.createQuery("From Usuario").list();
            Assertions.assertTrue(values.isEmpty(), "There are users found!!!");

        } catch (Exception e) {

            logger.error(e.getMessage());
            Assertions.assertFalse(Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName("login user")
    public void m5() {

        UserAnnotation user = new UserAnnotation("Raul01", "123", "raul@gmail", "raul", "sequeira", "11/05/1996", "masculino", "21113");
        SessionFactory manager = HibernateUtil.getSessionFactory();
        this.securityService = new UserSecurityServiceImpl(new UserDaoHibernet(manager));
        try {

            logger.debug("login user.");
            this.securityService.login(user);
            System.out.println("*********************************************************************");
            System.out.println("***************************login completed***************************");
            System.out.println("*********************************************************************");


        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            Assertions.assertFalse(Boolean.TRUE, "Problems executing the test.");

        } finally { manager.close(); }
    }
}

