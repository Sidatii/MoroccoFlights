package com.youcode.airafrika.Dao;

import com.youcode.airafrika.Entities.Airport;
import com.youcode.airafrika.Interfaces.AirportInterface;
import com.youcode.airafrika.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AirportDao implements AirportInterface {

    private final SessionFactory sessionFactory;

    public AirportDao() { sessionFactory = HibernateUtil.getSessionFactory(); }

    public AirportDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Airport> store(Airport airport) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(airport);
            session.getTransaction().commit();
            return Optional.of(airport);
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in AirportDao - store Method", exception);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Airport> update(Airport airport) {
        return Optional.empty();
    }

    @Override
    public Optional<Airport> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Airport airport = session.find(Airport.class, id);
            session.getTransaction().commit();
            return Optional.of(airport);
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in AirportDoa - findById Method", exception);
            return Optional.empty();
        }
    }

    @Override
    public List<Airport> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "SELECT A FROM Airport A";
            List<Airport> airports = session.createQuery(hql, Airport.class).list();
            session.getTransaction().commit();
            return airports;
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in AirportDoa - getAll Method", exception);
            return null;
        }
    }

    @Override
    public boolean delete() {
        return false;
    }
}
