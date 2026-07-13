package org.example; //namespace

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Привіт козаки!");
//        System.out.print("Добре їсти добре спати! ");
//        System.out.print("Наївся і поїв.");
//        sout
//        int a=25;
//        System.out.println("a = 25");
//        boolean b = true;
//        System.out.println("b = " + b);
//        char ch='a';
//        String str = "Смачна малина, коли чиста :)";
//        System.out.println(str);
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Як Вас звати?");
//        System.out.print("->_");
//        String str = scanner.nextLine(); //вводимо дані
//        System.out.println("Привіт містер "+str+"!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Вкажіть розмір масиву: ");
        int size = scanner.nextInt();
        //System.out.println("size = "+size);
        int[] array = new int[size];
        Random rand = new Random();
        int min = 18;
        int max = 60;
        for (int i =0;i<size;i++)
        {
            array[i]=rand.nextInt(max-min+1)+min;
        }
        int count = 0;
        for (int item : array)
        {
            System.out.printf("%d\t",item);
            if(item>=25)
                count++;
        }
        System.out.println();
        System.out.println("З них більше > 25 = "+count);
//        int num = rand.nextInt(max-min+1)+min;
//        System.out.println("Num = "+ num);
    }
}