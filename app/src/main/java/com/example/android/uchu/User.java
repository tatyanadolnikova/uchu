package com.example.android.uchu;

public class User {
    private String name;
    private String surname;
    private String email;
    private Boolean gender; // true: ж; false: м
    private String birthday;
    private String skill;

    public User(String name, String surname, String email, Boolean gender, String birthday, String skill) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
