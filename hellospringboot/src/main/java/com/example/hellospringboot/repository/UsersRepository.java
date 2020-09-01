package com.example.hellospringboot.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hellospringboot.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends BaseMapper<Users> {


}
