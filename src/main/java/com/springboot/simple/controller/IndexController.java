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

//    @GetMapping(value = "/index1")
    @RequestMapping(value = "/html/user/index")
    public String index(ModelMap modelMap){
        Map map = userDao.selectByUsername("hjd");
        modelMap.put("id", map.get("id"));
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("address", map.get("address"));
        return "/html/user/index";
    }
}
