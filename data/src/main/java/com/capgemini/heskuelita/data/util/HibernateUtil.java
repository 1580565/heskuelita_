package com.capgemini.heskuelita.data.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class HibernateUtil {


    private static SessionFactory sessionFactory;

    private static SessionFactory sessionAnnotationFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateUtil.class);


    private HibernateUtil () {

        super ();
    }



    private static SessionFactory buildSessionFactory () {

        try {

            Configuration configuration = new Configuration ();
            configuration.configure ("hibernate.cfg.xml");
            logger.debug ("Hibernate Configuration loaded...");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder ().
                    applySettings (configuration.getProperties()).build ();
            logger.debug ("Hibernate serviceRegistry created...");

            SessionFactory sessionFactory = configuration.buildSessionFactory (serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {

            logger.error ("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError (ex);
        }
    }

    private static SessionFactory buildSessionAnnotationFactory () {

        try {

            Configuration configuration = new Configuration ();
            configuration.configure ("hibernate.cfg.xml");
            logger.debug ("Hibernate Annotation Configuration loaded...");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder ().
                    applySettings (configuration.getProperties ()).build ();
            logger.debug ("Hibernate Annotation serviceRegistry created...");

            SessionFactory sessionFactory = configuration.buildSessionFactory (serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {

            logger.error ("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError (ex);
        }
    }


    public static SessionFactory getSessionFactory () {

        if (sessionFactory == null) { sessionFactory = buildSessionFactory (); }
        return sessionFactory;
    }

    public static SessionFactory getSessionAnnotationFactory () {

        if (sessionAnnotationFactory == null) { sessionAnnotationFactory = buildSessionAnnotationFactory (); }
        return sessionAnnotationFactory;
    }


}
