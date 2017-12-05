package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by nikolayemrikh on 22.11.15.
 */
public class HibernateUtil {
    private static final SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}
