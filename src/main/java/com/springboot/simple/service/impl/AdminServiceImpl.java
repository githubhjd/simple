package com.springboot.simple.service.impl;


import com.springboot.simple.dao.SysAuthDao;
import com.springboot.simple.dao.SysRoleDao;
import com.springboot.simple.domain.SysAuth;
import com.springboot.simple.domain.SysRole;
import com.springboot.simple.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysAuthDao sysAuthDao;

    @Override
    public List<SysRole> querySysRoleList() {
        return sysRoleDao.querySysRoleList();
    }

    @Override
    public void deleteOneSysRoleById(Integer id) {
        sysRoleDao.deleteOneSysRoleById(id);
    }

    @Override
    public List<SysAuth> querySysAuthList() {
        return sysAuthDao.querySysAuthList();
    }

    @Override
    public void deleteOneSysAuthById(Integer id) {
        sysAuthDao.deleteOneSysAuthById(id);
    }
}
