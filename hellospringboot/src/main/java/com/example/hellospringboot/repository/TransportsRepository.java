package com.example.hellospringboot.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hellospringboot.entity.Transports;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportsRepository extends BaseMapper<Transports> {

}
