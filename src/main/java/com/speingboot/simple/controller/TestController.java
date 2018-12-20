package com.speingboot.simple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

//    @RequestMapping("/")
//    public ModelAndView index(){
//        ModelAndView modelAndView = new ModelAndView("userList");
//        return modelAndView;
//    }

    @GetMapping(value = "/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @GetMapping(value = "/myLogin")
    public ModelAndView myLogin(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping(value = "/main")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView("main");
        return modelAndView;
    }

//    @RequestMapping("/api")
//    public ModelAndView api(){
//        ModelAndView modelAndView = new ModelAndView("api");
//        return modelAndView;
//    }
}
