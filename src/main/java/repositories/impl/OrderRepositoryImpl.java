package repositories.impl;

import entities.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repositories.Repository;

import java.util.List;

public class OrderRepositoryImpl implements Repository<Order, Integer> {

    private final Session session;

    public OrderRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Order create(Order order) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> read() {
        Query query = null;
        try {
            query = session.createQuery("from Order", Order.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Order update(Order order) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Order delete(Order order) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Order findById(Integer id) {
        try {
            return (session.find(Order.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


