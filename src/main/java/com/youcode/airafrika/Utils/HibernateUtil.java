package com.youcode.airafrika.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtil.sessionFactory = sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try (final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build()) {
//                setSessionFactory(new MetadataSources( standardServiceRegistry ).getMetadataBuilder().build().getSessionFactoryBuilder().build());
                sessionFactory = new MetadataSources( standardServiceRegistry ).getMetadataBuilder().build().getSessionFactoryBuilder().build();
            }catch (Exception exception) {
                Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, "An Error Occured: SessionFactory.", exception);
            }
        }
        return sessionFactory;
    }
}
