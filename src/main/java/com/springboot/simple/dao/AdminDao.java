package com.springboot.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AdminDao {
    void insertAdmin(String username,String password,int status,int create_time,int update_time, String last_login_ip);
}
