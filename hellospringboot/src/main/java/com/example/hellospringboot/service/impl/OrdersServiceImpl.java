package com.example.hellospringboot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hellospringboot.entity.Orders;
import com.example.hellospringboot.entity.Products;
import com.example.hellospringboot.repository.OrdersRepository;
import com.example.hellospringboot.service.OrdersService;
import com.example.hellospringboot.util.MyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersRepository ordersRepository;

    @Override
    public Orders addOrder(Products product, int uid) {
        Orders order = new Orders();
        order.setOrderDate(MyUtil.utilDateToChineseString(new Date()));
        order.setSid(product.getSid());
        order.setUid(uid);
        order.setAddr(product.getAddr());
        order.setPrice(product.getPrice());
        order.setIsPaid(0);
        int result = ordersRepository.insert(order);
        if (result > 0) {
            return ordersRepository.selectById(order.getOid());
        }
        return null;
    }

    @Override
    public boolean deleteOrder(int uid, int oid) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("oid", oid);
        int result = ordersRepository.delete(wrapper);
        return result > 0;
    }

    @Override
    public List<Orders> queryAllOrder(int uid) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        return ordersRepository.selectList(wrapper);
    }

    @Override
    public Orders payOrder(int uid, int oid) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid).eq("oid", oid);
        Orders tmp_order = ordersRepository.selectOne(wrapper);
        tmp_order.setIsPaid(1);
        ordersRepository.updateById(tmp_order);
        return ordersRepository.selectById(tmp_order.getOid());
    }

    @Override
    public int settle(int uid) {
        int total = 0;
        Orders tmp = new Orders();
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        List<Orders> ordersList = ordersRepository.selectList(wrapper);
        for (Orders order : ordersList) {
            if (order.getIsPaid() == 0)
                total += order.getPrice();
        }
        return total;
    }

    @Override
    public void allSettle(int uid) {
        Orders tmp = new Orders();
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        List<Orders> ordersList = ordersRepository.selectList(wrapper);
        for (Orders orders : ordersList) {
            if (orders.getIsPaid() == 0)
                payOrder(uid, orders.getOid());
        }
    }

    @Override
    public int checkOrderedByUser(int uid, int sid) {
        System.out.println("uid->" + uid);
        System.out.println("sid->" + sid);
        List<Orders> orders = ordersRepository.checkOrderedByUser(uid, sid);
        if (orders != null && orders.size() >= 1)
            return 1;
        return 0;
    }
}
