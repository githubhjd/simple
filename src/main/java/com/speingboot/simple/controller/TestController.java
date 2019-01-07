package com.speingboot.simple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping("/404")
    public ModelAndView My404(){
        ModelAndView modelAndView = new ModelAndView("404");
        return modelAndView;
    }

    @RequestMapping("/cmsMain")
    public ModelAndView cmsMain(){
        ModelAndView modelAndView = new ModelAndView("cmsMain");
        return modelAndView;
    }

    @RequestMapping("/cms/index")
    public ModelAndView cmsIndex(){
        ModelAndView modelAndView = new ModelAndView("cms/index");
        return modelAndView;
    }

    //layui2.0

    @RequestMapping("/page/404")
    public ModelAndView page404(){
        ModelAndView modelAndView = new ModelAndView("/page/404");
        return modelAndView;
    }

    @RequestMapping("/page/main")
    public ModelAndView pageMain(){
        ModelAndView modelAndView = new ModelAndView("/page/main");
        return modelAndView;
    }

    @RequestMapping("/page/index")
    public ModelAndView pageIndex(){
        ModelAndView modelAndView = new ModelAndView("/page/index");
        return modelAndView;
    }

    @RequestMapping("/page/doc/addressDoc")
    public ModelAndView pageDocAddressDoc(){
        ModelAndView modelAndView = new ModelAndView("/page/doc/addressDoc");
        return modelAndView;
    }

    @RequestMapping("/page/doc/bodyTabDoc")
    public ModelAndView pageDocBodyTabDoc(){
        ModelAndView modelAndView = new ModelAndView("/page/doc/bodyTabDoc");
        return modelAndView;
    }

    @RequestMapping("/page/doc/NavDoc")
    public ModelAndView pageDocNavDoc(){
        ModelAndView modelAndView = new ModelAndView("/page/doc/NavDoc");
        return modelAndView;
    }

    @RequestMapping("/page/img/images")
    public ModelAndView pageImgImages(){
        ModelAndView modelAndView = new ModelAndView("/page/img/images");
        return modelAndView;
    }

    @RequestMapping("/page/login/login")
    public ModelAndView pageLoginLogin(){
        ModelAndView modelAndView = new ModelAndView("/page/login/login");
        return modelAndView;
    }

    @RequestMapping("/page/news/newsAdd")
    public ModelAndView pageNewsNewsAdd(){
        ModelAndView modelAndView = new ModelAndView("/page/news/newsAdd");
        return modelAndView;
    }

    @RequestMapping("/page/news/newsList")
    public ModelAndView pageNewsNewsList(){
        ModelAndView modelAndView = new ModelAndView("/page/news/newsList");
        return modelAndView;
    }

    @RequestMapping("/page/systemSetting/basicParameter")
    public ModelAndView pageSystemSettingBasicParameter(){
        ModelAndView modelAndView = new ModelAndView("/page/systemSetting/basicParameter");
        return modelAndView;
    }

    @RequestMapping("/page/systemSetting/icons")
    public ModelAndView pageSystemSettingIcons(){
        ModelAndView modelAndView = new ModelAndView("/page/systemSetting/icons");
        return modelAndView;
    }

    @RequestMapping("/page/systemSetting/linkList")
    public ModelAndView pageSystemSettingLinkList(){
        ModelAndView modelAndView = new ModelAndView("/page/systemSetting/linkList");
        return modelAndView;
    }

    @RequestMapping("/page/systemSetting/linkAdd")
    public ModelAndView pageSystemSettingLinkAdd(){
        ModelAndView modelAndView = new ModelAndView("/page/systemSetting/linkAdd");
        return modelAndView;
    }

    @RequestMapping("/page/systemSetting/logs")
    public ModelAndView pageSystemSettingLogs(){
        ModelAndView modelAndView = new ModelAndView("/page/systemSetting/logs");
        return modelAndView;
    }

    @RequestMapping("/page/user/changePwd")
    public ModelAndView pageUserChangePwd(){
        ModelAndView modelAndView = new ModelAndView("/page/user/changePwd");
        return modelAndView;
    }

    @RequestMapping("/page/user/userAdd")
    public ModelAndView pageUserUserAdd(){
        ModelAndView modelAndView = new ModelAndView("/page/user/userAdd");
        return modelAndView;
    }

    @RequestMapping("/page/user/userInfo")
    public ModelAndView pageUserUserInfo(){
        ModelAndView modelAndView = new ModelAndView("/page/user/userInfo");
        return modelAndView;
    }

    @RequestMapping("/page/user/userGrade")
    public ModelAndView pageUserUserGrade(){
        ModelAndView modelAndView = new ModelAndView("/page/user/userGrade");
        return modelAndView;
    }

    @RequestMapping("/page/user/userList")
    public ModelAndView pageUserUserList(){
        ModelAndView modelAndView = new ModelAndView("/page/user/userList");
        return modelAndView;
    }

}
