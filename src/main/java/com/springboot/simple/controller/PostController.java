package com.springboot.simple.controller;

import com.springboot.simple.dao.PostDao;
import com.springboot.simple.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;

@RestController
public class PostController {

    @Autowired
    private PostDao postDao;

    //接收贴子信息
    @RequestMapping(value = "/postReceive",method = RequestMethod.POST)
    public String postReceive(@RequestBody String str){
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        int add_time = (int)(System.currentTimeMillis()/1000);//时间戳
        String post_type = URLDecoder.decode(temp[1].replace("post_type=",""))+add_time;
        String address = URLDecoder.decode(temp[0].replace("address=",""));
//        System.out.println(address);
        String title = URLDecoder.decode(temp[2].replace("title=",""));
        String main_spec = URLDecoder.decode(temp[3].replace("main_spec=","")).replace("[pre]","<pre>").replace("[/pre]","</pre>");
        String post_bounty= URLDecoder.decode(temp[4].replace("post_bounty=",""));
        postDao.insertPost(post_type,title,main_spec,post_bounty,add_time);//插入贴子
        return post_type;
    }

    //修改贴子信息
    @RequestMapping(value = "/editPostReceive",method = RequestMethod.POST)
    public String editPostReceive(@RequestBody String str){
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        int add_time = (int)(System.currentTimeMillis()/1000);//时间戳
        String post_type = URLDecoder.decode(temp[1].replace("post_type=",""))+add_time;
        String address = URLDecoder.decode(temp[0].replace("address=",""));
//        System.out.println(address);
        String title = URLDecoder.decode(temp[2].replace("title=",""));
        String main_spec = URLDecoder.decode(temp[3].replace("main_spec=",""));
        String post_bounty= URLDecoder.decode(temp[4].replace("post_bounty=",""));
        String old_post_type = URLDecoder.decode(temp[5].replace("old_post_type=",""));
        postDao.updatePost(post_type,title,main_spec,post_bounty,add_time,old_post_type);//插入贴子
        return post_type;
    }
}