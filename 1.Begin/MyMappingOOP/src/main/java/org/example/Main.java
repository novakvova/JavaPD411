package org.example;

import org.example.utils.HibernateHelper;

public class Main {
    public static void main(String[] args) {
//        System.out.println("OOP Java 3");
        try {
            System.out.println("Підлкючення до БД");
            //Самі прості таблиці -
            // 1.Категорія, 2.Товар, 3.Фото товарів, 4.Користувачі,
            // 5.Відгуки товарів
            // 6.Замовлення користувачів і 7.товари в замовлені.

            var session = HibernateHelper.getSession();
            // ....
            HibernateHelper.shutDown();

        }catch (Exception e) {
            System.out.println("Щось пішло не так"+e.getMessage());
        }
    }
}