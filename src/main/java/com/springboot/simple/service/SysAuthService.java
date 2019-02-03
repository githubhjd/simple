package com.springboot.simple.service;

import com.springboot.simple.domain.SysAuth;

import java.util.List;

public interface SysAuthService {

    List<SysAuth> querySysAuthList();

    void deleteOneSysAuthById(Integer id);
}
