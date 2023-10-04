import logic.ProgramLogic;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        ProgramLogic programLogic = new ProgramLogic(args);
        programLogic.run();


//        ToDo: Сделать отдельный класс для получения сессий не в Main
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
//                configure("hibernate.cfg.xml").build();
//        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
//        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
//        sessionFactory.close();
    }
}
