package com.example.hellospringboot;

import com.example.hellospringboot.entity.Transports;
import com.example.hellospringboot.service.TransportsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HellospringbootApplicationTests {

    @Resource
    private TransportsService transportsService;

    @Test
    public void test1() {
        Transports transports = new Transports();
        transports.setTransDesc("G857 重庆-北京");
        transports.setTransPrice(924);
        transports.setTransType("train");
        transportsService.insert(transports);
    }

    @Test
    public void test2() {
        Transports transports = new Transports();
        transports.setTransDesc("CZ6605 南方航空 武汉-北京");
        transports.setTransPrice(350);
        transports.setTransType("plane");
        transportsService.insert(transports);
    }

    @Test
    public void test3() {
        Transports transports = new Transports();
        transports.setTransDesc("D1003 呐呐呐声纳号");
        transports.setTransPrice(999);
        transports.setTransType("boat");
        transportsService.insert(transports);
    }
}
