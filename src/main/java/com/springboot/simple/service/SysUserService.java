package com.springboot.simple.service;

import com.springboot.simple.domain.SysUser;

import java.util.List;

public interface SysUserService {

    List<SysUser> querySysUserList();

    SysUser querySysUserByUserName(String username);

    SysUser querySysUserById(Integer id);

    void saveOneSysUser(SysUser sysUser);

    void delOneSysUserById(Integer id);
}
