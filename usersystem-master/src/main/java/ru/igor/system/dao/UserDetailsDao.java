package ru.igor.system.dao;


import ru.igor.system.Security.User;

public interface UserDetailsDao {
  User findUserByUsername(String username);
}
