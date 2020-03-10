package ru.igor.HibernateDAO;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.igor.system.model.User;

import java.util.List;

@Transactional
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
    @Transactional
    public void addUser(User user){
        Criteria criteria = currentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("name", user.getName()));
        List addUser = criteria.list();
        if (!addUser.isEmpty()) {

        } else {
            currentSession().save(user);
        }
    }
    @Transactional
    public User getUserById(Long id) {
        return (User) currentSession().get(User.class, id);
    }
    @Transactional
    public void updateUser(User user){
        currentSession().update(user);
    }
    @Transactional
    public void delateUser(Long id){
        User user = (User) currentSession().get(User.class, id);
        currentSession().delete(user);
    }
    @Transactional
    public List<User> getListUsers() {
        SQLQuery query = currentSession().createSQLQuery("select * from user_base");
        query.addEntity(User.class);
        List<User> users = query.list();
        return users;
    }
}

