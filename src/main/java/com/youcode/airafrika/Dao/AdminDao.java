package com.youcode.airafrika.Dao;

import com.youcode.airafrika.Entities.Admin;
import com.youcode.airafrika.Interfaces.AdminInterface;
import com.youcode.airafrika.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDao implements AdminInterface {

    private final SessionFactory sessionFactory;

    public AdminDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public AdminDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Admin> store(Admin admin) {
        return Optional.empty();
    }

    @Override
    public Optional<Admin> update(Admin admin) {
        return Optional.empty();
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Admin> getAll() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public Optional<Admin> findByEmail(Admin admin) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Admin A where A.email = :email";
            Admin admin1 = session.createQuery(hql, Admin.class).setParameter("email", admin.getEmail()).getSingleResult();
            System.out.println("M: ==> " + admin1.getFirstName());
            transaction.commit();
            if (admin1 != null)
                return Optional.of(admin1);
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred With the Session.", exception);
            if (transaction != null)
                transaction.rollback();
        }
        return Optional.empty();
    }
}
