package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itsjava.dao.MailDaoJdbc;
import ru.itsjava.dao.PetDaoJdbc;
import ru.itsjava.dao.UserDaoJdbc;
import ru.itsjava.model.Mail;
import ru.itsjava.model.Pet;
import ru.itsjava.model.User;


import java.sql.SQLException;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws SQLException {
        var context = new SpringApplication(Application.class).run();

        UserDaoJdbc userDaoJdbc = context.getBean(UserDaoJdbc.class);
        User anna = new User(3L, "Anna", 26);
        userDaoJdbc.insert(anna);
        userDaoJdbc.changeAge(anna, 20);

        System.out.println("userDaoJdbc.findById(2L) = " + userDaoJdbc.findById(2L));

//        userDaoJdbc.deleteUser(anna);
        System.out.println("userDaoJdbc.count() = " + userDaoJdbc.count());

        MailDaoJdbc mailDaoJdbc = context.getBean(MailDaoJdbc.class);
        Mail annaMail = new Mail(3L, "dasha@yandex.ru");
        mailDaoJdbc.insert(annaMail);
        System.out.println("mailDaoJdbc.findById(3) = " + mailDaoJdbc.findById(3));
        mailDaoJdbc.changeMail(annaMail, "anna1@mail.ru");
        System.out.println("mailDaoJdbc.findById(3) = " + mailDaoJdbc.findById(3));
//        mailDaoJdbc.deleteMail(annaMail);
        System.out.println("mailDaoJdbc.count() = " + mailDaoJdbc.count());

        PetDaoJdbc petDaoJdbc = context.getBean(PetDaoJdbc.class);
        Pet annaPet = new Pet(3L, "Human", "Husband");
        petDaoJdbc.insert(annaPet);
        System.out.println("petDaoJdbc.findById(3) = " + petDaoJdbc.findById(3));
        petDaoJdbc.changeName(annaPet, "HusbanDDD");
        System.out.println("petDaoJdbc.findById(3) = " + petDaoJdbc.findById(3));
        petDaoJdbc.deletePet(annaPet);
        System.out.println("petDaoJdbc.count() = " + petDaoJdbc.count());

        System.out.println("userDaoJdbc.findById(3L) = " + userDaoJdbc.findById(3L));
        System.out.println("userDaoJdbc.findById(4L) = " + userDaoJdbc.findById(4L));

        Console.main(args);
    }
}
