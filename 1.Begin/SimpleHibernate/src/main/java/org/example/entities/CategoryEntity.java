package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity //Ознчає, що це таблиця БД
@Table(name="tblCategories")//Назва таблички
public class CategoryEntity {
    @Id //Це первиний ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Буде робитися autoincrement
    private int id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(name="date_created", nullable = false)
    private LocalDateTime dateCreated;

    public CategoryEntity() {
        this.dateCreated = LocalDateTime.now();
    }

    public CategoryEntity(String name) {
//        super(); //Конструкто батьківського класу
        this.dateCreated = LocalDateTime.now();
        this.name = name;
    }
}
