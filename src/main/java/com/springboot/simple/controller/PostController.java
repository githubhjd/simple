package com.springboot.simple.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;

@RestController
public class PostController {
    //接收贴子信息
    @RequestMapping(value = "/postReceive",method = RequestMethod.POST)
    public void postReceive(@RequestBody String str){
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String post_type =URLDecoder.decode(temp[0].replace("post_type=",""));
        String title = URLDecoder.decode(temp[0].replace("title=",""));
        String main_spec = URLDecoder.decode(temp[1].replace("main_spec=",""));
        String bounty= URLDecoder.decode(temp[2].replace("bounty=",""));
    }
}
