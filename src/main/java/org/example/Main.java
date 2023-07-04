package org.example;

import org.example.hw1_customArrayList.CustomArrayList;
import org.example.hw1_customArrayList.CustomList;



public class Main {
    public static void main(String[] args) {
        CustomList<String > strings = new CustomArrayList<>(4);
        strings.add("Один");
        strings.add("Два");
        strings.add("Четыре");
        strings.add("Три");
        System.out.println(strings);


        strings.sort();
        for (int i = 0; i < strings.size(); i++) {
            System.out.print(strings.get(i) + " ");
        }
        System.out.println(strings);



//        strings1.add("Один");
//        strings1.add("Два");
//        strings1.add("Три");
//        strings1.add("Три");
//
//
//        System.out.println(strings1);







    }
}