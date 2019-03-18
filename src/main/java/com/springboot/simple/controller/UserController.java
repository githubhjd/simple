package com.springboot.simple.controller;

import com.springboot.simple.dao.UserDao;
import com.springboot.simple.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class UserController {
    //会员的注册登录
    @Autowired
    private UserDao userDao;

    //会员注册及判断
    @RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    public String userReg(@RequestBody String str){
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String username = URLDecoder.decode(temp[0].replace("username=",""));
        String password = URLDecoder.decode(temp[1].replace("password=",""));
        String address = URLDecoder.decode(temp[2].replace("address=",""));
        String passRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        int add_time = (int)(System.currentTimeMillis()/1000);
        System.out.println(userDao.selectPasswordByUsername(username));
        if (Integer.parseInt(userDao.selectPasswordByUsername(username)) != 0){
            return "repeat_Username";
        }else if (!password.matches(passRegex)){
            return "password_wrong";
        }else {
            userDao.insertUser(username,password,address,add_time,1,"倔强青铜");
            return "reg_success";
        }
    }

    //会员登录及判断
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String userLogin(@RequestBody String str, ModelMap modelMap){
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String address = URLDecoder.decode(temp[0].replace("address=",""));
        String password = URLDecoder.decode(temp[1].replace("password=",""));
        String dbPassword = userDao.selectPasswordByAddress(address);
        if (password.equals(dbPassword) == true){
            Map map = userDao.selectByAddress(address);
            modelMap.put("username", map.get("username"));
            return address;
        }else {
            return "login_fail";
        }
    }

    //会员更新个人信息
    @RequestMapping(value = "/user/rewrite", method = RequestMethod.POST)
    public String userRewrite(@RequestBody String str){
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String address = URLDecoder.decode(temp[0].replace("address=",""));
        String username = URLDecoder.decode(temp[1].replace("username=",""));
        int sex = Integer.parseInt(URLDecoder.decode(temp[2].replace("sex=","")));
        String city = URLDecoder.decode(temp[3].replace("city=",""));
        String personal_note = URLDecoder.decode(temp[4].replace("personal_note=",""));
        userDao.updateUser(username, sex, city, personal_note, address);
//        String dbPassword = userDao.selectPasswordByAddress(address);
//        System.out.println(temp);
        return "success";
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${com.hjd}")

    private String post;
    //获取本机ip
    private String host;
    //图片存放根路径
    private String rootPath = "D:";
    //图片存放根目录下的子目录
    private String sonPath = "/IdeaProject/upload/";
    //获取图片链接
    private String imgPath = "/upload/";

    //会员头像的上传
    @RequestMapping(value = "/user/uploadProfilePhoto", method = RequestMethod.POST)
    public String uploadProfilePhoto(@RequestParam("file") MultipartFile file,@RequestParam("address") String address) throws IllegalStateException, IOException{
        if (file.isEmpty()){
            return "文件為空";
        }
        //獲取本機ip
        try{
            host = InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException e){
            logger.error("get server host Exception e:", e);
        }


        // 获取文件名
        String fileName = changName(file.getOriginalFilename());
        //logger.info("上传的文件名为：" + fileName);
        // 设置文件上传后的路径
        String filePath = rootPath + sonPath;
        logger.info("上传的文件路径" + filePath);
        logger.info("整个图片路径：" + host + ":" + post + sonPath + fileName);
        //创建文件路径
        File dest = new File(filePath + fileName);

        //圖片的訪問路徑
        String PhotoPath = ("http://" + host + ":" + post + imgPath + fileName).toString();

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            //假如文件不存在即重新创建新的文件已防止异常发生
            dest.getParentFile().mkdirs();
        }
        try {
            //transferTo（dest）方法将上传文件写到服务器上指定的文件
            file.transferTo(dest);
            //将链接保存到URL中
            System.out.println(PhotoPath);
            //将url保存到数据库
            userDao.updateUserProfilePhoto(PhotoPath, address);
            String str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"" + PhotoPath + "\"}}";
            return str;
        } catch (Exception e) {
            return "上传失败";
        }
    }

    //修改上传头像的照片，利用随机数防止重复
    public static String changName(String oldName){
        Random r = new Random();
        Date d = new Date();
//        String newName = oldName.substring(oldName.indexOf('.'));
        String newName = r.nextInt(99999999) + d.getTime() + oldName;
        return newName;
    }

    //會員密碼的重置
    @RequestMapping(value = "/user/repass", method = RequestMethod.POST)
    public String userRepass(@RequestBody String str){
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String address = URLDecoder.decode(temp[0].replace("address=",""));
        String password = URLDecoder.decode(temp[1].replace("password=",""));
        String rePassword = URLDecoder.decode(temp[2].replace("rePassword=",""));
        String myPassword = userDao.selectPasswordByAddress(address);
        if (password.equals(myPassword) == true){
            userDao.updateUserPassword(rePassword, address);
            return "repass_success";
        }else{
            return "repass_fail";
        }
    }

    //会员增加等级接口
    @RequestMapping(value = "/user/addLevel", method = RequestMethod.POST)
    public Map userAddLevel(String grade_num, String address){
        Map map = new HashMap();
        switch (Integer.valueOf(grade_num)){
            case 1:
                userDao.updateGradeNumByAddress(100,address);
                break;
            case 2:
                userDao.updateGradeNumByAddress(200,address);
                break;
            case 3:
                userDao.updateGradeNumByAddress(300,address);
                break;
            case 4:
                userDao.updateGradeNumByAddress(400,address);
                break;
            case 5:
                break;
        }
        map.put("status",0);
        return map;
    }

    //会员签到加积分
    @RequestMapping(value = "/sign/in", method = RequestMethod.POST)
    public void userSignIn(String access_address){
        int day_state = userDao.selectDayStateByAddress(access_address);
        if (String.valueOf(day_state).equals("0")){
            System.out.println("+5");
        }else {
            System.out.println("已+5");
        }
        Map map = new HashMap();
//        map.put("data",);
    }

}
