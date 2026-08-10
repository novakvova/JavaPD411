package org.example;

import lombok.Data;

@Data
public class Dog extends Animal {
    private int trainingCommandsCount; //кількість команд собакі
    //встановлюю дефолт значення для собакі
    public Dog() {
        super("Собака",2); //Виклкаю конструктор базового класу
        this.trainingCommandsCount = 3;
    }
    @Override
    public String toString()
    {
        String str = super.toString();
        str += "\t Кількість команд: "+ trainingCommandsCount;
        return str;
    }

}
