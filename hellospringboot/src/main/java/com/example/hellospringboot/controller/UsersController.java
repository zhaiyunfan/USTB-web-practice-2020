package com.example.hellospringboot.controller;

import com.example.hellospringboot.entity.Users;
import com.example.hellospringboot.service.UsersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UsersController {
    @Resource
    private UsersService usersService;

    @GetMapping("auth")
    public Map<String,Object> login(String username, String password){
        Map<String,Object> result = new HashMap<String,Object>();
        Users loginUser =  usersService.login(username,password);
        if(loginUser!=null){
            result.put("code",200);
            result.put("data",loginUser);
            result.put("msg","loginSuccess");
        }else{
            result.put("code",400);
            result.put("data",null);
            result.put("msg","loginFailure");
        }
        return result;
    }

    @PostMapping("/")
    public Map<String,Object> reg(@RequestBody Users user){

        System.out.println("要注册的用户资料是：" + user);
        Users regUser =   usersService.reg(user);
        Map<String,Object> result = new HashMap<String,Object>();
        if(regUser!=null){
            result.put("code",200);
            result.put("data",regUser);
            result.put("msg","regSuccess");
        }else{
            result.put("code",400);
            result.put("data",null);
            result.put("msg","regFailure");
        }
        return result;
    }
}
