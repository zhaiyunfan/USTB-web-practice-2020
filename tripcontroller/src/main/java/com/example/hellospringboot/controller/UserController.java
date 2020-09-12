package com.example.hellospringboot.controller;

import com.example.hellospringboot.entity.User;
import com.example.hellospringboot.service.UserService;
import com.example.hellospringboot.util.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("reg")
    @ResponseBody
    public Map<String, Object> reg(@RequestBody User user) {

        Map<String, Object> result = new HashMap<>();

        if (userService.checkUpdateValue(user.getPhoneNum()) != 0) {
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "该手机号已被注册");
            return result;
        }

        User regUser = userService.reg(user);

        result.put("code", regUser == null ? 400 : 200);
        result.put("data", regUser);
        result.put("msg", regUser == null ? "regFailure" : "regSuccess");
        System.out.println(result);
        return result;
    }

    @PostMapping("auth")
    @ResponseBody
    public Map<String, Object> login(String identify, String password) {
        Map<String, Object> result = new HashMap<>();
        User loginUser = userService.login(identify, password);
        result.put("code", loginUser == null ? 400 : 200);
        result.put("data", loginUser);
        result.put("msg", loginUser == null ? "loginFailure" : "loginSuccess");
        System.out.println(result);
        return result;
    }

    @PostMapping("auth2")
    @ResponseBody
    public Map<String, Object> login2(String identify, String password) {
        Map<String, Object> result = new HashMap<>();
        User login2 = userService.login2(identify, password);
        result.put("code", login2 == null ? 400 : 200);
        result.put("data", login2);
        result.put("msg", login2 == null ? "loginFailure" : "loginSuccess");
        System.out.println(result);
        return result;
    }

    @PostMapping("changePassword")
    @ResponseBody
    public Map<String, Object> changePassword(int uid, String password) {
        User user = userService.findByUserId(uid);
        user.setPassword(MyUtil.encodeStringByMd5(password));
        return update(user);
    }

    @PostMapping("update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody User user) {
        System.out.println(user);
        Map<String, Object> result = new HashMap<>();
        User update = userService.update(user);
        result.put("code", update == null ? 400 : 200);
        result.put("data", update);
        result.put("msg", update == null ? "updateFailure" : "updateSuccess");
        System.out.println(update);
        return result;
    }


    @PostMapping("changeBasic")
    @ResponseBody
    public Map<String, Object> changeBasic(int uid, String username, String gender, long birthday) {
        int i = userService.checkUpdateValue(username);
        if ( i != 0 && i != uid) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "该用户名已被使用");
            return result;
        }
        User user = userService.findByUserId(uid);
        user.setUsername(username);
        user.setGender(gender);
        user.setBirthday(new Date(birthday));
        return update(user);
    }

    @PostMapping("changeEmail")
    @ResponseBody
    public Map<String, Object> changeEmail(int uid, String email) {
        int i = userService.checkUpdateValue(email);
        if ( i != 0 && i != uid) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "该邮箱已被绑定");
            return result;
        }
        User user = userService.findByUserId(uid);
        user.setEmail(email);
        return update(user);
    }

    @PostMapping("changePhoneNum")
    @ResponseBody
    public Map<String, Object> changePhoneNum(int uid, String phoneNum) {
        int i = userService.checkUpdateValue(phoneNum);
        if ( i != 0 && i != uid) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "该手机号已被绑定");
            return result;
        }

        User user = userService.findByUserId(uid);
        user.setPhoneNum(phoneNum);
        return update(user);
    }
}

