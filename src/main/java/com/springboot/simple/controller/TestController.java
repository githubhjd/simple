package com.springboot.simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

//    @RequestMapping("/")
//    public ModelAndView index(){
//        ModelAndView modelAndView = new ModelAndView("userList");
//        return modelAndView;
//    }

    // thymeleaf，前端接收后端的数据
    @GetMapping(value = "/thymeleaf")
    public String getMessage(Model model){
        model.addAttribute("message", "This is your message");
        return "thymeleaf";
    }

//    @GetMapping(value = "/thymeleaf")
//    public ModelAndView thymeleaf(){
//        ModelAndView modelAndView = new ModelAndView("thymeleaf");
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

//    @RequestMapping("/index")
//    public ModelAndView index(){
//        ModelAndView modelAndView = new ModelAndView("index");
//        return modelAndView;
//    }

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

    @RequestMapping("/text")
    public ModelAndView text(){
        ModelAndView modelAndView = new ModelAndView("text");
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

    //fly-3.0社区网页

    @RequestMapping("/html/catalog")
    public ModelAndView htmlCatalog(){
        ModelAndView modelAndView = new ModelAndView("/html/catalog");
        return modelAndView;
    }

    @RequestMapping("/html/index")
    public ModelAndView htmlIndex(){
        ModelAndView modelAndView = new ModelAndView("/html/index");
        return modelAndView;
    }

    @RequestMapping("/html/case/case")
    public ModelAndView htmlCaseCase(){
        ModelAndView modelAndView = new ModelAndView("/html/case/case");
        return modelAndView;
    }

    @RequestMapping("/html/common/common")
    public ModelAndView htmlCommonCommon(){
        ModelAndView modelAndView = new ModelAndView("/html/common/common");
        return modelAndView;
    }

    @RequestMapping("/html/common/filter")
    public ModelAndView htmlCommonFilter(){
        ModelAndView modelAndView = new ModelAndView("/html/common/filter");
        return modelAndView;
    }

    @RequestMapping("/html/common/footer")
    public ModelAndView htmlCommonFooter(){
        ModelAndView modelAndView = new ModelAndView("/html/common/footer");
        return modelAndView;
    }

    @RequestMapping("/html/common/header")
    public ModelAndView htmlCommonHeader(){
        ModelAndView modelAndView = new ModelAndView("/html/common/header");
        return modelAndView;
    }

    @RequestMapping("/html/common/link")
    public ModelAndView htmlCommonLink(){
        ModelAndView modelAndView = new ModelAndView("/html/common/link");
        return modelAndView;
    }

    @RequestMapping("/html/common/user-nav")
    public ModelAndView htmlCommonUserNav(){
        ModelAndView modelAndView = new ModelAndView("/html/common/user-nav");
        return modelAndView;
    }

//    @RequestMapping("/html/jie/add")
//    public ModelAndView htmlJieAdd(){
//        ModelAndView modelAndView = new ModelAndView("/html/jie/add");
//        return modelAndView;
//    }

    @RequestMapping("/html/jie/detail")
    public ModelAndView htmlJieDetail(){
        ModelAndView modelAndView = new ModelAndView("/html/jie/detail");
        return modelAndView;
    }

    @RequestMapping("/html/jie/index")
    public ModelAndView htmlJieIndex(){
        ModelAndView modelAndView = new ModelAndView("/html/jie/index");
        return modelAndView;
    }

    @RequestMapping("/html/other/404")
    public ModelAndView htmlOther404(){
        ModelAndView modelAndView = new ModelAndView("/html/other/404");
        return modelAndView;
    }

    @RequestMapping("/html/other/notice")
    public ModelAndView htmlOtherNotice(){
        ModelAndView modelAndView = new ModelAndView("/html/other/notice");
        return modelAndView;
    }

    @RequestMapping("/html/other/tips")
    public ModelAndView htmlOtherTips(){
        ModelAndView modelAndView = new ModelAndView("/html/other/tips");
        return modelAndView;
    }

    @RequestMapping("/html/user/activate")
    public ModelAndView htmlUserActivate(){
        ModelAndView modelAndView = new ModelAndView("/html/user/activate");
        return modelAndView;
    }

    @RequestMapping("/html/user/forget")
    public ModelAndView htmlUserForget(){
        ModelAndView modelAndView = new ModelAndView("/html/user/forget");
        return modelAndView;
    }

    @RequestMapping("/html/user/home")
    public ModelAndView htmlUserHome(){
        ModelAndView modelAndView = new ModelAndView("/html/user/home");
        return modelAndView;
    }

//    @RequestMapping("/html/user/index")
//    public ModelAndView htmlUserIndex(){
//        ModelAndView modelAndView = new ModelAndView("/html/user/index");
//        return modelAndView;
//    }

    @RequestMapping("/html/user/login")
    public ModelAndView htmlUserLogin(){
        ModelAndView modelAndView = new ModelAndView("/html/user/login");
        return modelAndView;
    }

    @RequestMapping("/html/user/message")
    public ModelAndView htmlUserMessage(){
        ModelAndView modelAndView = new ModelAndView("/html/user/message");
        return modelAndView;
    }

    @RequestMapping("/html/user/reg")
    public ModelAndView htmlUserReg(){
        ModelAndView modelAndView = new ModelAndView("/html/user/reg");
        return modelAndView;
    }

//    @RequestMapping("/html/user/set")
//    public ModelAndView htmlUserSet(){
//        ModelAndView modelAndView = new ModelAndView("/html/user/set");
//        return modelAndView;
//    }

    //view中的html
    @RequestMapping("/views/common/link")
    public ModelAndView viewsCommonLink(){
        ModelAndView modelAndView = new ModelAndView("/views/common/link");
        return modelAndView;
    }

    @RequestMapping("/views/common/header")
    public ModelAndView viewsCommonHeader(){
        ModelAndView modelAndView = new ModelAndView("/views/common/header");
        return modelAndView;
    }

    @RequestMapping("/views/common/footer")
    public ModelAndView viewsCommonFooter(){
        ModelAndView modelAndView = new ModelAndView("/views/common/footer");
        return modelAndView;
    }

    @RequestMapping("/views/user/reg")
    public ModelAndView viewsCommonReg(){
        ModelAndView modelAndView = new ModelAndView("/views/user/reg");
        return modelAndView;
    }

}
