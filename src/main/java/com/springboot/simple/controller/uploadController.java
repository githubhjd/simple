package com.springboot.simple.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class uploadController {

    private static final Logger logger = LoggerFactory.getLogger(uploadController.class);

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

    //上传图片接口
    @ResponseBody
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public String uploadImg(HttpServletRequest request, @Param("file")MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return "文件為空";
        }
        //獲取本機ip
        try{
            host = InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException e){
            logger.error("get server host Exception e:", e);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());//实际时间
        //本地地址
//        String rootPath = "D:\\IdeaProject\\simple\\src\\main\\resources\\static\\uploadImg\\";

        //原来名称
        String originalFilename = file.getOriginalFilename();
        //新文件名称
        String newFileName = res + originalFilename.substring(originalFilename.lastIndexOf("."));
        //创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
        //新文件
        File newFile = new File(rootPath + sonPath + File.separator + dateDirs + File.separator + newFileName);

        //判断目标文件所在目录是否存在
        if (!newFile.getParentFile().exists()){
            //如果目标文件所在目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);
        //将内存中的数据写入磁盘
        file.transferTo(newFile);
        //完整的url
        String fileUrl = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + newFileName;

        //圖片的訪問路徑
        String PhotoPath = ("http://" + host + ":" + post + imgPath + fileUrl).toString();

        Map<String ,Object> map = new HashMap<String, Object>();
        Map<String ,Object> map2 = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "上传成功");// 提示信息
        map.put("data", map2);
        map2.put("src", PhotoPath);// 图片的url
        map2.put("title", newFileName);// 图片名称，这个会显示在输入框中
        String result = new JSONObject(map).toString();
        return result;
    }

}
