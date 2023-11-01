package com.youcode.airafrika.Dao;

import com.youcode.airafrika.Entities.FlightPath;
import com.youcode.airafrika.Interfaces.IDao;
import com.youcode.airafrika.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightPathDao implements IDao<FlightPath, Long> {

    private final SessionFactory sessionFactory;

    public FlightPathDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public FlightPathDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<FlightPath> store(FlightPath flightPath) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(flightPath);
            session.getTransaction().commit();
            return Optional.of(flightPath);
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in FlightPathDao - store Method", exception);
            return Optional.empty();
        }
    }

    @Override
    public Optional<FlightPath> update(FlightPath flightPath) {
        return Optional.empty();
    }

    @Override
    public Optional<FlightPath> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<FlightPath> getAll() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
