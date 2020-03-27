package ru.igor.system.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.igor.system.Security.User;

@Repository
public class UserDetailsDaoImp implements UserDetailsDao {

  @Autowired
 // @Qualifier("security")
  private SessionFactory sessionFactory;

  @Override
  public User findUserByUsername(String username) {
    return sessionFactory.getCurrentSession().get(User.class, username);
  }
}
