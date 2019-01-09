//package com.springboot.simple.controller;
//
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController(value = "/")
//public class IndexController {
//
//    @GetMapping(value = "/login")
//    public String login(Model model, @RequestParam(value = "error", required = false) String error){
//        if (error != null){
//            model.addAttribute("error","用户名或密码错误");
//        }
//        return "/page/index";
//    }
//}
