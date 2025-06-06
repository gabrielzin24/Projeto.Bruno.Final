package model.dao.impl;

import model.dao.CategoryDAO;
import model.entity.Category;
import util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import model.entity.User;
import org.hibernate.Hibernate;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public void save(Category category) {
        org.hibernate.Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(category);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category category) {
        org.hibernate.Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(category);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Category findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Category> findAllByUserId(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Category> categories = session.createQuery("FROM Category c WHERE c.user.id = :userId", Category.class)
                    .setParameter("userId", userId)
                    .getResultList();

            for (Category c : categories) {
                Hibernate.initialize(c.getTransactions());
            }

            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category findByNameAndUserId(String name, Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Category c WHERE c.name = :name AND c.user.id = :userId", Category.class)
                          .setParameter("name", name)
                          .setParameter("userId", userId)
                          .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    // isso apaga a categoria, e troca todas "category" de transações para Deleted.
    public boolean delete(Category category, User user) {
        org.hibernate.Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Category cat = findCategoryByIdAndUser(session, category.getId(), user.getId());
            if (cat != null) {
                Category fallback = getOrCreateFallbackCategory(session, user, "Deleted");
                reassignTransactions(session, cat, fallback);
                session.flush();
                session.remove(cat);
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    private Category findCategoryByIdAndUser(Session session, Long catId, Long userId) {
        return session.createQuery(
                "FROM Category c WHERE c.id = :catId AND c.user.id = :userId", Category.class)
                .setParameter("catId", catId)
                .setParameter("userId", userId)
                .uniqueResult();
    }

    private Category getOrCreateFallbackCategory(Session session, User user, String fallbackName) {
        Category fallback = session.createQuery(
                "FROM Category c WHERE c.name = :name AND c.user.id = :userId", Category.class)
                .setParameter("name", fallbackName)
                .setParameter("userId", user.getId())
                .uniqueResult();

        if (fallback == null) {
            fallback = new Category();
            fallback.setName(fallbackName);
            fallback.setUser(user);
            session.persist(fallback);
        }

        return fallback;
    }

    private void reassignTransactions(Session session, Category fromCategory, Category toCategory) {
        List<model.entity.Transaction> transactions = session.createQuery(
                "FROM Transaction t WHERE t.category.id = :catId", model.entity.Transaction.class)
                .setParameter("catId", fromCategory.getId())
                .list();

        for (model.entity.Transaction t : transactions) {
            t.setCategory(toCategory);
            session.update(t);
        }
    }
}
