package com.youcode.airafrika.Dao;

import com.youcode.airafrika.Entities.Company;
import com.youcode.airafrika.Interfaces.IDao;
import com.youcode.airafrika.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyDao implements IDao<Company, Long> {

    private final SessionFactory sessionFactory;

    public CompanyDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public CompanyDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Company> store(Company company) {
        return Optional.empty();
    }

    @Override
    public Optional<Company> update(Company company) {
        return Optional.empty();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Company> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "SELECT C FROM Company C";
            List<Company> companies = session.createQuery(hql, Company.class).list();
            session.getTransaction().commit();
            return companies;
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in CompanyDao - getAll Method", exception);
            return null;
        }
    }

    @Override
    public boolean delete() {
        return false;
    }
}
