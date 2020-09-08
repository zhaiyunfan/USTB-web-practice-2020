package com.example.hellospringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hellospringboot.entity.Users;
import com.example.hellospringboot.repository.UsersRepository;
import com.example.hellospringboot.service.UsersService;
import com.example.hellospringboot.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users reg(Users user) {
        //用户注册的时候必须对明文的密码进行加密。自动注册日期写入数据库中。
        user.setPassword(MyUtil.encodeStringByMd5(user.getPassword()));
        //保存注册日期
        user.setRegDate(MyUtil.utilDateToChineseString(new Date()));
        //根据手机号码生成一个默认的用户名
        user.setUsername(MyUtil.generateRandomUsername(user.getMobile()));

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
    public Users login(String identify, String password) {
        //需要判断一下，identify是手机，电子邮箱，还是用户名
        QueryWrapper<Users> wrapper = new QueryWrapper<Users>();

        switch(MyUtil.checkIdentifyType(identify)){
            case MOBILE:
                wrapper.eq("mobile",identify).eq("password",MyUtil.encodeStringByMd5(password));
                break;
            case EMAIL:
                wrapper.eq("email",identify).eq("password",MyUtil.encodeStringByMd5(password));
                break;
            case USERNAME:
                wrapper.eq("username",identify).eq("password",MyUtil.encodeStringByMd5(password));
                break;
        }

        return usersRepository.selectOne(wrapper);
    }

    @Override
    public Users queryUserByUid(int uid) {
        return usersRepository.selectById(uid);
    }

    @Override
    public Users updateUser(Users user) {
        usersRepository.updateById(user);
        return usersRepository.selectById(user.getUid());
    }

    @Override
    public boolean deleteUserByUid(int uid) {
        int result = usersRepository.deleteById(uid);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Users> queryAllUsers() {
        return usersRepository.selectList(null);
    }

    @Override
    public boolean checkMobileIsExist(String mobile) {
        QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
        wrapper.eq("mobile",mobile);
        List<Users> users = usersRepository.selectList(wrapper);
        if(users!=null&users.size()>0){
            //说明这个手机号码已经注册了。
            return true;
        }
        return false;
    }
}
