package com.example.hellospringboot.controller;

import com.example.hellospringboot.entity.Products;
import com.example.hellospringboot.service.FavorService;
import com.example.hellospringboot.service.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("favors")
public class FavorController {

    @Resource
    private FavorService favorService;

    @Resource
    private ProductsService productsService;

    @RequestMapping("{uid}")
    @ResponseBody
    public List<Products> getFavors(@PathVariable int uid) {
        List<Integer> allByUid = favorService.findAllByUid(uid);

        System.out.println(allByUid);
        if (allByUid.size() != 0) {
            List<Products> productsList = new ArrayList<>();
            for (Integer integer : allByUid) {
                productsList.add(productsService.findBySid(integer));
            }
            System.out.println(productsList);
            return productsList;
        }
        return null;
    }

    @RequestMapping("delete")
    public int delete(int uid, int sid) {
        favorService.deleteOneFavorBySid(uid, sid);
        return productsService.reduceFavorTime(sid);
    }

    @RequestMapping("checkFavored")
    @ResponseBody
    public int checkFavored(int uid, int sid) {
        return favorService.checkFavored(uid, sid);
    }

}
