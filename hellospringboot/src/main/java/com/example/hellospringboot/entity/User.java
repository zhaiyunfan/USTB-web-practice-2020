package com.example.hellospringboot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @TableId(value = "uid", type = IdType.AUTO)
    private int uid;
    @Column(length = 50)
    private String username;
    @Column(length = 50)
    private String password;
    @Column(length = 50)
    private String email;
    @Column(length = 4)
    private String gender;
    private Date birthday;
    @Column(length = 11)
    private String phoneNum;
    private Date regDate;
    private boolean status;

    private transient List<Products> productsList;
}
