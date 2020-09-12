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
import java.util.List;

@Entity//表示这个实体类会映射成一张表
@Data//自动生成getter和setter toString hashCode equals
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    //所有属性一律都是私有的
    @Id//表示它是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//使用默认的主键生成策略auto_increment
    @TableId(value = "oid",type = IdType.AUTO)
    private int oid;//订单编号
    private int uid;//所属用户
    private String orderDate;//下单时间
    private int sid;//景点编号
    private String addr;
    private double price;
    private int isPaid;//1是已经已经支付，0是未支付
}
