package com.springboot.simple.dao;

import com.springboot.simple.domain.SysAuth;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysAuthDao {

    List<SysAuth> querySysAuthList();

    void deleteOneSysAuthById(Integer id);
}
