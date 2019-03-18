package com.springboot.simple.controller;

import com.springboot.simple.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class ReplyController {

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private LikeDao likeDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageDao messageDao;

    public static String sbraces1;
    public static String sbraces2;
    public static String sbraces3;
    public static String sbraces4;
    public static String sbraces5;
    public static String sbraces6;
    public static String sbraces7;
    public static String sbraces8;
    public static String sbraces9;
    public static String sbraces10;
    public static String sbraces11;

    //改写href链接（正则表达式）
    public String changeLayuiHref(String str){
        String pattern = "a+\\(+((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+\\)+\\[+((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+\\]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find())
        {
            sbraces1=m.group();
            System.out.print("("+sbraces1.toString()+")"+"\n");
        }
        return sbraces1;

    }

    //获取href链接
    public String changeHref(String str){
        String pattern = "\\(+((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+\\)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find())
        {
            sbraces2=m.group().replace("(","").replace(")","");
            System.out.print("("+sbraces2.toString()+")"+"\n");
        }
        return sbraces2;

    }

    //更换合法链接
    public String LastChangeHref(String str){
        //排除链接为空值
        try {
            String main_spec_layui_href = changeLayuiHref(str.replace("main_spec=",""));
            String main_spec_href = changeHref(str.replace("main_spec=",""));
            String main_spec_a_href = "<a href=\""+main_spec_href+"\">"+main_spec_href+"</a>";
            sbraces3 = str.replace(main_spec_layui_href, main_spec_a_href);
        }catch (Exception e){
            sbraces3 = str;
        }
        return sbraces3;
    }

    //获取face[]
    public String changeAllFace(String str){
        String pattern = "face+\\[+[\\u4e00-\\u9fa5]+\\]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find())
        {
            sbraces4=m.group();
            System.out.print("("+sbraces4.toString()+")"+"\n");
        }
        return sbraces4;
    }

    //获取face[]
    public String changeWordFace(String str){
        String pattern = "face+\\[+[\\u4e00-\\u9fa5]+\\]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find())
        {
            sbraces5=m.group().substring(5,m.group().length()-1);
            System.out.print("("+sbraces5.toString()+")"+"\n");
        }
        return sbraces5;
    }

    //表情管理
    public String changeFace(String str){
        try {
            String str1 = changeAllFace(str);
            String str2 = changeWordFace(str);
            String[] array = {
                    "<img alt=\"[微笑]\" title=\"[微笑]\" src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/5c/huanglianwx_thumb.gif\">",
                    "<img alt=\"[嘻嘻]\" title=\"[嘻嘻]\" src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/0b/tootha_thumb.gif\">",
            };
//            String str3 = "<img alt=\"[微笑]\" title=\"[微笑]\" src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/5c/huanglianwx_thumb.gif\">";
            System.out.println(str2);
            if (str2.equals("微笑")||str2.equals("嘻嘻")){
                sbraces6 = str.replace(str1,array[0]).replace(str1,array[1]);
//            }else if (str2.equals("嘻嘻")){
//                sbraces6 = str.replace(str1,array[1]);
            }
        }catch (Exception e){
            sbraces6 = str;
        }
        return sbraces6;
    }

    //获取图片url
    public String changAllPicUrl(String str){
//        String str = "img[http://172.6.1.243:8100/upload/1547695138522卡卡西_看图王.jpg] ";

        String pattern = "img+\\[+(.*?)+\\]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find())
        {
            sbraces7=m.group();
            System.out.print("("+sbraces7.toString()+")"+"\n");
        }
        return sbraces7;
    }

    //获取图片url
    public String changeWordPicUrl(String str){
//        String str = "img[http://172.6.1.243:8100/upload/1547695138522卡卡西_看图王.jpg] ";

        String pattern = "img+\\[+(.*?)+\\]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find())
        {
            sbraces8=m.group().substring(4,m.group().length()-1);
            System.out.print("("+sbraces8.toString()+")"+"\n");
        }
        return sbraces8;
    }


    public String GetAddressBefore(String str){
//        String str = "img[http://172.6.1.243:8100/upload/1547695138522卡卡西_看图王.jpg] ";

        String pattern = "img+\\[+(.*?)+\\]";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find())
        {
            sbraces10=m.group().substring(4,m.group().length()-1);
            System.out.print("("+sbraces10.toString()+")"+"\n");
        }
        return sbraces10;
    }

    //图片添加
    public String changePicUrl(String str){
        try {
            String str1 = changAllPicUrl(str);
            String str2 = changeWordPicUrl(str);
            String str3 = "<img src=\""+str2+"\">";
            sbraces9 = str.replace(str1,str3);
        }catch (Exception e){
            sbraces9 = str;
        }
        return sbraces9;
    }

    //将String字符串进行Base64解密
    public static String Base64ToString(String text){
        byte[] textByte = text.getBytes();
        byte[] jmtextByte = Base64.getDecoder().decode(textByte);
        String str = new String(jmtextByte);
//        System.out.println(jmtextByte);
        return str;
    }

    //添加回帖
    @RequestMapping(value = "/addReply", method = RequestMethod.POST)
    public String addReply(@RequestBody String str){
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String post_type = URLDecoder.decode(temp[0].replace("post_type=",""));
        String reply_main_spec = changePicUrl(changeFace(LastChangeHref(URLDecoder.decode(temp[1].replace("reply_main_spec=",""))).replace("[pre]","<pre>").replace("[/pre]","</pre>").replace("[hr]","<hr>")));
        //访问者
        String reply_address = URLDecoder.decode(temp[2].replace("address=",""));
        int status = 0;
        int like_num = 0;
        int add_time = (int)(System.currentTimeMillis()/1000);//时间戳
        replyDao.InsertReply(post_type, reply_main_spec, status, like_num, add_time, reply_address);
        //添加消息
        String temp1[] = reply_main_spec.split(" ");
        String str2 = temp1[0];
        System.out.println(temp1[0]);
        String pattern = "@+(.*)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str2);
        if (m.find()){
            sbraces11 = m.group();
            System.out.println(m.group());
        }
        if (str2.equals(sbraces11)){
            messageDao.insertMessage(post_type,reply_address,0,0,1,sbraces11,add_time);
        }else {
            messageDao.insertMessage(post_type,reply_address,0,0,0,sbraces11,add_time);
        }
        return post_type;
    }

    //获取回帖
    @RequestMapping(value = "/getReply", method = RequestMethod.POST)
    public List<Map<String, Object>> getReply(String post_type){
        List<Map<String,Object>> replyList = replyDao.selectByPostType(post_type);
        return replyList;
    }

    //采纳答案
    @RequestMapping(value = "/api/jieda-accept/", method = RequestMethod.POST)
    public Map<String, Object> acceptReply(String id,String post_type,String reply_address64,String access_address,String post_bounty){
        System.out.println(id);
        replyDao.updateStatusReplyById(Integer.valueOf(id));
        userDao.updateUserTotalAdd(Integer.valueOf(post_bounty),reply_address64);
        userDao.updateUserTotalSub(Integer.valueOf(post_bounty),access_address);
        int clear_time = (int)(System.currentTimeMillis()/1000);//时间戳
        postDao.updateStatusOver(clear_time,post_type);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 0);
        return map;
    }

    //点赞
    @RequestMapping(value = "/api/jieda-zan/", method = RequestMethod.POST)
    public Map<String, Object> zanReoply(String id, String reply_address64, String access_address){
        System.out.println(id);
//        replyDao.updateLikeNumReplyById(Integer.valueOf(id));
        Map<String, Object> map = new HashMap<>();
        int add_time = (int)(System.currentTimeMillis()/1000);//时间戳
        int change_time = (int)(System.currentTimeMillis()/1000);//时间戳
        int countLike = likeDao.selectCountByReplyIdAndAddress(Integer.valueOf(id),access_address);//查询是否存在改该用户的点赞记录
        //判断是否点赞记录表中有所记录
        if (countLike == 0){
            //没有则添加
            likeDao .insertLike(Integer.valueOf(id),access_address,1,change_time,add_time);
            replyDao.updateLikeNumReplyByIdAdd(Integer.valueOf(id));
            map.put("status", 0);
            map.put("msg","点赞成功");
        }else if(countLike == 1){
            int Liked = likeDao.selectByReplyIdAndAddress(Integer.valueOf(id),access_address);
            if (Liked == 1){
                replyDao.updateLikeNumReplyByIdSub(Integer.valueOf(id));
                likeDao.updateLiked2(Integer.valueOf(id),access_address);
                map.put("status", 0);
                map.put("msg", "取消点赞");
            }else if (Liked == 2){
                replyDao.updateLikeNumReplyByIdAdd(Integer.valueOf(id));
                likeDao.updateLiked1(Integer.valueOf(id),access_address);
                map.put("status", 0);
                map.put("msg", "已重新点赞");
            }
        }
        return map;
    }

    //回帖榜（测试接口）
    @RequestMapping(value = "/getTop12Reply", method = RequestMethod.GET)
    public List<Map<String, Object>> getTop12Reply(){
         List<Map<String, Object>> top12ReplyList = replyDao.selectCountTop12();
         for (int i = 0;i < top12ReplyList.size();i++){
             String reply_address = Base64ToString(String.valueOf(top12ReplyList.get(i).get("reply_address")));
             Map map = userDao.selectNameGradePhotoByAddress(reply_address);
             top12ReplyList.get(i).put("username",map.get("username"));
             top12ReplyList.get(i).put("profile_photo",map.get("IFNULL(profile_photo,0)"));
             top12ReplyList.get(i).put("reply_address",reply_address);
         }
         return top12ReplyList;
    }

}
