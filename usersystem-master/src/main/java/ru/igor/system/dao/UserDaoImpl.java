package ru.igor.system.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.igor.HibernateDAO.HibernateDao;
import ru.igor.system.model.User;
import java.util.List;


@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    public HibernateDao hibernateDao;

   /* public UserDaoImpl(HibernateDao hibernateDao) {
        this.hibernateDao = hibernateDao;
    }*/

    @Override
    public List<User> findAll() {
        return hibernateDao.getListUsers();
    }

    @Override
    public User getById(Long id) {
        return hibernateDao.getUserById(id);
    }

    @Override
    public void save(User user) {
       hibernateDao.addUser(user);
    }

    @Override
    public void delete(Long id) {
       hibernateDao.delateUser(id);
    }
    @Override
    public void update(User user) {
        hibernateDao.updateUser(user);
    }

}
