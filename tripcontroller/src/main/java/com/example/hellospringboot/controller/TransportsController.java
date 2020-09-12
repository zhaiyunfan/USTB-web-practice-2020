package com.example.hellospringboot.controller;

import com.example.hellospringboot.entity.Transports;
import com.example.hellospringboot.service.TransportsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transports")
public class TransportsController {

    @Resource
    private TransportsService transportsService;

    @GetMapping("/")
    public Map<String,Object> queryTransports(){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Transports> transportsList = transportsService.queryAllTransports();
        if(transportsList!=null){
            result.put("code", 200);
            result.put("data", transportsList);
            result.put("msg", "querySuccess");
        }else{
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "queryFailure");
        }
        return result;
    }

    @GetMapping("/train")
    public Map<String,Object> queryTrainTransports(){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Transports> trainList = transportsService.queryAllTrain();
        if(trainList!=null){
            result.put("code", 200);
            result.put("data", trainList);
            result.put("msg", "queryTrainSuccess");
        }else{
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "queryTrainFailure");
        }
        return result;
    }

    @GetMapping("/plane")
    public Map<String,Object> queryPlaneTransports(){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Transports> planeList = transportsService.queryAllPlane();
        if(planeList!=null){
            result.put("code", 200);
            result.put("data", planeList);
            result.put("msg", "queryPlaneSuccess");
        }else{
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "queryPlaneFailure");
        }
        return result;
    }

    @GetMapping("/boat")
    public Map<String,Object> queryBoatTransports(){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Transports> boatList = transportsService.queryAllBoat();
        if(boatList!=null){
            result.put("code", 200);
            result.put("data", boatList);
            result.put("msg", "queryBoatSuccess");
        }else{
            result.put("code", 400);
            result.put("data", null);
            result.put("msg", "queryBoatFailure");
        }
        return result;
    }
}
