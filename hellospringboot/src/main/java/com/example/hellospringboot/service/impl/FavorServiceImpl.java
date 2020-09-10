package com.example.hellospringboot.service.impl;

import com.example.hellospringboot.repository.FavorMapper;
import com.example.hellospringboot.service.FavorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FavorServiceImpl implements FavorService {

    @Resource
    private FavorMapper favorMapper;


    @Override
    public List<Integer> findAllByUid(int uid) {
        return favorMapper.findAllByUid(uid);
    }
}
