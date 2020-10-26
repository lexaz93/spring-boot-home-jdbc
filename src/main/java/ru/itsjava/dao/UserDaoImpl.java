package ru.itsjava.dao;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Repository
public class UserDaoImpl implements UserDaoJdbc {
    private final NamedParameterJdbcOperations jdbcOperations;
    private final MailDaoJdbc mailDaoJdbc;
    private final PetDaoJdbc petDaoJdbc;


    @Override
    public int count() {
        return jdbcOperations.getJdbcOperations().queryForObject("SELECT count(*) FROM USERS", Integer.class);
    }

    @Override
    public void insert(User user) {
        final Map<String, Object> params = new HashMap<>();
        params.put("ID", user.getId());
        params.put("NAME", user.getName());
        params.put("AGE", user.getAge());
        jdbcOperations.update("INSERT INTO USERS(ID, NAME, AGE) VALUES(:ID, :NAME, :AGE)", params);
    }

    @Override
    public User findById(long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("ID", id);
        try {
            if (mailDaoJdbc.findById(id) == null || petDaoJdbc.findById(id) == null) {
                return jdbcOperations.queryForObject("SELECT ID, NAME, AGE FROM USERS WHERE ID = :ID", params, new UserMapper());
            } else {
                User user = jdbcOperations.queryForObject("SELECT ID, NAME, AGE FROM USERS WHERE ID = :ID", params, new UserMapper());
                user.setEmail(mailDaoJdbc.findById(id));
                user.setPet(petDaoJdbc.findById(id));
                return user;
            }
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void changeAge(User user, int newAge) {
        final Map<String, Object> params = new HashMap<>();
        params.put("ID", user.getId());
        params.put("AGE", newAge);
        jdbcOperations.update("UPDATE USERS SET AGE=:AGE where ID = :ID", params);
    }

    @Override
    public void deleteUser(User user) {
        final Map<String, Object> params = new HashMap<>();
        params.put("ID", user.getId());
        jdbcOperations.update("DELETE FROM USERS where ID = :ID", params);
    }

    private static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            int age = resultSet.getInt("AGE");

            return new User(id, name, age);
        }
    }
}
