package com.example.hellospringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.hellospringboot.repository")
public class HellospringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellospringbootApplication.class, args);
    }

}
