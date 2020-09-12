package com.example.hellospringboot.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hellospringboot.entity.Products;
import org.apache.ibatis.annotations.Select;

public interface ProductsMapper extends BaseMapper<Products> {

    @Select("update products set favor_time=favor_time+1 where sid=#{sid}")
    public void addFavorTime(int sid);

    @Select("update products set favor_time=favor_time-1 where sid=#{sid}")
    public void reduceFavorTime(int sid);

}
