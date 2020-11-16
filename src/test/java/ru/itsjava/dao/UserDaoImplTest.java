package ru.itsjava.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.model.User;

@JdbcTest
@DisplayName("UserDaoJdbc have correct: ")
@Import({UserDaoImpl.class, MailDaoImpl.class, PetDaoImpl.class})
public class UserDaoImplTest {
    @Autowired
    UserDaoJdbc userDaoJdbc;

    @DisplayName("correct find count")
    @Test
    public void shouldCorrectCount() {
        Assertions.assertEquals(userDaoJdbc.count(), 3);
    }

    @DisplayName("correct find by ID")
    @Test
    public void shouldCorrectFindById() {
        User user = new User(3, "Katya", 19);
        Assertions.assertEquals(userDaoJdbc.findById(3), user);
    }

    @DisplayName("correct method insert")
    @Test
    public void shouldCorrectInsert() {
        User user = new User(4, "Oleg", 37);
        userDaoJdbc.insert(user);
        Assertions.assertEquals(userDaoJdbc.findById(4), user);
    }

    @DisplayName("correct method delete")
    @Test
    public void shouldCorrectDelete() {
        User user = new User(3, "Katya", 19);
        userDaoJdbc.deleteUser(user);
        Assertions.assertEquals(userDaoJdbc.count(), 2);
    }


}
