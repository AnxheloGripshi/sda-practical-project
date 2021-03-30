package repositories.impl;

import entities.Staff;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repositories.Repository;

import java.util.List;
import java.util.Scanner;

public class StaffRepositoryImpl implements Repository<Staff, Integer> {

    private final Session session;

    public StaffRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public Staff findById(Integer id) {
        try {
            return session.find(Staff.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Staff update(Staff staff) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(staff);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public List<Staff> read() {
        Query query = null;
        try {
            query = session.createQuery("from Staff", Staff.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Staff create(Staff staff) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(staff);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public Staff delete(Staff staff) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(staff);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return staff;
    }

}
