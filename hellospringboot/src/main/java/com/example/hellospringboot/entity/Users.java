package com.example.hellospringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity //这个实体类会映射为一张表
@Data //自动生成getter/setter toString hashCode equals
@AllArgsConstructor //自动生成带所有参数的构造方法
@NoArgsConstructor //自动生成默认不带参数的构造方法
public class Users {

    //所有的属性一律都是私有。
    @Id //表示是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用默认的主键生成策略 auto_increment
    @TableId(value = "uid",type = IdType.AUTO)
    private int uid; //用户编号
    private String username; //用户名
    private String password; //密码
    private String birthday; //生日
    @Column(length = 2)
    private String gender; //性别  无Column注解时占用255，'男' '女' ，'M','F' MALE FEMALE
    private String regDate; //注册日期
    private int status; //用户的状态  状态：1 可用 0 禁用
}
