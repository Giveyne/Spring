package system.dao;

import org.springframework.stereotype.Repository;
import system.model.User;

import java.util.Arrays;
import java.util.List;


@Repository// База данных
public class UserDao {
    private List<User> users = Arrays.asList(
            new User(1L,"admin", "admin"),
            new User(2L, "user1", "user1"));

    public List<User> getAllUsers() {
        return users;
    }
}
