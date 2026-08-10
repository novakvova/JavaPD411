package org.example.utils;

import lombok.Getter;
import org.example.entities.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateHelper {
    //Підключення до БД - в Java усе фабрики
    @Getter
    private static SessionFactory sessionFactory;

    //автоматичний викли методу, якщо ми використовуємо даний клас
    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .build(); //читає конфігурацію hibernate.properties
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(CategoryEntity.class) //вказали, що буде таблиця в БД
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("Сталася помилка "+ ex);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void shutDown() {
        if(sessionFactory != null)
            sessionFactory.close();
    }
}
