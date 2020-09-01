package com.example.hellospringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hellospringboot.entity.Users;
import com.example.hellospringboot.repository.UsersRepository;
import com.example.hellospringboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users reg(Users user) {
        int result = usersRepository.insert(user);
        if(result>0){
            //usersRepository.selectById(user.getUid())
            //
            System.out.println("新用户的编号是："+user.getUid());
            return usersRepository.selectById(user.getUid()); //如果注册成功返回注册成功的用户对象。
        }
        return null; //如果注册失败返回一个空对象。
    }

    @Override
    public Users login(String username, String password) {

        QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
        wrapper.eq("username",username).eq("password",password);
        return usersRepository.selectOne(wrapper);
    }
}
