package com.springboot.simple.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRole implements Serializable {
    private Integer id;
    private String role_name;

    //角色可有多个权限，对应role_auth
    private List<SysAuth> sysAuthList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<SysAuth> getSysAuthList() {
        return sysAuthList;
    }

    public void setSysAuthList(List<SysAuth> sysAuthList) {
        this.sysAuthList = sysAuthList;
    }
}
