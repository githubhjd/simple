package com.springboot.simple.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hjd on 2019/1/9 0009
 */
@RestController
public class BackUserController {
    //后台管理系统超级管理员和一般管理员
    @RequestMapping(value = "/backlogin")
    public void backLogin(@RequestParam String str){
        System.out.print(str);
    }
}
