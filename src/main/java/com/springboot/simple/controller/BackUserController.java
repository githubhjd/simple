package com.springboot.simple.controller;

import com.springboot.simple.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;

/**
 * Created by hjd on 2019/1/9 0009
 */

@RestController
public class BackUserController {
    //后台管理系统超级管理员和一般管理员

    @Autowired
    private SysUserDao sysUserDao;

    @RequestMapping(value = "/backlogin", method = RequestMethod.POST)
    public String backLogin(@RequestBody String str){
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String username = URLDecoder.decode(temp[0].replace("userName=",""));
        String password = URLDecoder.decode(temp[1].replace("password=",""));
        String code = URLDecoder.decode(temp[2].replace("code=",""));
        String password1 = sysUserDao.findPasswordBySysUserName(username);
        //验证登录用户
        if (password.equals(password1)){
            return "success";
        }else {
            return "false";
        }
    }
}
