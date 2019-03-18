package com.springboot.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ColDao {

    void insertCol(@Param("col_post_type") String col_post_type,@Param("address") String address,@Param("col_time") int col_time);

    List<Map<String, Object>> selectColByAddress(@Param("address") String address);

    String selectColPostTypeByAddress(@Param("address") String address);

    String selectAddressByColPostType(@Param("col_post_type") String col_post_type);
}
