package com.springboot.simple.service;

import com.springboot.simple.domain.SysRole;

import java.util.List;

public interface SysRoleService {

    List<SysRole> querySysRoleList();

    void deleteOneSysRoleById(Integer id);
}
