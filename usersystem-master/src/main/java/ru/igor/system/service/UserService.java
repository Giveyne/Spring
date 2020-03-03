package ru.igor.system.service;

import ru.igor.system.model.User;


import java.util.List;

public interface UserService {
    User getById(Long id);
    List<User> findAll();

    void save(User user);
    void delete(Long id);
}
