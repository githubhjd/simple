package com.springboot.simple.controller;

import com.springboot.simple.dao.PostDao;
import com.springboot.simple.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Map;

//@RestController(value = "/")
@Controller
public class IndexController {

    //将String字符串进行Base64解密
    public static String Base64ToString(String text){
        byte[] textByte = Base64.getDecoder().decode(text);
        String jmtextByte = new String(textByte);
//        System.out.println(jmtextByte);
        return jmtextByte;
    }

//    @GetMapping(value = "/login")
//    public String login(Model model, @RequestParam(value = "error", required = false) String error){
//        if (error != null){
//            model.addAttribute("error","用户名或密码错误");
//        }
//        return "/page/index";
//    }
    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    //会员用户中心
    @RequestMapping(value = "/html/user/index")
    public String htmlUserIndex(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        modelMap.put("username", map.get("username"));
        modelMap.put("address", map.get("address"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        return "/html/user/index";
    }

    //会员的贴子
    @RequestMapping(value = "/html/user/post")
    public String htmlUserPost(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        modelMap.put("username", map.get("username"));
        modelMap.put("address", map.get("address"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        return "/html/user/post";
    }

    //会员基本设置
    @RequestMapping(value = "/html/user/set")
    public String htmlUserSet(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("city", map.get("city"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        return "/html/user/set";
    }

//    会员发帖
    @RequestMapping(value = "/html/jie/add")
    public String htmlJieAdd(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        return "/html/jie/add";
    }

    //   会员登录主页
    @RequestMapping(value = "/html/indexSuc")
    public String htmlIndexSuc(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        return "/html/indexSuc";
    }

    //  会员贴子信息页面
    @RequestMapping(value = "/html/jie/detail")
    public String htmlJieDetail(ModelMap modelMap, String post_type){
//        System.out.println(post_type);
        String address = Base64ToString(post_type.substring(3,post_type.length()-10));
        String timestamp = post_type.substring(post_type.length()-10,post_type.length());
        Map map = userDao.selectByAddress(address);
        Map map1 = postDao.selectByPostTypeAndAddTime(post_type, timestamp);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("title", map1.get("title"));
        modelMap.put("main_spec", map1.get("main_spec"));
        modelMap.put("post_bounty", map1.get("post_bounty"));
        modelMap.put("post_type",map1.get("post_type"));
        return "/html/jie/detail";
    }

    //    会员修改贴子
    @RequestMapping(value = "/html/jie/editAdd")
    public String htmlJieEditAdd(ModelMap modelMap, String post_type){
        String address = Base64ToString(post_type.substring(3,post_type.length()-10));
        String timestamp = post_type.substring(post_type.length()-10,post_type.length());
        String select_value = post_type.substring(0,3);
        Map map = userDao.selectByAddress(address);
        Map map1 = postDao.selectByPostTypeAndAddTime(post_type, timestamp);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("select_value", select_value);
        modelMap.put("title", map1.get("title"));
        modelMap.put("main_spec", map1.get("main_spec"));
        modelMap.put("post_bounty", map1.get("post_bounty"));
        modelMap.put("post_type",map1.get("post_type"));
        return "/html/jie/editAdd";
    }
}
