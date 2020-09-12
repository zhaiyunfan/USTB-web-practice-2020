package com.example.hellospringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hellospringboot.entity.User;
import com.example.hellospringboot.repository.UserMapper;
import com.example.hellospringboot.service.UserService;
import com.example.hellospringboot.util.MyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User reg(User user) {
        user.setPassword(MyUtil.encodeStringByMd5(user.getPassword()));
        // 根据手机号默认生成一个用户名
        user.setUsername(MyUtil.generateRandomUsername(user.getPhoneNum()));
        user.setRegDate(new Date());
        user.setStatus(true);
        int res = userMapper.insert(user);
        if (res > 0) {
            return userMapper.selectById(user.getUid());
        }
        return null;
    }

    @Override
    public User login(String identity, String password) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        password = MyUtil.encodeStringByMd5(password);
        switch (MyUtil.checkIdentifyType(identity)) {
            case MOBILE:
                wrapper.eq("phone_num", identity).eq("password", password);
                break;
            case EMAIL:
                wrapper.eq("email", identity).eq("password", password);
                break;
            case USERNAME:
                wrapper.eq("username", identity).eq("password", password);
                break;
        }
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User login2(String identity, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone_num", identity).eq("password", password);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User update(User user) {
        userMapper.updateById(user);
        return userMapper.selectById(user.getUid());
    }

    @Override
    public boolean deleteUserByUid(int uid) {
        int res = userMapper.deleteById(uid);
        return res > 0;
    }

    @Override
    public User findByUserId(int uid) {
        return userMapper.selectById(uid);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public int checkUpdateValue(String update) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        switch (MyUtil.checkIdentifyType(update)) {
            case MOBILE:
                wrapper.eq("phone_num", update);
                break;
            case EMAIL:
                wrapper.eq("email", update);
                break;
            case USERNAME:
                wrapper.eq("username", update);
                break;
        }
        User user = userMapper.selectOne(wrapper);
        if (user != null)
            return user.getUid();
        else return 0;
    }
}
