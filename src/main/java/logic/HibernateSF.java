package logic;

import errors.ErrorMessages;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSF {

    private static SessionFactory sessionFactory = null;

    public static void initSessionFactory() {
        try {
            if (sessionFactory == null) {
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
                        configure("hibernate.cfg.xml").build();
                Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }
        } catch (HibernateException ex) {
            throw new RuntimeException(ErrorMessages.ERROR_MESSAGE_CODE_17);
        }
    }

    public static Session openSession() {
        if (sessionFactory == null) {
            initSessionFactory();
        }
        return sessionFactory.openSession();
    }

    public static void close() {
        sessionFactory.close();
    }

}
