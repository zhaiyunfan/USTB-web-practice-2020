package com.example.hellospringboot.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hellospringboot.entity.Orders;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends BaseMapper<Orders> {

    @Select("select * from orders where uid=#{uid} and sid=#{sid} and is_paid=0")
    public List<Orders> checkOrderedByUser(int uid, int sid);


}
