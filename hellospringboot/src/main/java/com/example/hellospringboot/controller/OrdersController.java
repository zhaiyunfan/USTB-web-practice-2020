package com.example.hellospringboot.controller;


import com.example.hellospringboot.entity.Orders;
import com.example.hellospringboot.entity.Products;
import com.example.hellospringboot.service.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("orders")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    @PostMapping("add/{uid}")
    @ResponseBody
    public Map<String, Object> addOrder(@RequestBody Products product, @PathVariable int uid) {
        System.out.println("product->" + product);
        System.out.println("uid->" + uid);
        Orders order = ordersService.addOrder(product, uid);
        Map<String, Object> result = new HashMap<String, Object>();
        if (order != null) {
            result.put("code", 200);
            result.put("data", order);
            result.put("msg", "regSuccess");
        } else {
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "regFailure");
        }
        return result;
    }

    @DeleteMapping("/{oid}")
    @ResponseBody
    public Map<String, Object> deleteOrder(@PathVariable("oid") int oid) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (ordersService.deleteOrder(oid)) {
            result.put("code", 200);
            result.put("data", null);
            result.put("msg", "deleteSuccess");
        } else {
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "deleteFailure");
        }
        return result;
    }

    @GetMapping("data")
    @ResponseBody
    public Map<String, Object> queryAllOrders() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Orders> ordersList = ordersService.queryAllOrder();
        if (ordersList != null) {
            result.put("code", 200);
            result.put("data", ordersList);
            result.put("msg", "querySuccess");
        } else {
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "queryFailure");
        }
        return result;
    }

    @PutMapping("pay/{oid}")
    @ResponseBody
    public Map<String, Object> payOrder(@PathVariable("oid") int oid) {
        System.out.println(oid);
        Map<String, Object> result = new HashMap<String, Object>();
        Orders order = ordersService.payOrder(oid);
        result.put("code", 200);
        result.put("data", order);
        result.put("msg", "paySuccess");
        return result;
    }

    @GetMapping("total")
    @ResponseBody
    public Map<String, Object> settle() {
        Map<String, Object> result = new HashMap<String, Object>();
        int total = ordersService.settle();
        result.put("code", 200);
        result.put("data", total);
        result.put("msg", "settleSuccess");
        return result;
    }

    @PutMapping("allSettle")
    @ResponseBody
    public void allSettle() {
        ordersService.allSettle();
    }

    @RequestMapping("check")
    @ResponseBody
    public int checkOrderedByUser(int uid, int sid) {
        return ordersService.checkOrderedByUser(uid, sid);
    }
}
