package com.example.android.uchu;

import java.util.ArrayList;

public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String birthday;
    private String city;
    private String skill;
    private String info;
    private ArrayList<Integer> savedContacts;

    public User(String email, String password, String name, String surname, String birthday, String city, String skill) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.city = city;
        this.skill = skill;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCity() {
        return city;
    }

    public String getSkill() {
        return skill;
    }

    public String getInfo() {
        return info;
    }

    public ArrayList<Integer> getSavedContacts() {
        return savedContacts;
    }
}
