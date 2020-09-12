package com.example.hellospringboot.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hellospringboot.entity.Favor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavorMapper extends BaseMapper<Favor> {

    @Select("select sid from favor where uid=#{uid}")
    public List<Integer> findAllByUid(int uid);

    @Select("delete from favor where uid=#{uid} and sid=#{sid}")
    public void deleteOneFavor(int uid, int sid);
    
    @Select("select * from favor where uid=#{uid} and sid=#{sid}")
    public List<Favor> checkFavored(int uid, int sid);
}
