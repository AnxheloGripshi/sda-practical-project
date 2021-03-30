package repositories.impl;
import entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repositories.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository<User,Integer> {

    private final Session session;

    public UserRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {

            Query query = session.createQuery(" from User u where u.userName = :username and u.password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            user = (User) query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    @Override
    public User findById(Integer id) {

        try {
            return session.find(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public User create(User user) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

        return user;
    }

    @Override
    public List<User> read() {
        List<User> users;
        try {
            Query query = session.createQuery("from User");
            users = query.list();
            return users;

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

        return user;
    }

    @Override
    public User delete(User user) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }

        return user;
    }

}


