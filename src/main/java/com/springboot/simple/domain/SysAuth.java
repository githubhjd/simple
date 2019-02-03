package com.springboot.simple.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysAuth implements Serializable {

    private Integer id;

    private String auth_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuth_name() {
        return auth_name;
    }

    public void setAuth_name(String auth_name) {
        this.auth_name = auth_name;
    }
}
