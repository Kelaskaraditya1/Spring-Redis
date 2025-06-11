package com.StarkIndustries.SpringRedis.withDatabase.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "time_stamp")
    private LocalDateTime localDateTime;

    public Student(Long studentId, String name, String email, String username, String password, LocalDateTime localDateTime) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.localDateTime = localDateTime;
    }

    public Student(String name, String email, String username, String password, LocalDateTime localDateTime) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.localDateTime = localDateTime;
    }

    public Student() {

    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
