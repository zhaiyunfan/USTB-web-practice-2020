package com.example.hellospringboot.controller;

import com.example.hellospringboot.service.FavorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("favors")
public class FavorController {

    @Resource
    private FavorService favorService;

    @RequestMapping("{uid}")
    @ResponseBody
    public List<Integer> getFavors(@PathVariable int uid) {
        List<Integer> allByUid = favorService.findAllByUid(uid);
        System.out.println(allByUid);
        return allByUid;
    }

}
