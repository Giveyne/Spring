package ru.igor.system.mapping;


import org.springframework.jdbc.core.RowMapper;
import ru.igor.system.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapping implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
