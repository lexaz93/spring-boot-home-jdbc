package ru.itsjava.dao;

import ru.itsjava.model.Mail;

public interface MailDaoJdbc {
    int count();
    void insert(Mail mail);
    Mail findById(long idHolder);
    void changeMail(Mail mail, String newMail);
    void deleteMail(Mail mail);
}
