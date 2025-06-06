package model.dao.impl;

import java.util.Collections;
import model.dao.TransactionDAO;
import model.entity.Transaction;
import model.entity.TransactionType;
import model.entity.User;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;
import util.HibernateUtil;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public void save(Transaction transaction) {
        org.hibernate.Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Transaction transaction) {
        org.hibernate.Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Transaction transaction) {
        org.hibernate.Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Transaction findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Transaction.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Transaction> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Transaction", Transaction.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Transaction> findByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Transaction t WHERE t.user = :user", Transaction.class)
                          .setParameter("user", user)
                          .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Transaction> findByType(TransactionType type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Transaction t WHERE t.type = :type", Transaction.class)
                          .setParameter("type", type)
                          .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Transaction> findByCategory(Long catId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Transaction t WHERE t.category.id = :catId", Transaction.class)
                    .setParameter("catId", catId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}