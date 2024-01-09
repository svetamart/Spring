package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class AuthorizationManager {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Инициализация SessionFactory не удалась: " + e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User loginUser(Scanner scanner) {
        System.out.println("Введите имя пользователя:");
        String username = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            User user = (User) session.createQuery("FROM User WHERE username = :username AND password = :password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .uniqueResult();

            transaction.commit();

            if (user != null) {
                System.out.println("Вход выполнен успешно!");
                return user;
            } else {
                System.out.println("Неверное имя пользователя или пароль.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User registerUser(Scanner scanner) {
        System.out.println("Введите имя пользователя:");
        String username = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            User existingUser = (User) session.createQuery("FROM User WHERE username = :username")
                    .setParameter("username", username)
                    .uniqueResult();

            if (existingUser != null) {
                System.out.println("Пользователь с таким именем уже существует.");
                transaction.commit();
                return null;
            }

            System.out.println(username);
            String tasksFileName = username + "_tasks.json";

            User newUser = new User(username, password, tasksFileName);
            session.save(newUser);

            transaction.commit();

            System.out.println("Регистрация прошла успешно!");
            return newUser;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
