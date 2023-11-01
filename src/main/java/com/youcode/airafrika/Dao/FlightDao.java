package com.youcode.airafrika.Dao;

import com.youcode.airafrika.Entities.Flight;
import com.youcode.airafrika.Interfaces.FlightInterface;
import com.youcode.airafrika.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightDao implements FlightInterface {

    private final SessionFactory sessionFactory;

    public FlightDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public FlightDao(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    @Override
    public Optional<Flight> store(Flight flight) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(flight);
            session.getTransaction().commit();
            return Optional.of(flight);
        }catch(Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in Flight DAO - Store Method", exception);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Flight> update(Flight flight) {
        return Optional.empty();
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Flight> getAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "SELECT f FROM Flight f";
            List<Flight> flights = session.createQuery(hql, Flight.class).list();
            session.getTransaction().commit();
            return flights;
        }catch (Exception exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An Error Occurred in Flight Doa - getAll method.", exception);
            return null;
        }
    }

    @Override
    public boolean delete() {
        return false;
    }
}
