package com.springboot.simple.service.impl;

import com.springboot.simple.dao.SysUserSysRoleSysAuthDao;
import com.springboot.simple.service.SysUserSysRoleSysAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserSysRoleAuthSysServiceImpl implements SysUserSysRoleSysAuthService {

    @Autowired
    private SysUserSysRoleSysAuthDao sysUserSysRoleSysAuthDao;

    @Override
    public void delSysRole(Integer sys_user_id, Integer sys_role_id) {
        sysUserSysRoleSysAuthDao.delSysRole(sys_user_id, sys_role_id);
    }

    @Override
    public void delSysAuth(Integer sys_role_id, Integer sys_auth_id) {
        sysUserSysRoleSysAuthDao.delSysAuth(sys_role_id, sys_auth_id);
    }

    @Override
    public void saveSysUserSysRole(Integer sys_user_id, Integer sys_role_id) {
        sysUserSysRoleSysAuthDao.saveSysUserSysRole(sys_user_id, sys_role_id);
    }

    @Override
    public void saveSysRoleSysAuth(Integer sys_role_id, Integer sys_auth_id) {
        sysUserSysRoleSysAuthDao.saveSysRoleSysAuth(sys_role_id, sys_auth_id);
    }
}
