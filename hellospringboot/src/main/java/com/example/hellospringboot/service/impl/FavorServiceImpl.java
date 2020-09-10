package com.example.hellospringboot.service.impl;

import com.example.hellospringboot.entity.Favor;
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

    @Override
    public void deleteOneFavorBySid(int uid, int sid) {
        favorMapper.deleteOneFavor(uid, sid);
    }

    @Override
    public int checkFavored(int uid, int sid) {
        List<Favor> favors = favorMapper.checkFavored(uid, sid);
        if (favors != null && favors.size() >= 1)
            return 1;
        return 0;
    }

    @Override
    public void addFavor(Favor favor) {
        favorMapper.insert(favor);
    }
}
