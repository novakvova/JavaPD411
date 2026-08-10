package org.example;

import org.example.entities.CategoryEntity;
import org.example.utils.HibernateHelper;

public class Main {
    public static void main(String[] args) {
        System.out.println("++Слава Україні++");
        System.out.println("-----Нехай буде hibernate------");
        try(var session = HibernateHelper.getSession()) {
            session.beginTransaction();
//            CategoryEntity cat =
//                    new CategoryEntity("Морозиво");
//            session.persist(cat); //Додати в БД
            var list = session.createQuery("from CategoryEntity",
                    CategoryEntity.class).getResultList();
            for(var item : list)
                System.out.printf("%d\t%s\n", item.getId(), item.getName());

            session.getTransaction().commit(); //Зберегти зміни

        } catch(Exception ex) {
            System.out.println("Помилка "+ex.getMessage());
        }
    }
}