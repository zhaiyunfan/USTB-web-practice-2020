package com.example.hellospringboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

// 景点类
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "sid", type = IdType.AUTO)
    private int sid;
    private String productName;
    private double price;
    private String picFolder;
    private String addr;
    // 被收藏次数
    private int favorTime;

}
