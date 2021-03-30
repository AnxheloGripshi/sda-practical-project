package repositories.impl;

import entities.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repositories.Repository;

import java.util.List;

public class ReviewRepositoryImpl implements Repository<Review, Integer> {

    private final Session session;

    public ReviewRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Review create(Review review) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(review);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

        return review;
    }

    @Override
    public Review delete(Review review) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(review);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

        return review;
    }

    @Override
    public Review update(Review review) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(review);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

        return review;
    }

    @Override
    public Review findById(Integer id) {
        try {
            return session.find(Review.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Review> read() {
        Query query = null;
        try {
            query = session.createQuery("from Review", Review.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }


}
