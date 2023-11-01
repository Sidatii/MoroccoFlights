package com.youcode.airafrika.Dao;

import com.youcode.airafrika.Entities.Plane;
import com.youcode.airafrika.Interfaces.PlaneInterface;
import com.youcode.airafrika.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaneDao implements PlaneInterface {

    private final SessionFactory sessionFactory;

    public PlaneDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public PlaneDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Plane> store(Plane plane) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(plane);
            session.getTransaction().commit();
            return Optional.of(plane);
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in PlaneDao - store Method", exception);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Plane> update(Plane plane) {
        return Optional.empty();
    }

    @Override
    public Optional<Plane> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Plane plane = session.find(Plane.class, id);
            session.getTransaction().commit();
            return Optional.of(plane);
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in PlaneDao - findById Method", exception);
            return Optional.empty();
        }
    }

    @Override
    public List<Plane> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "SELECT P FROM Plane P";
            List<Plane> planes = session.createQuery(hql, Plane.class).list();
            session.getTransaction().commit();
            return planes;
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in PlaneDao - getAll Method", exception);
            return null;
        }
    }

    @Override
    public boolean delete() {
        return false;
    }
}
