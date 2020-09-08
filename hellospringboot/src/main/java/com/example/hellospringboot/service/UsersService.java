package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Users;

import java.util.List;

public interface UsersService {
    //用户注册
    public Users reg(Users user);

    //用户登录,考虑到用户自由的选择三种方式登录 1.用户名  2.手机号码  3.电子邮箱
    public Users login(String identify,String password);

    //根据用户编号查询单个用户
    public Users queryUserByUid(int uid);

    //更新用户资料
    public Users updateUser(Users user);

    //根据用户ID删除单个用户
    public boolean deleteUserByUid(int uid);

    //查询所用用户的
    public List<Users> queryAllUsers();

    public boolean checkMobileIsExist(String mobile);
}
