package com.springboot.simple.dao;

import com.springboot.simple.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {

    public SysUser findByUserName(String username);

}
