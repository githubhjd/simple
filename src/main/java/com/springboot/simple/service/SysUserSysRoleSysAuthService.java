package com.springboot.simple.service;

public interface SysUserSysRoleSysAuthService {

    void delSysRole(Integer sys_user_id, Integer sys_role_id);

    void delSysAuth(Integer sys_role_id, Integer sys_auth_id);

    void saveSysUserSysRole(Integer sys_user_id, Integer sys_role_id);

    void saveSysRoleSysAuth(Integer sys_role_id, Integer sys_auth_id);
}
