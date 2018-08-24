package com;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            String hibernate_cfg_path = "hibernate.cfg.xml";
            Configuration configuration = new Configuration().configure(hibernate_cfg_path);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static Session commitCurrentSessions() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        if (session.isOpen()) {
            Transaction t = session.getTransaction();
            if (t.isActive()) {
                try {
                    t.commit();
                } catch (Throwable ex) {
                    return session;
                }
            } else {
                return session;
            }
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
