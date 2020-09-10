package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Transports;

import java.util.List;

public interface TransportsService {

    //查询所有交通工具
    public List<Transports> queryAllTransports();

    //查询列车
    public List<Transports> queryAllTrain();

    //查询飞机
    public List<Transports> queryAllPlane();

    //查询船舶
    public List<Transports> queryAllBoat();



}
