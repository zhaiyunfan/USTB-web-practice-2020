package com.example.hellospringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public Map<String,Object> hello(){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code",200);
        result.put("data","北京科技大学");
        return result;
    }
}
