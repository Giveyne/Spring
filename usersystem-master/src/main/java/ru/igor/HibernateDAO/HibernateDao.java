package ru.igor.HibernateDAO;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.igor.system.model.User;

import java.util.List;

@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
@Repository
public class HibernateDao {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    @CachePut(value = "users", key = "#user.name")
    public void addUser(User user){
        Criteria criteria = currentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("name", user.getName()));
        List addUser = criteria.list();
        if (!addUser.isEmpty()) {

        } else {
            currentSession().save(user);
        }
    }

    public User getUserById(Long id) {
        return (User) currentSession().get(User.class, id);
    }

    @Transactional
    @CachePut(value = "users", key = "#user.name")
    public void updateUser(User user){
        Criteria criteria = currentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("name", user.getName()));
        criteria.add(Restrictions.ne("id", user.getId()));
        List addUser = criteria.list();
        if (!addUser.isEmpty()) {
            throw new RuntimeException("ewe");
        } else {
            currentSession().update(user);
        }
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @CachePut(value = "users", key = "#user.name")
    public void delateUser(Long id){
        User user = (User) currentSession().get(User.class, id);
        currentSession().delete(user);
    }

    public List<User> getListUsers() {
        SQLQuery query = currentSession().createSQLQuery("select * from user_base");
        query.addEntity(User.class);
        List<User> users = query.list();
        return users;
    }
}

