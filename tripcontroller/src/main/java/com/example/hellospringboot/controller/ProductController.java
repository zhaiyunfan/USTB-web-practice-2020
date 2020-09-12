package com.example.hellospringboot.controller;

import com.example.hellospringboot.entity.Favor;
import com.example.hellospringboot.entity.Products;
import com.example.hellospringboot.service.FavorService;
import com.example.hellospringboot.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {


    @Resource
    private ProductsService productsService;

    @Resource
    private FavorService favorService;

    @RequestMapping("updateFavor")
    @ResponseBody
    public int updateFavorTime(int uid, int sid) {
        System.out.println("updateFavor");
        System.out.println("uid->" + uid);
        System.out.println("sid->" + sid);
        Favor favor = new Favor();
        favor.setUid(uid);
        favor.setSid(sid);
        favorService.addFavor(favor);
        return productsService.addFavorTime(sid);
    }

    @RequestMapping("get")
    @ResponseBody
    public Products getBySid(int sid) {
        System.out.println(sid);
        return productsService.findBySid(sid);
    }
    
    @RequestMapping("getAll")
    @ResponseBody
    public List<Products> getAll() {
        List<Products> all = productsService.findAll();
        System.out.println(all);
        return all;
    }

}
