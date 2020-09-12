package com.example.hellospringboot.service;



import com.example.hellospringboot.entity.Orders;
import com.example.hellospringboot.entity.Products;

import java.util.List;

public interface OrdersService {
    //添加订单
    public Orders addOrder(Products product, int uid);
    //删除订单
    public boolean deleteOrder(int uid, int oid);
    //获取所有订单的信息
    public List<Orders> queryAllOrder(int uid);
    //支付订单
    public Orders payOrder(int uid, int oid);
    //计算总金额
    public int settle(int uid);
    //全部结算
    public void allSettle(int uid);
    
    public int checkOrderedByUser(int uid, int sid);
}
