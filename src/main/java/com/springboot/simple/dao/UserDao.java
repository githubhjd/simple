package com.springboot.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

@Mapper
@Component
public interface UserDao {

    String selectPasswordByUsername(@Param("username") String username);

    String selectPasswordByAddress(@Param("address") String address);

    Map selectByUsername(@Param("username") String username);

    void insertUser(@Param("username") String username, @Param("password") String password, @Param("address") String address, @Param("add_time") int add_time);
}
