package ru.gb.mikenord.module02.homework03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PhoneBook {
    private HashMap<String, ArrayList> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String name, String phoneNumber) {
        if (name == null || phoneNumber == null) {
            return;
        }
        if (!phoneBook.containsKey(name)) {
            phoneBook.put(name, new ArrayList<String>());
        }
        phoneBook.get(name).add(phoneNumber);
    }

    public ArrayList get(String name) {
        return phoneBook.containsKey(name) ? phoneBook.get(name): new ArrayList<>();
    }
}
