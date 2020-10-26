package ru.itsjava.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@JdbcTest
@DisplayName("UserDaoJdbc have correct: ")
@Import({UserDaoImpl.class, MailDaoImpl.class, PetDaoImpl.class})
public class UserDaoImplTest {
    @Autowired
    UserDaoJdbc userDaoJdbc;

    @DisplayName("correct find count")
    @Test
    public void shouldCorrectCount() {
        Assertions.assertEquals(userDaoJdbc.count(), 2);
    }


}
