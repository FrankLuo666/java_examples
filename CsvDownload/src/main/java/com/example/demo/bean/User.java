package com.example.demo.bean;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private int age;

    public User(int id, String username, String password, int age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
