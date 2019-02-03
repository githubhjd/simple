package com.springboot.simple.dao;

import com.springboot.simple.domain.SysAuth;
import com.springboot.simple.domain.SysRole;
import com.springboot.simple.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysUserDao {

    public SysUser findByUserName(String username);

    public String findPasswordBySysUserName(String username);

    List<SysUser> queryAllSysUsers();

    SysUser querySysUserByUsername(String username);

    SysUser querySysUserById(Integer id);

    Integer querySysRoleIdByUserId(Integer id);//通过SysUser表中的id查询SysRole表中的id

    SysRole querySysRoleByRoleId(Integer id);

    List<Integer> querySysAuthIdByRoleId(Integer id);//通过SysRole表中的id查询SysAuth表中的id

    SysAuth querySysAuthByAuthId(Integer id);

    void saveOneSysUser(SysUser sysUser);

    void delOneSysUserById(Integer id);

}
