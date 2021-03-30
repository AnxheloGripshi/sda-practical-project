package repositories.impl;

import entities.Restaurant;
import entities.RestaurantTable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repositories.Repository;

import java.util.List;

public class RestaurantRepositoryImpl implements Repository<Restaurant, Integer> {

    private final Session session;

    public RestaurantRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Restaurant findById(Integer id) {
        try {
            return session.find(Restaurant.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(restaurant);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public List<Restaurant> read() {
        Query query = null;
        try {
            query = session.createQuery("from Restaurant", Restaurant.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(restaurant);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public Restaurant delete(Restaurant restaurant) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(restaurant);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return restaurant;
    }
}
