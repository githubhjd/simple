package com.springboot.simple.controller;

import com.springboot.simple.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//@RestController(value = "/")
@Controller
public class IndexController {

//    @GetMapping(value = "/login")
//    public String login(Model model, @RequestParam(value = "error", required = false) String error){
//        if (error != null){
//            model.addAttribute("error","用户名或密码错误");
//        }
//        return "/page/index";
//    }
    @Autowired
    private UserDao userDao;

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
}
