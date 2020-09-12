package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Products;

import java.util.List;

public interface ProductsService {
    
    // 根据景点id获取景点信息
    public Products findBySid(int sid);
    
    // 根据景点名称
    public Products findByName(String name);
    
    public int addFavorTime(int sid);
    
    public int reduceFavorTime(int sid);
    
    public List<Products> findAll();
}
