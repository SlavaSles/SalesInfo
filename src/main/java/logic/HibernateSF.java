package logic;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSF {

    private static SessionFactory sessionFactory = null;

    private static void initSessionFactory() {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
                    configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException ex) {
            throw new RuntimeException("Ошибка инициализации SessionFactory Hibernate. Неверные данные в файле конфигурации");
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
