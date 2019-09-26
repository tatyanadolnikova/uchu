package com.example.android.uchu;

public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private int gender; // true: ж; false: м
    private String birthday;
    private String city;
    private String skill;

    public User(String email, String password, String name, String surname, int gender, String birthday, String city, String skill) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
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

    public int getGender() {
        return gender;
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
}
