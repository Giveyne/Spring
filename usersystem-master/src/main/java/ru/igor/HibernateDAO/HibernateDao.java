package ru.igor.HibernateDAO;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.igor.system.model.User;

import java.util.List;

@Transactional(propagation= Propagation.SUPPORTS, readOnly=true)
@Repository
public class HibernateDao {

    @Autowired
    //@Qualifier("myBase")
    private SessionFactory sessionFactory;

//    public HibernateDao(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }


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
    @Transactional
    public User getUserById(Long id) {
        return currentSession().get(User.class, id);
    }

    @Transactional
    //@CachePut(value = "users", key = "#user.name")
    public void updateUser(User user){
        //User user1 = currentSession().get(User.class, user.getName());
//        criteria.add(Restrictions.eq("name", user.getName()));
//        criteria.add(Restrictions.ne("id", user.getId()));
     //   List addUser = criteria.list();
        currentSession().update(user);
       /* if (!(user1 == null)) {
            throw new RuntimeException("ewe");
        } else {

        }*/
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @CachePut(value = "users", key = "#user.name")
    public void delateUser(Long id){
        User user = currentSession().get(User.class, id);
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

