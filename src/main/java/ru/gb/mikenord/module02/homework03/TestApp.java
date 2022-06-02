package ru.gb.mikenord.module02.homework03;

import java.util.ArrayList;
import java.util.HashMap;

public class TestApp {

    public static void main(String[] args) {
        //  task 01
        String[] wordArray = "В первой форме конструктора создаётся пустое древовидное множество Во второй древовидное множество В третьей пустое древовидное множество".split(" ");

        HashMap<String, Integer> wordSet = new HashMap<>();

        for (String s : wordArray) {
            wordSet.put(s, wordSet.getOrDefault(s, 0) + 1);
        }
        System.out.println("Task 01");
        System.out.println(wordSet);


        //  task 02

        PhoneBook myPBook = new PhoneBook();

        myPBook.add("Иванов", "+79299299229");
        myPBook.add("Петров", "+19299287346");
        myPBook.add("Сидоров", "+792992384529");
        myPBook.add("Михайлов", "+79119244572");
        myPBook.add("Романов", "+79061135214");
        myPBook.add("Иванов", "89299299230");
        myPBook.add("Сидоров", "69229");
        myPBook.add("Шишкин", "+79349222113");

        String[] testName = {"Иванов", "Петров", "Сидоров", "Михайлов", "Романов", "Юрьев", "Зайцев"};

        System.out.println("\nTask 02");

        for (String s : testName) {
            ArrayList tmpList = myPBook.get(s);
            System.out.print("По фамилии \"" + s + "\" найдено номеров - " + tmpList.size());
            if (tmpList.size() > 0) {
                for (Object o : tmpList) {
                    System.out.print (", " + (String) o);
                }
            }
            System.out.println();
        }
    }
}
