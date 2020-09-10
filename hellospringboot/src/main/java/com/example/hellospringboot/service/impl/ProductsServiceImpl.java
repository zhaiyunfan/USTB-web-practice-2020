package com.example.hellospringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hellospringboot.entity.Products;
import com.example.hellospringboot.repository.ProductsMapper;
import com.example.hellospringboot.service.ProductsService;

import javax.annotation.Resource;

public class ProductsServiceImpl implements ProductsService {

    @Resource
    private ProductsMapper productMapper;

    @Override
    public Products findBySid(int sid) {
        return productMapper.selectById(sid);
    }

    @Override
    public Products findByName(String name) {
        QueryWrapper<Products> wrapper = new QueryWrapper<>();
        wrapper.eq("product_name", name);
        return productMapper.selectOne(wrapper);
    }
}
