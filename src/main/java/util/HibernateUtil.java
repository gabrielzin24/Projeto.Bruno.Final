package util;

import model.entity.User;
import model.entity.Transaction;
import model.entity.Category;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            System.out.println(Thread.currentThread().getContextClassLoader().getResource("hibernate.cfg.xml"));
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Transaction.class);
            configuration.addAnnotatedClass(Category.class);

            return configuration.buildSessionFactory(
                    new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build()
            );

        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Erro ao criar SessionFactory");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}