package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Users;

public interface UsersService {
    //用户注册
    public Users reg(Users user);

    //用户登录
    public Users login(String username,String password);
}
