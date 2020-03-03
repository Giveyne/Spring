package ru.igor.system.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.igor.system.mapping.UserMapping;
import ru.igor.system.model.User;

import java.util.Iterator;
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
        List<User> user;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        user = criteria.list();
        session.close();
        return user.get(0);
    }

    @Override
    public void save(User user) {


    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("Delete from user_base WHERE id = ?");
        session.close();
    }
}
