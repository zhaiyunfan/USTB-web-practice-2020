package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Products;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FavorService {

    public List<Integer> findAllByUid(int uid);
    
}
