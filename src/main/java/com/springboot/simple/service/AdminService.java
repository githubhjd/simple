package com.springboot.simple.service;


import com.springboot.simple.domain.SysAuth;
import com.springboot.simple.domain.SysRole;

import java.util.List;

public interface AdminService {

    List<SysRole> querySysRoleList();

    void deleteOneSysRoleById(Integer id);

    List<SysAuth> querySysAuthList();

    void deleteOneSysAuthById(Integer id);
}
