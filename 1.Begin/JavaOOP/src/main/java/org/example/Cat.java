package org.example;

import lombok.Data;

@Data
public class Cat extends Animal {
    private boolean indoorOnly; //де живе котик
    //встановлюю дефолт значення для кота
    public Cat() {
        super("Кіт",2); //Виклкаю конструктор базового класу
        this.indoorOnly = false;
    }
    public Cat(String name, int age, boolean indoorOnly)
    {
        super(name, age);
        this.indoorOnly = indoorOnly;
    }
    @Override
    public String toString()
    {
        String str = super.toString();
        str += "\t Живе в хаті: "+indoorOnly;
        return str;
    }

}
