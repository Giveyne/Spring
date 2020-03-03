package ru.igor.system.service;


import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.igor.system.dao.UserDao;
import ru.igor.system.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier("getUserDao")
    public UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void save(User user) {


    }

    @Override
    public void delete(Long id) {
    }
}
