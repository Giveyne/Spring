package HibernateDAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


// Стандартный класс для подключения Hibernate

public class HibernateUtil {
        private static SessionFactory sessionFactory = null; // на основе этого можно создавать сессии транзакции комиты и ролбеки
        static {
            Configuration cfg = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(builder.build());
        }
        public static SessionFactory getSessionFactory(){
            return sessionFactory;
        }
}


