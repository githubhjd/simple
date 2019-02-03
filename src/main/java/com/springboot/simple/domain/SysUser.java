package com.springboot.simple.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysUser implements Serializable {
    private Integer id;
    private String username;
    private String password;

    //一个用户有多个角色、对应sys_role_user表
    private List<SysRole> sysRoleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }
}
