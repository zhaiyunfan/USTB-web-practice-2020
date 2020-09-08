package com.example.hellospringboot.service;


import com.example.hellospringboot.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

    @Resource
    private UsersService usersService;


    @Test
    public void testReg(){
        Users user = new Users();
        user.setUsername("小沈阳");
        user.setPassword("123456");
        user.setBirthday("1999-10-10");
        user.setRegDate("2020-09-01");
        user.setGender("女");
        user.setStatus(1);

        user= usersService.reg(user);
        if(user!=null){
            System.out.println(user);
        }else{
            System.out.println("注册失败！");
        }
    }

    @Test
    public void testLogin(){
        Users loginUser = usersService.login("宋小宝","123456");
        if(loginUser!=null){
            System.out.println("登录成功！");
        }else{
            System.out.println("登录失败!");
        }
    }

    @Test
    public void testUpdateUser(){
        //把'李白'用户查询出来
        Users user = usersService.queryUserByUid(29);
        user.setPassword("123456");
        user = usersService.updateUser(user);
        System.out.println(user);
    }
}
