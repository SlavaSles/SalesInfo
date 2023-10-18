package logic;

import errors.ErrorMessages;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Класс для запуска Hibernate и создания SessionFactory
 */
public class HibernateSF {

    /**
     * Поле - SessionFactory
     */
    private static SessionFactory sessionFactory = null;

    /**
     * Статическая процедура инициализации Hibernate и SessionFactory
     * @throws RuntimeException Исключение выбрасывается при ошибках в файле конфигурации hibernate.cfg.xml или в
     * процессе создания SessionFactory
     */
    public static void initSessionFactory() throws RuntimeException{
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

    /**
     * Статическая функция создания новой сессии Hibernate
     * @return возвращает новую сессию Hibernate
     */
    public static Session openSession() {
        if (sessionFactory == null) {
            initSessionFactory();
        }
        return sessionFactory.openSession();
    }

    /**
     * Процедура, закрывающая SessionFactory
     */
    public static void close() {
        sessionFactory.close();
    }

}
