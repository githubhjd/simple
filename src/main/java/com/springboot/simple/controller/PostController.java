package com.springboot.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.simple.dao.PostDao;
import com.springboot.simple.dao.ReplyDao;
import com.springboot.simple.dao.UserDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.springboot.simple.controller.IndexController.StringToBase64;

@RestController
public class PostController {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ReplyDao replyDao;

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
    public static String type;

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

    //接收贴子信息
    @RequestMapping(value = "/postReceive",method = RequestMethod.POST)
    public String postReceive(@RequestBody String str){
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        int add_time = (int)(System.currentTimeMillis()/1000);//时间戳
        String post_type = URLDecoder.decode(temp[1].replace("post_type=",""))+add_time;
        String address = URLDecoder.decode(temp[0].replace("address=",""));
//        System.out.println(address);
        String title = URLDecoder.decode(temp[2].replace("title=",""));
//        String main_spec_layui_href = changeLayuiHref(URLDecoder.decode(temp[3].replace("main_spec=","")));
//        String main_spec_href = changeHref(URLDecoder.decode(temp[3].replace("main_spec=","")));
//        String main_spec_a_href = "<a href=\""+main_spec_href+"\">"+main_spec_href+"</a>";
//        String main_spec = URLDecoder.decode(temp[3].replace("main_spec=","")).replace("[pre]","<pre>").replace("[/pre]","</pre>").replace("[hr]","<hr>");
        String main_spec = changePicUrl(changeFace(LastChangeHref(URLDecoder.decode(temp[3].replace("main_spec=",""))).replace("[pre]","<pre>").replace("[/pre]","</pre>").replace("[hr]","<hr>")));
        String post_bounty= URLDecoder.decode(temp[4].replace("post_bounty=",""));
        int total = userDao.selectTotalByAddress(address);
        if (total >= Integer.valueOf(post_bounty)){
            postDao.insertPost(post_type,title,main_spec,post_bounty,add_time);//插入贴子
            return post_type;
        }else {
            return "0";
        }

    }

    //修改贴子信息
    @RequestMapping(value = "/editPostReceive",method = RequestMethod.POST)
    public String editPostReceive(@RequestBody String str){
        System.out.print(str);
        //对json字符串进行解析，获取相关数据
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        int add_time = (int)(System.currentTimeMillis()/1000);//时间戳
        String post_type = URLDecoder.decode(temp[1].replace("post_type=",""))+add_time;
        String address = URLDecoder.decode(temp[0].replace("address=",""));
//        System.out.println(address);
        String title = URLDecoder.decode(temp[2].replace("title=",""));
//        String main_spec = URLDecoder.decode(temp[3].replace("main_spec=",""));
        String main_spec = changePicUrl(changeFace(LastChangeHref(URLDecoder.decode(temp[3].replace("main_spec=",""))).replace("[pre]","<pre>").replace("[/pre]","</pre>").replace("[hr]","<hr>")));
        String post_bounty= URLDecoder.decode(temp[4].replace("post_bounty=",""));
        String old_post_type = URLDecoder.decode(temp[5].replace("old_post_type=",""));
        int total = userDao.selectTotalByAddress(address);
        if (total >= Integer.valueOf(post_bounty)){
            postDao.updatePost(post_type,title,main_spec,post_bounty,add_time,old_post_type);//插入贴子
            return post_type;
        }else {
            return "0";
        }
    }

    /*
     * 将时间戳转换为时间
     * 时间戳转为日期时必须要long类型，不然会直接1970年
     */

    public static String stampToTime(long stamp) {
        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(stamp*1000);
        time = simpleDateFormat.format(date);
        return time;
    }

    //用户个人贴子信息
    @RequestMapping(value = "/myPostListShow", method = RequestMethod.GET)
    public String myPostListShow(String address, int page, int limit ){

        String result = "";
        String address1 = "%"+address+"%";
        try {
            List<Map<String, Object>> postList = postDao.selectByLikePostType(address1);
            for (int m = 0;m < postList.size();m++){
                String status = String.valueOf(postList.get(m).get("status"));
                if (status.equals("0")){
                    postList.get(m).put("status","未结");
                }else if (status.equals("1")){
                    postList.get(m).put("status","已结");
                }
                String new_time = stampToTime(Long.parseLong(String.valueOf(postList.get(m).get("add_time"))));
                postList.get(m).put("add_time", new_time);
                String click_num = String.valueOf(postList.get(m).get("click_num"));
                String count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(postList.get(m).get("post_type"))));
                String num = click_num+"阅/"+count+"答";
                postList.get(m).put("num", num);
            }
            Map<String ,Object> map = new HashMap<String, Object>();
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            int myPage = (page-1)*10;
            if ((postList.size()-(limit+myPage)) > 10){
                for (int i = myPage; i < (limit+myPage);i++){
                    resultList.add(postList.get(i));
                }
            }else {
                for (int i = myPage; i < postList.size();i++){
                    resultList.add(postList.get(i));
                }
            }
//            System.out.println(postList.get(10));
            map.put("code", 0);// 0表示成功，1表示失败
            map.put("msg", "200");// 提示信息
            map.put("count", postList.size());
            map.put("data", resultList);
            result = new JSONObject(map).toString();
        }catch (Exception e){

        }finally {
            return result;
        }
    }

    /* 根据属性名获取属性值
     * */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {

            return null;
        }
    }

    //将String字符串进行Base64解密
    public static String Base64ToString(String text){
        byte[] textByte = text.getBytes();
        byte[] jmtextByte = Base64.getDecoder().decode(textByte);
        String str = new String(jmtextByte);
//        System.out.println(jmtextByte);
        return str;
    }

    //置顶帖子信息
    @RequestMapping(value = "/getTopPost", method = RequestMethod.POST)
    public String getTopPost(){
        List<Map<String, Object>> top4ReplyList = replyDao.selectCountTop4();
        for (int i = 0;i < top4ReplyList.size();i++){
            String reply_post_type = String.valueOf(top4ReplyList.get(i).get("post_type"));
            String str = reply_post_type.substring(0,3);
            Map<String, Object> map = postDao.selectByPostType(reply_post_type);
            String new_time = stampToTime(Long.parseLong(String.valueOf(map.get("add_time"))));
            map.put("add_time", new_time);
            switch (str){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            map.put("type",type);
            String address1 = reply_post_type.substring(3,reply_post_type.length()-10);
            String BaseAddress = Base64ToString(address1);
            Map map1 = userDao.selectNameGradePhotoByAddress(BaseAddress);
            map.put("username",map1.get("username"));
            map.put("grade_num",map1.get("grade_num"));
            map.put("profile_photo",map1.get("IFNULL(profile_photo,0)"));
            map.put("address",BaseAddress);
            top4ReplyList.get(i).putAll(map);
        }
        Map map2 = new HashMap();
        map2.put("code", 0);// 0表示成功，1表示失败
        map2.put("msg", "200");// 提示信息
        map2.put("count", top4ReplyList.size());
        map2.put("data", top4ReplyList);
        String result = new JSONObject(map2).toString();
        return result;
    }

    //所有的贴子信息
    @RequestMapping(value = "/getAllPost", method = RequestMethod.POST)
    public String getAllPost(int pageIndex, int pageSize){
        List<Map<String, Object>> allPostList = postDao.selectAllPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
//        Post post = new Post();
//        System.out.println("------------------------------");
//        System.out.println(post.getPost_type());
//        System.out.println("------------------------------");
        for (int i = 0; i < allPostList.size(); i++){
//            Map address = allPostList.get(i);
            System.out.println("------------------------------");
//            Object str3 = getFieldValueByName("post_type",allPostList);
//            String str = String.valueOf(str3);
            String str = String.valueOf(allPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
//            System.out.println(type);
            allPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(allPostList.get(i).get("add_time"))));
            allPostList.get(i).put("add_time",newTime);
            //遍历User的Map，逐个添加进allPostList
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
//                Map map1 = new HashMap();
//                map1.put(entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo"),entry.getValue());
//                System.out.println(map1);
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                allPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(allPostList.get(i).get("post_type"))));
            allPostList.get(i).put("reply_count", reply_count);
        }
        int count = allPostList.size()/pageSize;
        int yu = allPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < allPostList.size();i++){
                resultList.add(allPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(allPostList.get(i));
            }
        }
        //AllPost列表获取访问者address和主页面address
        for (int i = 0;i < resultList.size();i++){
            String post_type1 = String.valueOf(resultList.get(i).get("post_type"));
            String address1 = Base64ToString(post_type1.substring(3,post_type1.length()-10));
            String home_key1 = StringToBase64(address1+'&'+resultList.get(i).get("address"));
            resultList.get(i).put("home_key",home_key1);
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", allPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
//        return allPostList;
    }

    //所有的未结贴子信息
    @RequestMapping(value = "/getAllOutStandingPost", method = RequestMethod.POST)
    public String getAllOutStandingPost(int pageIndex, int pageSize){
        List<Map<String, Object>> allOutStandingPostList = postDao.selectAllOutStandingPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
//        Post post = new Post();
//        System.out.println("------------------------------");
//        System.out.println(post.getPost_type());
//        System.out.println("------------------------------");
        for (int i = 0; i < allOutStandingPostList.size(); i++){
//            Map address = allPostList.get(i);
            System.out.println("------------------------------");
//            Object str3 = getFieldValueByName("post_type",allPostList);
//            String str = String.valueOf(str3);
            String str = String.valueOf(allOutStandingPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
//            System.out.println(type);
            allOutStandingPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(allOutStandingPostList.get(i).get("add_time"))));
            allOutStandingPostList.get(i).put("add_time",newTime);
            //遍历User的Map，逐个添加进allPostList
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
//                Map map1 = new HashMap();
//                map1.put(entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo"),entry.getValue());
//                System.out.println(map1);
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                allOutStandingPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(allOutStandingPostList.get(i).get("post_type"))));
            allOutStandingPostList.get(i).put("reply_count", reply_count);
        }
        int count = allOutStandingPostList.size()/pageSize;
        int yu = allOutStandingPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < allOutStandingPostList.size();i++){
                resultList.add(allOutStandingPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(allOutStandingPostList.get(i));
            }
        }
        //AllPost列表获取访问者address和主页面address
        for (int i = 0;i < resultList.size();i++){
            String post_type1 = String.valueOf(resultList.get(i).get("post_type"));
            String address1 = Base64ToString(post_type1.substring(3,post_type1.length()-10));
            String home_key1 = StringToBase64(address1+'&'+resultList.get(i).get("address"));
            resultList.get(i).put("home_key",home_key1);
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", allOutStandingPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
//        return allPostList;
    }

    //所有的已结贴子信息
    @RequestMapping(value = "/getAllOverPost", method = RequestMethod.POST)
    public String getAllOverPost(int pageIndex, int pageSize){
        List<Map<String, Object>> allOverPostList = postDao.selectAllOverPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
//        Post post = new Post();
//        System.out.println("------------------------------");
//        System.out.println(post.getPost_type());
//        System.out.println("------------------------------");
        for (int i = 0; i < allOverPostList.size(); i++){
//            Map address = allPostList.get(i);
            System.out.println("------------------------------");
//            Object str3 = getFieldValueByName("post_type",allPostList);
//            String str = String.valueOf(str3);
            String str = String.valueOf(allOverPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
//            System.out.println(type);
            allOverPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(allOverPostList.get(i).get("add_time"))));
            allOverPostList.get(i).put("add_time",newTime);
            //遍历User的Map，逐个添加进allPostList
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
//                Map map1 = new HashMap();
//                map1.put(entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo"),entry.getValue());
//                System.out.println(map1);
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                allOverPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(allOverPostList.get(i).get("post_type"))));
            allOverPostList.get(i).put("reply_count", reply_count);
        }
        int count = allOverPostList.size()/pageSize;
        int yu = allOverPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < allOverPostList.size();i++){
                resultList.add(allOverPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(allOverPostList.get(i));
            }
        }
        //AllPost列表获取访问者address和主页面address
        for (int i = 0;i < resultList.size();i++){
            String post_type1 = String.valueOf(resultList.get(i).get("post_type"));
            String address1 = Base64ToString(post_type1.substring(3,post_type1.length()-10));
            String home_key1 = StringToBase64(address1+'&'+resultList.get(i).get("address"));
            resultList.get(i).put("home_key",home_key1);
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", allOverPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
//        return allPostList;
    }

    @RequestMapping(value = "/getQuizPostList", method = RequestMethod.POST)
    public String getQuizPostList(int pageIndex, int pageSize){
        List<Map<String, Object>> quizPostList = postDao.selectQuizPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < quizPostList.size(); i++){
            String str = String.valueOf(quizPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            quizPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(quizPostList.get(i).get("add_time"))));
            quizPostList.get(i).put("add_time",newTime);
            //遍历allPostList中的Map，逐个添加user信息
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                quizPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(quizPostList.get(i).get("post_type"))));
            quizPostList.get(i).put("reply_count", reply_count);
        }
        int count = quizPostList.size()/pageSize;
        int yu = quizPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < quizPostList.size();i++){
                resultList.add(quizPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(quizPostList.get(i));
            }
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", quizPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
    }

    //未结提问贴子信息
    @RequestMapping(value = "/getOutStandingQuizPost", method = RequestMethod.POST)
    public String getOutStandingQuizPost(int pageIndex, int pageSize){
        List<Map<String, Object>> allOutStandingQuizPostList = postDao.selectOutStandingQuizPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
//        Post post = new Post();
//        System.out.println("------------------------------");
//        System.out.println(post.getPost_type());
//        System.out.println("------------------------------");
        for (int i = 0; i < allOutStandingQuizPostList.size(); i++){
//            Map address = allPostList.get(i);
            System.out.println("------------------------------");
//            Object str3 = getFieldValueByName("post_type",allPostList);
//            String str = String.valueOf(str3);
            String str = String.valueOf(allOutStandingQuizPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
//            System.out.println(type);
            allOutStandingQuizPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(allOutStandingQuizPostList.get(i).get("add_time"))));
            allOutStandingQuizPostList.get(i).put("add_time",newTime);
            //遍历User的Map，逐个添加进allPostList
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
//                Map map1 = new HashMap();
//                map1.put(entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo"),entry.getValue());
//                System.out.println(map1);
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                allOutStandingQuizPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(allOutStandingQuizPostList.get(i).get("post_type"))));
            allOutStandingQuizPostList.get(i).put("reply_count", reply_count);
        }
        int count = allOutStandingQuizPostList.size()/pageSize;
        int yu = allOutStandingQuizPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < allOutStandingQuizPostList.size();i++){
                resultList.add(allOutStandingQuizPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(allOutStandingQuizPostList.get(i));
            }
        }
        //AllPost列表获取访问者address和主页面address
        for (int i = 0;i < resultList.size();i++){
            String post_type1 = String.valueOf(resultList.get(i).get("post_type"));
            String address1 = Base64ToString(post_type1.substring(3,post_type1.length()-10));
            String home_key1 = StringToBase64(address1+'&'+resultList.get(i).get("address"));
            resultList.get(i).put("home_key",home_key1);
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", allOutStandingQuizPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
//        return allPostList;
    }

    //已结提问帖子信息
    @RequestMapping(value = "/getOverQuizPost", method = RequestMethod.POST)
    public String getOverQuizPost(int pageIndex, int pageSize){
        List<Map<String, Object>> quizOverPostList = postDao.selectOverQuizPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < quizOverPostList.size(); i++){
            String str = String.valueOf(quizOverPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            quizOverPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(quizOverPostList.get(i).get("add_time"))));
            quizOverPostList.get(i).put("add_time",newTime);
            //遍历allPostList中的Map，逐个添加user信息
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                quizOverPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(quizOverPostList.get(i).get("post_type"))));
            quizOverPostList.get(i).put("reply_count", reply_count);
        }
        int count = quizOverPostList.size()/pageSize;
        int yu = quizOverPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < quizOverPostList.size();i++){
                resultList.add(quizOverPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(quizOverPostList.get(i));
            }
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", quizOverPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
    }

    @RequestMapping(value = "/getSharePostList", method = RequestMethod.POST)
    public String getSharePostList(int pageIndex, int pageSize){
        List<Map<String, Object>> sharePostList = postDao.selectSharePost();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < sharePostList.size(); i++){
            String str = String.valueOf(sharePostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            sharePostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(sharePostList.get(i).get("add_time"))));
            sharePostList.get(i).put("add_time",newTime);
            //遍历allPostList中的Map，逐个添加user信息
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                sharePostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(sharePostList.get(i).get("post_type"))));
            sharePostList.get(i).put("reply_count", reply_count);
        }
        int count = sharePostList.size()/pageSize;
        int yu = sharePostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < sharePostList.size();i++){
                resultList.add(sharePostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(sharePostList.get(i));
            }
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", sharePostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
    }

    //未结讨论贴子信息
    @RequestMapping(value = "/getOutStandingDiscussPost", method = RequestMethod.POST)
    public String getOutStandingDiscussPost(int pageIndex, int pageSize){
        List<Map<String, Object>> OutStandingDiscussPostList = postDao.selectOutStandingDiscussPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
//        Post post = new Post();
//        System.out.println("------------------------------");
//        System.out.println(post.getPost_type());
//        System.out.println("------------------------------");
        for (int i = 0; i < OutStandingDiscussPostList.size(); i++){
//            Map address = allPostList.get(i);
            System.out.println("------------------------------");
//            Object str3 = getFieldValueByName("post_type",allPostList);
//            String str = String.valueOf(str3);
            String str = String.valueOf(OutStandingDiscussPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
//            System.out.println(type);
            OutStandingDiscussPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(OutStandingDiscussPostList.get(i).get("add_time"))));
            OutStandingDiscussPostList.get(i).put("add_time",newTime);
            //遍历User的Map，逐个添加进allPostList
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
//                Map map1 = new HashMap();
//                map1.put(entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo"),entry.getValue());
//                System.out.println(map1);
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                OutStandingDiscussPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(OutStandingDiscussPostList.get(i).get("post_type"))));
            OutStandingDiscussPostList.get(i).put("reply_count", reply_count);
        }
        int count = OutStandingDiscussPostList.size()/pageSize;
        int yu = OutStandingDiscussPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < OutStandingDiscussPostList.size();i++){
                resultList.add(OutStandingDiscussPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(OutStandingDiscussPostList.get(i));
            }
        }
        //AllPost列表获取访问者address和主页面address
        for (int i = 0;i < resultList.size();i++){
            String post_type1 = String.valueOf(resultList.get(i).get("post_type"));
            String address1 = Base64ToString(post_type1.substring(3,post_type1.length()-10));
            String home_key1 = StringToBase64(address1+'&'+resultList.get(i).get("address"));
            resultList.get(i).put("home_key",home_key1);
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", OutStandingDiscussPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
//        return allPostList;
    }

    //已结讨论帖子信息
    @RequestMapping(value = "/getOverDiscussPost", method = RequestMethod.POST)
    public String getOverDiscussPost(int pageIndex, int pageSize){
        List<Map<String, Object>> discussOverPostList = postDao.selectOverDiscussPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < discussOverPostList.size(); i++){
            String str = String.valueOf(discussOverPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            discussOverPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(discussOverPostList.get(i).get("add_time"))));
            discussOverPostList.get(i).put("add_time",newTime);
            //遍历allPostList中的Map，逐个添加user信息
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                discussOverPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(discussOverPostList.get(i).get("post_type"))));
            discussOverPostList.get(i).put("reply_count", reply_count);
        }
        int count = discussOverPostList.size()/pageSize;
        int yu = discussOverPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < discussOverPostList.size();i++){
                resultList.add(discussOverPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(discussOverPostList.get(i));
            }
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", discussOverPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
    }

    @RequestMapping(value = "/getDiscussPostList", method = RequestMethod.POST)
    public String getDiscussPostList(int pageIndex, int pageSize){
        List<Map<String, Object>> discussPostList = postDao.selectDiscussPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < discussPostList.size(); i++){
            String str = String.valueOf(discussPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            discussPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(discussPostList.get(i).get("add_time"))));
            discussPostList.get(i).put("add_time",newTime);
            //遍历allPostList中的Map，逐个添加user信息
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                discussPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(discussPostList.get(i).get("post_type"))));
            discussPostList.get(i).put("reply_count", reply_count);
        }
        int count = discussPostList.size()/pageSize;
        int yu = discussPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < discussPostList.size();i++){
                resultList.add(discussPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(discussPostList.get(i));
            }
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", discussPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
    }

    //未结分享贴子信息
    @RequestMapping(value = "/getOutStandingSharePost", method = RequestMethod.POST)
    public String getOutStandingSharePost(int pageIndex, int pageSize){
        List<Map<String, Object>> OutStandingSharePostList = postDao.selectOutStandingSharePost();
        List<Map<String, Object>> resultList = new ArrayList<>();
//        Post post = new Post();
//        System.out.println("------------------------------");
//        System.out.println(post.getPost_type());
//        System.out.println("------------------------------");
        for (int i = 0; i < OutStandingSharePostList.size(); i++){
//            Map address = allPostList.get(i);
            System.out.println("------------------------------");
//            Object str3 = getFieldValueByName("post_type",allPostList);
//            String str = String.valueOf(str3);
            String str = String.valueOf(OutStandingSharePostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
//            System.out.println(type);
            OutStandingSharePostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(OutStandingSharePostList.get(i).get("add_time"))));
            OutStandingSharePostList.get(i).put("add_time",newTime);
            //遍历User的Map，逐个添加进allPostList
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
//                Map map1 = new HashMap();
//                map1.put(entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo"),entry.getValue());
//                System.out.println(map1);
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                OutStandingSharePostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(OutStandingSharePostList.get(i).get("post_type"))));
            OutStandingSharePostList.get(i).put("reply_count", reply_count);
        }
        int count = OutStandingSharePostList.size()/pageSize;
        int yu = OutStandingSharePostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < OutStandingSharePostList.size();i++){
                resultList.add(OutStandingSharePostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(OutStandingSharePostList.get(i));
            }
        }
        //AllPost列表获取访问者address和主页面address
        for (int i = 0;i < resultList.size();i++){
            String post_type1 = String.valueOf(resultList.get(i).get("post_type"));
            String address1 = Base64ToString(post_type1.substring(3,post_type1.length()-10));
            String home_key1 = StringToBase64(address1+'&'+resultList.get(i).get("address"));
            resultList.get(i).put("home_key",home_key1);
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", OutStandingSharePostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
//        return allPostList;
    }

    //已结分享帖子信息
    @RequestMapping(value = "/getOverSharePost", method = RequestMethod.POST)
    public String getOverSharePost(int pageIndex, int pageSize){
        List<Map<String, Object>> shareOverPostList = postDao.selectOverSharePost();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < shareOverPostList.size(); i++){
            String str = String.valueOf(shareOverPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            shareOverPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(shareOverPostList.get(i).get("add_time"))));
            shareOverPostList.get(i).put("add_time",newTime);
            //遍历allPostList中的Map，逐个添加user信息
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                shareOverPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(shareOverPostList.get(i).get("post_type"))));
            shareOverPostList.get(i).put("reply_count", reply_count);
        }
        int count = shareOverPostList.size()/pageSize;
        int yu = shareOverPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < shareOverPostList.size();i++){
                resultList.add(shareOverPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(shareOverPostList.get(i));
            }
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", shareOverPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
    }

    @RequestMapping(value = "/getSuggestPostList", method = RequestMethod.POST)
    public String getSuggestPostList(int pageIndex, int pageSize){
        List<Map<String, Object>> suggestPostList = postDao.selectSuggestPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < suggestPostList.size(); i++){
            String str = String.valueOf(suggestPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            suggestPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(suggestPostList.get(i).get("add_time"))));
            suggestPostList.get(i).put("add_time",newTime);
            //遍历allPostList中的Map，逐个添加user信息
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                suggestPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(suggestPostList.get(i).get("post_type"))));
            suggestPostList.get(i).put("reply_count", reply_count);
        }
        int count = suggestPostList.size()/pageSize;
        int yu = suggestPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < suggestPostList.size();i++){
                resultList.add(suggestPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(suggestPostList.get(i));
            }
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", suggestPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
    }

    @RequestMapping(value = "/getNewsPostList", method = RequestMethod.POST)
    public String getNewsPostList(int pageIndex, int pageSize){
        List<Map<String, Object>> newsPostList = postDao.selectNewsPost();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < newsPostList.size(); i++){
            String str = String.valueOf(newsPostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            newsPostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(newsPostList.get(i).get("add_time"))));
            newsPostList.get(i).put("add_time",newTime);
            //遍历allPostList中的Map，逐个添加user信息
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                newsPostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(newsPostList.get(i).get("post_type"))));
            newsPostList.get(i).put("reply_count", reply_count);
        }
        int count = newsPostList.size()/pageSize;
        int yu = newsPostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < newsPostList.size();i++){
                resultList.add(newsPostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(newsPostList.get(i));
            }
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", newsPostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
    }

    @RequestMapping(value = "/getNoticePostList", method = RequestMethod.POST)
    public String getNoticePostList(int pageIndex, int pageSize){
        List<Map<String, Object>> NoticePostList = postDao.selectNoticePost();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (int i = 0; i < NoticePostList.size(); i++){
            String str = String.valueOf(NoticePostList.get(i).get("post_type"));
            String str1 = str.substring(3, str.length()-10);
            String str2 = Base64ToString(str1);
            String str3 = str.substring(0,3);
            switch (str3){
                case "000":
                    type = "提问";
                    break;
                case "099":
                    type = "分享";
                    break;
                case "100":
                    type = "讨论";
                    break;
                case "101":
                    type = "建议";
                    break;
                case "168":
                    type = "公告";
                    break;
                case "169":
                    type = "动态";
                    break;
                default:
                    System.out.println("default");
            }
            NoticePostList.get(i).put("type",type);
            System.out.println(str2);
            System.out.println("------------------------------");
            Map map = userDao.selectNameGradePhotoByAddress(str2);
            String newTime = stampToTime(Long.parseLong(String.valueOf(NoticePostList.get(i).get("add_time"))));
            NoticePostList.get(i).put("add_time",newTime);
            //遍历allPostList中的Map，逐个添加user信息
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
//            Map map1 = new HashMap();
            while (it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                System.out.println("key= " + entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo") + " and value= " + entry.getValue());
                String key = entry.getKey().replace("IFNULL(profile_photo,0)","profile_photo");
                Object value = entry.getValue();
                NoticePostList.get(i).put(key,value);
            }
            String reply_count = String.valueOf(replyDao.selectCountReplyByPostType(String.valueOf(NoticePostList.get(i).get("post_type"))));
            NoticePostList.get(i).put("reply_count", reply_count);
        }
        int count = NoticePostList.size()/pageSize;
        int yu = NoticePostList.size()%pageSize;
        if (yu > 0){
            count = count + 1;
        }
        if (pageIndex == count){
            for (int i = (pageIndex-1)*10;i < NoticePostList.size();i++){
                resultList.add(NoticePostList.get(i));
            }
        }else {
            for (int i = (pageIndex-1)*10;i < pageIndex*10;i++){
                resultList.add(NoticePostList.get(i));
            }
        }
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("code", 0);// 0表示成功，1表示失败
        map.put("msg", "200");// 提示信息
        map.put("count", NoticePostList.size());
        map.put("data", resultList);
        String result = new JSONObject(map).toString();
        return result;
    }

//    @RequestMapping(value = "/getJson")
//    public void getJson(){
//        Map map = new HashMap();
//        Map map1 = new HashMap();
//        map1.put("hscode","999999999");
//        map.put("code","1");
//        map.put("msg","200");
//        map.put("data",map1);s
//        String result = new JSONObject(map).toString();
//        System.out.println(result);
//    }

    //热议榜（测试接口）
    @RequestMapping(value = "/getTop10Post_type",method = RequestMethod.GET)
    public List<Map<String, Object>> getTop10Post_typeList(){
        List<Map<String, Object>> top10Post_typeList = replyDao.selectCountTop10();
        for (int i = 0;i < top10Post_typeList.size();i++){
            String top10Post_type = String.valueOf(top10Post_typeList.get(i).get("post_type"));
            String top10Title = postDao.selectTitleByPostType(top10Post_type);
            top10Post_typeList.get(i).put("title",top10Title);
        }
        return top10Post_typeList;
    }



}