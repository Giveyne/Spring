package ru.igor.system.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.igor.system.model.User;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    public SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select * from user_base");
        query.addEntity(User.class);
        List<User> users = query.list();
        session.close();
        return users;
    }

    @Override
    public User getById(Long id) {
        User user;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        user = (User)criteria.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("name", user.getName()));
            List addUser = criteria.list();
            if (!addUser.isEmpty()) {
                session.getTransaction().rollback();
            } else {
                session.save(user);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //User currentUser = (User) session.get(User.class, user.getId());
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

}
