package com.springboot.simple.controller;

import com.springboot.simple.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.Map;

@RestController
public class UserController {
    //会员的注册登录
    @Autowired
    private UserDao userDao;

    //会员注册及判断
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
        System.out.println(userDao.selectPasswordByUsername(username));
        if (Integer.parseInt(userDao.selectPasswordByUsername(username)) != 0){
            return "repeat_Username";
        }else if (!password.matches(passRegex)){
            return "password_wrong";
        }else {
            userDao.insertUser(username,password,address,add_time,1,"倔强青铜");
            return "reg_success";
        }
    }

    //会员登录及判断
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String userLogin(@RequestBody String str, ModelMap modelMap){
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String address = URLDecoder.decode(temp[0].replace("address=",""));
        String password = URLDecoder.decode(temp[1].replace("password=",""));
        String dbPassword = userDao.selectPasswordByAddress(address);
        if (password.equals(dbPassword)){
            Map map = userDao.selectByAddress(address);
            modelMap.put("username", map.get("username"));
            return address;
        }else {
            return "login_fail";
        }
    }

    //会员更新个人信息
    @RequestMapping(value = "/user/rewrite", method = RequestMethod.POST)
    public String userRewrite(@RequestBody String str){
        System.out.println(str);
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String address = URLDecoder.decode(temp[0].replace("address=",""));
        String username = URLDecoder.decode(temp[1].replace("username=",""));
        int sex = Integer.parseInt(URLDecoder.decode(temp[2].replace("sex=","")));
        String city = URLDecoder.decode(temp[3].replace("city=",""));
        String personal_note = URLDecoder.decode(temp[4].replace("personal_note=",""));
        userDao.updateUser(username, sex, city, personal_note, address);
//        String dbPassword = userDao.selectPasswordByAddress(address);
//        System.out.println(temp);
        return "success";
    }

}
