package com.example.hellospringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity //这个实体类会映射为一张表
@Data //自动生成getter/setter toString hashCode equals
@AllArgsConstructor //自动生成带所有参数的构造方法
@NoArgsConstructor //自动生成默认不带参数的构造方法
public class Transports {

    //所有的属性一律都是私有。
    @Id //表示是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //使用默认的主键生成策略 auto_increment
    @TableId(value = "tid",type = IdType.AUTO)
    private int tid;            //编号，自增
    private String transType;   //交通工具种类
    private String transDesc;    //交通工具描述
    private int transPrice;      //使用的价格


}
