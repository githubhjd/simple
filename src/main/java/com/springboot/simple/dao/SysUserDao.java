package com.springboot.simple.dao;

import com.springboot.simple.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysUserDao {

    public SysUser findByUserName(String username);

    public String findPasswordBySysUserName(String username);

}
