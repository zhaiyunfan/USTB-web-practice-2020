package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.User;

import java.util.List;

public interface UserService {


    public User reg(User user);

    public User login(String identity, String password);
    
    public User login2(String identity, String password);

    public User update(User user);

    public boolean deleteUserByUid(int uid);

    public User findByUserId(int uid);

    public List<User> findAll();

    public int checkUpdateValue(String update);
}
