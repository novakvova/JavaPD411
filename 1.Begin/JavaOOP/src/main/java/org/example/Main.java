package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("-----Робота з класами------");
//        Animal cat = new Animal();
//        cat.setName("Барсик рижих");
//        cat.setAge(4);
//        System.out.println(cat);
//        Animal mary = new Animal("Mary", 2);
//        System.out.println(mary);

        Cat myCat = new Cat();
        System.out.println(myCat);
        Dog myDog = new Dog();
        System.out.println(myDog);

        ArrayList<Animal> list = new ArrayList<>();
        list.add(myCat);
        list.add(myDog);

    }
}