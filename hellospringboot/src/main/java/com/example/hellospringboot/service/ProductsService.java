package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Products;

public interface ProductsService {
    
    // 根据景点id获取景点信息
    public Products findBySid(int sid);
    
    // 根据景点名称
    public Products findByName(String name);
    
}
