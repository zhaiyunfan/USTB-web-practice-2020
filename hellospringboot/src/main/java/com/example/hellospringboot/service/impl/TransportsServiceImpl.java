package com.example.hellospringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hellospringboot.entity.Transports;
import com.example.hellospringboot.repository.TransportsRepository;
import com.example.hellospringboot.service.TransportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportsServiceImpl implements TransportsService {

    @Autowired
    private TransportsRepository transportsRepository;

    @Override
    public List<Transports> queryAllTransports() {
        return transportsRepository.selectList(null);
    }

    @Override
    public List<Transports> queryAllTrain() {
        QueryWrapper<Transports> queryWrapper = new QueryWrapper<Transports>();
        queryWrapper.eq("trans_type","train");
        return transportsRepository.selectList(queryWrapper);
    }

    @Override
    public List<Transports> queryAllPlane() {
        QueryWrapper<Transports> queryWrapper = new QueryWrapper<Transports>();
        queryWrapper.eq("trans_type","plane");
        return transportsRepository.selectList(queryWrapper);
    }

    @Override
    public List<Transports> queryAllBoat() {
        QueryWrapper<Transports> queryWrapper = new QueryWrapper<Transports>();
        queryWrapper.eq("trans_type","boat");
        return transportsRepository.selectList(queryWrapper);
    }


}
