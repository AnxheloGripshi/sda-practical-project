package repositories.impl;

import entities.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repositories.Repository;

import java.util.List;

public class ReservationRepositoryImpl implements Repository<Reservation, Integer> {

    private final Session session;

    public ReservationRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Reservation create(Reservation reservation) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public List<Reservation> read() {
        Query query = null;
        try {
            query = session.createQuery("from Reservation", Reservation.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Reservation update(Reservation reservation) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public Reservation delete(Reservation reservation) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public Reservation findById(Integer id) {
        try {
            return session.find(Reservation.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

