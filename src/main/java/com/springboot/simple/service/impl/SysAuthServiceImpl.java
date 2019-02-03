package com.springboot.simple.service.impl;

import com.springboot.simple.dao.SysAuthDao;
import com.springboot.simple.domain.SysAuth;
import com.springboot.simple.service.SysAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysAuthServiceImpl implements SysAuthService {

    @Autowired
    private SysAuthDao sysAuthDao;

    @Override
    public List<SysAuth> querySysAuthList() {
        return sysAuthDao.querySysAuthList();
    }

    @Override
    public void deleteOneSysAuthById(Integer id) {
        sysAuthDao.deleteOneSysAuthById(id);
    }
}
