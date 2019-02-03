package com.springboot.simple.service.impl;

import com.springboot.simple.dao.SysRoleDao;
import com.springboot.simple.domain.SysRole;
import com.springboot.simple.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public List<SysRole> querySysRoleList() {
        return sysRoleDao.querySysRoleList();
    }

    @Override
    public void deleteOneSysRoleById(Integer id) {
        sysRoleDao.deleteOneSysRoleById(id);
    }
}
