package ru.igor.HibernateDAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.igor.system.model.User;

import java.util.List;

@Component
public class HibernateConnector {

    @Autowired
    private SessionFactory sessionFactory;

    public String connectionDatabase(User user){

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();


            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("name", user.getName()));
            List l = criteria.list();
            if (!l.isEmpty()) {
                session.getTransaction().rollback();
                return "invalid";
            } else {
                session.save(user);
                session.getTransaction().commit();
                return "valid";
            }

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "invalid";
        } finally {
            session.close();
        }
    }

}