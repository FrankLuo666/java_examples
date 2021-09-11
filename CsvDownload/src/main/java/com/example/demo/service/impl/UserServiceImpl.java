package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUserList() {
        List<User> list = new ArrayList<>();
        User user1 = new User(001, "Jack", "123456", 23);
        User user2 = new User(002, "Tom", "123457", 24);
        User user3 = new User(003, "Jerry", "123458", 25);
        User user4 = new User(004, "Lily", "123459", 26);
        User user5 = new User(005, "张三", "123464", 27);

        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        return list;
    }
}
