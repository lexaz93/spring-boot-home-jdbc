package ru.itsjava.dao;

import ru.itsjava.model.User;

public interface UserDaoJdbc {
    int count();
    void insert(User user);
    User findById(long id);
    void changeAge(User user, int newAge);
    void deleteUser(User user);
}
