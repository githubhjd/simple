package com.springboot.simple.controller;

import com.springboot.simple.dao.SysUserDao;
import com.springboot.simple.domain.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjd on 2019/1/9 0009
 */

@RestController
public class BackUserController {
    //后台管理系统超级管理员和一般管理员

    @Autowired
    private SysUserDao sysUserDao;

    //获取验证码
    @RequestMapping("/drawCheckCode")
    @ResponseBody
    public void drawCheckCode(HttpServletResponse resp, HttpSession session) throws IOException{
        resp.setContentType("image/jpg");
        int width = 116;
        int height = 36;
        Captcha c = Captcha.getInstance();
        c.set(width, height);
        String checkcode = c.generateCheckcode();
        //验证码放入session
        session.setAttribute("cCode", checkcode);
        OutputStream os = resp.getOutputStream();
        ImageIO.write(c.generateCheckImg(checkcode), "jpg", os);
    }

    @RequestMapping(value = "/backLogin", method = RequestMethod.POST)
    public Map backLogin(@RequestBody String str,HttpSession session){
        //获取session中的验证码
        String cCode = String.valueOf(session.getAttribute("cCode"));
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String username = URLDecoder.decode(temp[0].replace("userName=",""));
        String password = URLDecoder.decode(temp[1].replace("password=",""));
        String code = URLDecoder.decode(temp[2].replace("code=",""));
        Map map = new HashMap();
        if (code.equals(cCode)){
            map.put("TAG",'1');
        }else {
            map.put("TAG",'0');
        }
        return map;
    }
}
