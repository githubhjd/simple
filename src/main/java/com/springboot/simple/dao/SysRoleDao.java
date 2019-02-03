package com.springboot.simple.dao;

import com.springboot.simple.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysRoleDao {

    List<SysRole> querySysRoleList();

    void deleteOneSysRoleById(Integer id);
}
