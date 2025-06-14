package com.StarkIndustries.SpringRedis.implementation.model;

import java.io.Serializable;

public class Person implements Serializable {

    private String personId;

    private String name;

    private String age;

    private String username;

    private String email;

    private String password;

    public Person(String name, String age, String username, String email, String password) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Person(String personId, String name, String age, String username, String email, String password) {
        this.personId = personId;
        this.name = name;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId='" + personId + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
