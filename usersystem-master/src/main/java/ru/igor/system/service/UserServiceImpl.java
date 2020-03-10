package ru.igor.system.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.igor.system.dao.UserDao;
import ru.igor.system.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
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
    public void save(User user) { userDao.save(user); }
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
