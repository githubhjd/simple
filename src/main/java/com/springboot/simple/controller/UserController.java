package com.springboot.simple.controller;

import com.springboot.simple.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;

@RestController
public class UserController {
    //会员的注册登录
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    public String userReg(@RequestBody String str){
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String username = URLDecoder.decode(temp[0].replace("username=",""));
        String password = URLDecoder.decode(temp[1].replace("password=",""));
        String address = URLDecoder.decode(temp[2].replace("address=",""));
        String passRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        int add_time = (int)(System.currentTimeMillis()/1000);
        if (userDao.selectPasswordByUsername(username).length() != 0){
            return "repeat_Username";
        }else if (!password.matches(passRegex)){
            return "password_wrong";
        }else {
            userDao.insertUser(username,password,address,add_time);
            return "reg_success";
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String userLogin(@RequestBody String str){
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String address = URLDecoder.decode(temp[0].replace("address=",""));
        String password = URLDecoder.decode(temp[1].replace("password=",""));
        String dbPassword = userDao.selectPasswordByAddress(address);
        if (password.equals(dbPassword)){
            return address;
        }else {
            return "login_fail";
        }
    }
}
