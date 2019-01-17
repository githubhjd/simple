package com.springboot.simple.domain;

import java.util.List;

public class SysRole {
    private Integer id;
    private String role_name;

    //角色可有多个权限，对应role_auth
    private List<SysAuth> authList;

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

    public List<SysAuth> getAuthList() {
        return authList;
    }

    public void setAuthList(List<SysAuth> authList) {
        this.authList = authList;
    }
}
