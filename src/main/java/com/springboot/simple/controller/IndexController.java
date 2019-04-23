package com.springboot.simple.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.simple.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

//@RestController(value = "/")
@Controller
public class IndexController {

    //将String字符串进行Base64解密
    public static String Base64ToString(String text){
        byte[] textByte = Base64.getDecoder().decode(text);
        String jmtextByte = new String(textByte);
//        System.out.println(jmtextByte);
        return jmtextByte;
    }

    //将String字符串进行Base64加密
    public static String StringToBase64(String text){
        byte[] textByte = text.getBytes();
        String jmtextByte = Base64.getEncoder().encodeToString(textByte);
//        System.out.println(jmtextByte);
        return jmtextByte;
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

//    @GetMapping(value = "/login")
//    public String login(Model model, @RequestParam(value = "error", required = false) String error){
//        if (error != null){
//            model.addAttribute("error","用户名或密码错误");
//        }
//        return "/page/index";
//    }
    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private ReplyDao replyDao;

    @Autowired
    private ColDao colDao;

    @Autowired
    private LikeDao likeDao;

    @Autowired
    private MessageDao messageDao;

    //会员用户中心
    @RequestMapping(value = "/html/user/index")
    public String htmlUserIndex(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        modelMap.put("username", map.get("username"));
        modelMap.put("address", map.get("address"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("total",map.get("total"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("my_home_key", StringToBase64(address+"&"+address));
        return "/html/user/index";
    }

    //会员的贴子
    @RequestMapping(value = "/html/user/post")
    public String htmlUserPost(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        //获取该用户的post详情
        String address1 = "%"+StringToBase64(address)+"%";
        List<Map<String, Object>> postList = postDao.selectByLikePostType(address1);
        //获取该用户的col详情
        List<Map<String, Object>> colList = colDao.selectColByAddress(address);
        modelMap.put("username", map.get("username"));
        modelMap.put("address", map.get("address"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("my_home_key", StringToBase64(address+"&"+address));
        modelMap.put("postList",postList);
        modelMap.put("colList",colList);
        return "/html/user/post";
    }

    //会员基本设置
    @RequestMapping(value = "/html/user/set")
    public String htmlUserSet(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("city", map.get("city"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("my_home_key", StringToBase64(address+"&"+address));
        return "/html/user/set";
    }

//    会员发帖
    @RequestMapping(value = "/html/jie/add")
    public String htmlJieAdd(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        return "/html/jie/add";
    }

    //   会员登录主页
    @RequestMapping(value = "/html/indexSuc")
    public String htmlIndexSuc(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        List<Map<String, Object>> resultList = postDao.selectAllPost();
        List<Map<String, Object>> allOverPostList = postDao.selectAllOverPost();
        List<Map<String, Object>> allOutStandingPostList = postDao.selectAllOutStandingPost();
        List<Map<String, Object>> top12ReplyList = replyDao.selectCountTop12();
        for (int i = 0;i < top12ReplyList.size();i++){
            String reply_address = Base64ToString(String.valueOf(top12ReplyList.get(i).get("reply_address")));
            Map map1 = userDao.selectNameGradePhotoByAddress(reply_address);
            String home_key12 = StringToBase64(reply_address+'&'+address);
            top12ReplyList.get(i).put("username",map1.get("username"));
            top12ReplyList.get(i).put("profile_photo",map1.get("IFNULL(profile_photo,0)"));
            top12ReplyList.get(i).put("reply_address",reply_address);
            top12ReplyList.get(i).put("home_key12",home_key12);
            System.out.println(top12ReplyList.get(i).get("home_key12"));
        }
        List<Map<String, Object>> top10Post_typeList = replyDao.selectCountTop10();
        for (int i = 0;i < top10Post_typeList.size();i++){
            String top10Post_type = String.valueOf(top10Post_typeList.get(i).get("post_type"));
            String top10Title = postDao.selectTitleByPostType(top10Post_type);
            top10Post_typeList.get(i).put("title",top10Title);
            String address10 = top10Post_type.substring(3,top10Post_type.length()-10);
            String home_key10 = StringToBase64(address10+'&'+address);
            top10Post_typeList.get(i).put("home_key10",home_key10);
            top10Post_typeList.get(i).put("access_address",address);
        }
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("my_home_key", StringToBase64(address+"&"+address));
        modelMap.put("resultList", resultList);
        modelMap.put("allOverPostList", allOverPostList);
        modelMap.put("allOutStandingPostList", allOutStandingPostList);
        modelMap.put("top12ReplyList", top12ReplyList);
        modelMap.put("top10Post_typeList", top10Post_typeList);
        return "/html/indexSuc";
    }

    //会员提问界面
    @RequestMapping(value = "/html/quiz")
    public String htmlQuiz(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        List<Map<String, Object>> resultList = postDao.selectQuizPost();
        List<Map<String, Object>> top12ReplyList = replyDao.selectCountTop12();
        List<Map<String, Object>> quizOverPostList = postDao.selectOverQuizPost();
        List<Map<String, Object>> quizOutStandingPostList = postDao.selectOutStandingQuizPost();
        for (int i = 0;i < top12ReplyList.size();i++){
            String reply_address = Base64ToString(String.valueOf(top12ReplyList.get(i).get("reply_address")));
            Map map1 = userDao.selectNameGradePhotoByAddress(reply_address);
            String home_key12 = StringToBase64(reply_address+'&'+address);
            top12ReplyList.get(i).put("username",map1.get("username"));
            top12ReplyList.get(i).put("profile_photo",map1.get("IFNULL(profile_photo,0)"));
            top12ReplyList.get(i).put("reply_address",reply_address);
            top12ReplyList.get(i).put("home_key12",home_key12);
            System.out.println(top12ReplyList.get(i).get("home_key12"));
        }
        List<Map<String, Object>> top10Post_typeList = replyDao.selectCountTop10();
        for (int i = 0;i < top10Post_typeList.size();i++){
            String top10Post_type = String.valueOf(top10Post_typeList.get(i).get("post_type"));
            String top10Title = postDao.selectTitleByPostType(top10Post_type);
            top10Post_typeList.get(i).put("title",top10Title);
            String address10 = top10Post_type.substring(3,top10Post_type.length()-10);
            String home_key10 = StringToBase64(address10+'&'+address);
            top10Post_typeList.get(i).put("home_key10",home_key10);
            top10Post_typeList.get(i).put("access_address",address);
        }
        modelMap.put("top12ReplyList", top12ReplyList);
        modelMap.put("top10Post_typeList", top10Post_typeList);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("resultList", resultList);
        modelMap.put("quizOverPostList", quizOverPostList);
        modelMap.put("quizOutStandingPostList", quizOutStandingPostList);
        return "/html/quiz";
    }

    //会员分享页面
    @RequestMapping(value = "/html/share")
    public String htmlShare(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        List<Map<String, Object>> resultList = postDao.selectSharePost();
        List<Map<String, Object>> top12ReplyList = replyDao.selectCountTop12();
        List<Map<String, Object>> shareOverPostList = postDao.selectOverSharePost();
        List<Map<String, Object>> shareOutStandingPostList = postDao.selectOutStandingSharePost();
        for (int i = 0;i < top12ReplyList.size();i++){
            String reply_address = Base64ToString(String.valueOf(top12ReplyList.get(i).get("reply_address")));
            Map map1 = userDao.selectNameGradePhotoByAddress(reply_address);
            String home_key12 = StringToBase64(reply_address+'&'+address);
            top12ReplyList.get(i).put("username",map1.get("username"));
            top12ReplyList.get(i).put("profile_photo",map1.get("IFNULL(profile_photo,0)"));
            top12ReplyList.get(i).put("reply_address",reply_address);
            top12ReplyList.get(i).put("home_key12",home_key12);
            System.out.println(top12ReplyList.get(i).get("home_key12"));
        }
        List<Map<String, Object>> top10Post_typeList = replyDao.selectCountTop10();
        for (int i = 0;i < top10Post_typeList.size();i++){
            String top10Post_type = String.valueOf(top10Post_typeList.get(i).get("post_type"));
            String top10Title = postDao.selectTitleByPostType(top10Post_type);
            top10Post_typeList.get(i).put("title",top10Title);
            String address10 = top10Post_type.substring(3,top10Post_type.length()-10);
            String home_key10 = StringToBase64(address10+'&'+address);
            top10Post_typeList.get(i).put("home_key10",home_key10);
            top10Post_typeList.get(i).put("access_address",address);
        }
        modelMap.put("top12ReplyList", top12ReplyList);
        modelMap.put("top10Post_typeList", top10Post_typeList);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("shareOverPostList", shareOverPostList);
        modelMap.put("shareOutStandingPostList", shareOutStandingPostList);
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("resultList", resultList);
        return "/html/share";
    }

    //会员讨论界面
    @RequestMapping(value = "/html/discuss")
    public String htmlDiscuss(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        List<Map<String, Object>> resultList = postDao.selectDiscussPost();
        List<Map<String, Object>> top12ReplyList = replyDao.selectCountTop12();
        List<Map<String, Object>> discussOverPostList = postDao.selectOverDiscussPost();
        List<Map<String, Object>> discussOutStandingPostList = postDao.selectOutStandingDiscussPost();
        for (int i = 0;i < top12ReplyList.size();i++){
            String reply_address = Base64ToString(String.valueOf(top12ReplyList.get(i).get("reply_address")));
            Map map1 = userDao.selectNameGradePhotoByAddress(reply_address);
            String home_key12 = StringToBase64(reply_address+'&'+address);
            top12ReplyList.get(i).put("username",map1.get("username"));
            top12ReplyList.get(i).put("profile_photo",map1.get("IFNULL(profile_photo,0)"));
            top12ReplyList.get(i).put("reply_address",reply_address);
            top12ReplyList.get(i).put("home_key12",home_key12);
            System.out.println(top12ReplyList.get(i).get("home_key12"));
        }
        List<Map<String, Object>> top10Post_typeList = replyDao.selectCountTop10();
        for (int i = 0;i < top10Post_typeList.size();i++){
            String top10Post_type = String.valueOf(top10Post_typeList.get(i).get("post_type"));
            String top10Title = postDao.selectTitleByPostType(top10Post_type);
            top10Post_typeList.get(i).put("title",top10Title);
            String address10 = top10Post_type.substring(3,top10Post_type.length()-10);
            String home_key10 = StringToBase64(address10+'&'+address);
            top10Post_typeList.get(i).put("home_key10",home_key10);
            top10Post_typeList.get(i).put("access_address",address);
        }
        modelMap.put("discussOverPostList", discussOverPostList);
        modelMap.put("discussOutStandingPostList", discussOutStandingPostList);
        modelMap.put("top12ReplyList", top12ReplyList);
        modelMap.put("top10Post_typeList", top10Post_typeList);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("resultList", resultList);
        return "/html/discuss";
    }

    //会员建议页面
    @RequestMapping(value = "/html/suggest")
    public String htmlSuggest(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        List<Map<String, Object>> resultList = postDao.selectSuggestPost();
        List<Map<String, Object>> top12ReplyList = replyDao.selectCountTop12();
        for (int i = 0;i < top12ReplyList.size();i++){
            String reply_address = Base64ToString(String.valueOf(top12ReplyList.get(i).get("reply_address")));
            Map map1 = userDao.selectNameGradePhotoByAddress(reply_address);
            String home_key12 = StringToBase64(reply_address+'&'+address);
            top12ReplyList.get(i).put("username",map1.get("username"));
            top12ReplyList.get(i).put("profile_photo",map1.get("IFNULL(profile_photo,0)"));
            top12ReplyList.get(i).put("reply_address",reply_address);
            top12ReplyList.get(i).put("home_key12",home_key12);
            System.out.println(top12ReplyList.get(i).get("home_key12"));
        }
        List<Map<String, Object>> top10Post_typeList = replyDao.selectCountTop10();
        for (int i = 0;i < top10Post_typeList.size();i++){
            String top10Post_type = String.valueOf(top10Post_typeList.get(i).get("post_type"));
            String top10Title = postDao.selectTitleByPostType(top10Post_type);
            top10Post_typeList.get(i).put("title",top10Title);
            String address10 = top10Post_type.substring(3,top10Post_type.length()-10);
            String home_key10 = StringToBase64(address10+'&'+address);
            top10Post_typeList.get(i).put("home_key10",home_key10);
            top10Post_typeList.get(i).put("access_address",address);
        }
        modelMap.put("top12ReplyList", top12ReplyList);
        modelMap.put("top10Post_typeList", top10Post_typeList);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("resultList", resultList);
        return "/html/suggest";
    }

    //会员公告页面
    @RequestMapping(value = "/html/news")
    public String htmlNews(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        List<Map<String, Object>> resultList = postDao.selectNewsPost();
        List<Map<String, Object>> top12ReplyList = replyDao.selectCountTop12();
        for (int i = 0;i < top12ReplyList.size();i++){
            String reply_address = Base64ToString(String.valueOf(top12ReplyList.get(i).get("reply_address")));
            Map map1 = userDao.selectNameGradePhotoByAddress(reply_address);
            String home_key12 = StringToBase64(reply_address+'&'+address);
            top12ReplyList.get(i).put("username",map1.get("username"));
            top12ReplyList.get(i).put("profile_photo",map1.get("IFNULL(profile_photo,0)"));
            top12ReplyList.get(i).put("reply_address",reply_address);
            top12ReplyList.get(i).put("home_key12",home_key12);
            System.out.println(top12ReplyList.get(i).get("home_key12"));
        }
        List<Map<String, Object>> top10Post_typeList = replyDao.selectCountTop10();
        for (int i = 0;i < top10Post_typeList.size();i++){
            String top10Post_type = String.valueOf(top10Post_typeList.get(i).get("post_type"));
            String top10Title = postDao.selectTitleByPostType(top10Post_type);
            top10Post_typeList.get(i).put("title",top10Title);
            String address10 = top10Post_type.substring(3,top10Post_type.length()-10);
            String home_key10 = StringToBase64(address10+'&'+address);
            top10Post_typeList.get(i).put("home_key10",home_key10);
            top10Post_typeList.get(i).put("access_address",address);
        }
        modelMap.put("top12ReplyList", top12ReplyList);
        modelMap.put("top10Post_typeList", top10Post_typeList);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("resultList", resultList);
        return "/html/news";
    }

    //特定字符串变化
    public  static  String changeToList(String str){
        String str1 = str.replace(",","',").replace("post_type=","'post_type':'").replace("post_bounty=","'post_bounty':'").replace("click_num=","'click_num':'").replace("title=","'title':'").replace("add_time=","'add_time':'").replace("status=","'status':'").replace("}'","'}");
        StringBuilder stringBuilder=new StringBuilder(str1);
        stringBuilder.insert(str1.length()-2,"'");
        String str2 = stringBuilder.toString();
        return str2;
    }

    //String转为List<Map<String, Object>>
    public static List<Map<String, Object>> toListMap(String json){

        List<Object> list = JSON.parseArray(json);

        List< Map<String,Object>> listw = new ArrayList<Map<String,Object>>();
        for (Object object : list){
            Map<String,Object> ageMap = new HashMap<String,Object>();
            Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
        /*for (Entry<String, Object> entry : ret.entrySet()) {
            ageMap.put(entry.getKey());
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            listw.add(ageMap);  //添加到list集合  成为 list<map<String,Object>> 集合
        }  */
            listw.add(ret);
        }
        return listw;

    }

    //会员动态页面
    @RequestMapping(value = "/html/notice")
    public String htmlNotice(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        //根据会员address在post和reply中联表查查询回答数（影响大）和浏览率最高的前10个会员
        //根据post_type查询会员，重复会员名称删掉
        List<Map<String, Object>> List1 = postDao.selectTop10();
        List<String> List2 = new ArrayList();
        for (int i = 0;i < List1.size();i++){
            String post_type1 = String.valueOf(List1.get(i).get("post_type"));
            String address1 = Base64ToString(post_type1.substring(3,post_type1.length()-10));
            Map map1 = userDao.selectNameGradePhotoByAddress(address1);
            if (String.valueOf(map1.get("grade_num")).equals("5")){
                List2.add(String.valueOf(map1.get("username")));
            }
            String new_time = stampToTime(Long.valueOf(String.valueOf(List1.get(i).get("add_time"))));
            List1.get(i).put("add_time",new_time);
        }
        //遍历后判断赋给另一个list集合,保留顺序
        List<String> newList = new  ArrayList<String>();
        for (String cd:List2) {
            if(!newList.contains(cd)){
                newList.add(cd);
            }
        }
        //根据username查找对应信息
        List<Map<String, Object>> topList = new ArrayList<>();
        for (int j = 0;j < newList.size();j++){
            Map<String, Object> topMap = userDao.selectByUsername(newList.get(j));
            topList.add(topMap);
        }
        for (int m = 0;m < topList.size();m++){
            String str = "%"+StringToBase64(String.valueOf(topList.get(m).get("address")))+"%";
            List<Map<String, Object>> topList1 = postDao.selectByLikePostType3(str);
            topList.get(m).put("post_list",topList1);
        }
        for (int n = 0;n < topList.size();n++) {
            String str1 = String.valueOf(topList.get(n).get("post_list"));
            String home_key = StringToBase64(topList.get(n).get("address")+"&"+address);
            topList.get(n).put("home_key",home_key);
            List<Map<String, Object>> list = toListMap(changeToList(str1));
            for (int a = 0;a < list.size();a++) {
                String new_time = stampToTime(Long.valueOf(String.valueOf(list.get(a).get("add_time"))));
                list.get(a).put("add_time",new_time);
                int num = replyDao.selectCountReplyByPostType(String.valueOf(list.get(a).get("post_type")));
                list.get(a).put("num",num);
                String str2 = String.valueOf(list.get(a).get("post_type"));
                String str3 = str2.substring(0,3);
                String type = "";
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
                list.get(a).put("type",type);
            }
            topList.get(n).put("post_list",list);
        }
        List<Map<String, Object>> top12ReplyList = replyDao.selectCountTop12();
        for (int i = 0;i < top12ReplyList.size();i++){
            String reply_address = Base64ToString(String.valueOf(top12ReplyList.get(i).get("reply_address")));
            Map map1 = userDao.selectNameGradePhotoByAddress(reply_address);
            String home_key12 = StringToBase64(reply_address+'&'+address);
            top12ReplyList.get(i).put("username",map1.get("username"));
            top12ReplyList.get(i).put("profile_photo",map1.get("IFNULL(profile_photo,0)"));
            top12ReplyList.get(i).put("reply_address",reply_address);
            top12ReplyList.get(i).put("home_key12",home_key12);
            System.out.println(top12ReplyList.get(i).get("home_key12"));
        }
        List<Map<String, Object>> top10Post_typeList = replyDao.selectCountTop10();
        for (int i = 0;i < top10Post_typeList.size();i++){
            String top10Post_type = String.valueOf(top10Post_typeList.get(i).get("post_type"));
            String top10Title = postDao.selectTitleByPostType(top10Post_type);
            top10Post_typeList.get(i).put("title",top10Title);
            String address10 = top10Post_type.substring(3,top10Post_type.length()-10);
            String home_key10 = StringToBase64(address10+'&'+address);
            top10Post_typeList.get(i).put("home_key10",home_key10);
            top10Post_typeList.get(i).put("access_address",address);
        }
        List<Map<String, Object>> resultList = postDao.selectNoticePost();
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("top12ReplyList", top12ReplyList);
        modelMap.put("top10Post_typeList", top10Post_typeList);
        modelMap.put("resultList", resultList);
        modelMap.put("topList", topList);
        return "/html/notice";
    }

    //身份
    public static String user_status;
    //回帖者身份
    public static String reply_user_status;
    //是否采纳
    public static String had_accept ;
    //zanOk
    public static String IsZan ;

    //  会员贴子信息页面
    @RequestMapping(value = "/html/jie/detail")
    public String htmlJieDetail(ModelMap modelMap, String post_type, String address){
//        System.out.println(post_type);
        had_accept = "0";
        //增加浏览量
        postDao.updateClickNumByPostTypeAdd(post_type);
        String post_address = Base64ToString(post_type.substring(3,post_type.length()-10));
        String timestamp = post_type.substring(post_type.length()-10,post_type.length());
        //访问者
        Map map = userDao.selectByAddress(address);
        //贴子作者
        Map map2 = userDao.selectByAddress(post_address);
        Map map1 = postDao.selectByPostTypeAndAddTime(post_type, timestamp);
        List<Map<String,Object>> replyList = replyDao.selectByPostType(post_type);
        //判断是否该帖子已存在最佳答案
        for (int i = 0;i < replyList.size();i++){
            String status = String.valueOf(replyList.get(i).get("status"));
            //status为1是已经有最佳答案
            if (status.equals(String.valueOf(1))){
                had_accept = "1";
                break;
            }
        }
        //循环将时间戳转为日期格式
        for (int i = 0;i < replyList.size();i++) {
            String oldtime = String.valueOf(replyList.get(i).get("add_time"));
            String newtime = stampToTime(Long.valueOf(String.valueOf(oldtime)));
            replyList.get(i).put("add_time", newtime);
        }
            //通过reply_address找到该回帖的用户信息
        for (int i = 0;i < replyList.size();i++){
            String reply_address = String.valueOf(replyList.get(i).get("reply_address"));
            String reply_address64 = Base64ToString(reply_address);
//            replyList.get(i).put("username",userDao.selectUsernameByAddress(Base64ToString(reply_address)));
            Map map3 = userDao.selectNameGradePhotoByAddress(Base64ToString(reply_address));
            replyList.get(i).put("username",map3.get("username"));
            replyList.get(i).put("profile_photo",map3.get("IFNULL(profile_photo,0)"));
            replyList.get(i).put("reply_address64",reply_address64);
            //验证回帖者身份
            if (had_accept.equals("1")){
                reply_user_status = "no_take";
            }else {
                if (post_address.equals(reply_address64)){
                    reply_user_status = "no_take";
                }else if (!post_address.equals(address)){
                    reply_user_status = "no_take";
                }else {
                    reply_user_status = "take";
                }
            }
            replyList.get(i).put("reply_user_status",reply_user_status);
            //是否已点赞
            int reply_id = Integer.valueOf(String.valueOf(replyList.get(i).get("reply_id")));
            int countLike = likeDao.selectCountByReplyIdAndAddress(reply_id,address);//查询是否存在改该用户的点赞记录
            if (countLike == 0){
                IsZan = "zan";
            }else if (countLike == 1){
                int Liked = likeDao.selectByReplyIdAndAddress(reply_id,address);
                if (Liked == 1){
                    IsZan = "zanOk";
                }else if (Liked == 2){
                    IsZan = "zan";
                }
            }
            replyList.get(i).put("IsZan",IsZan);
        }
        //获取出type
        String type = String.valueOf(post_type).substring(0,3);
        //判断该贴子是否已被访问用户收藏
        String col_address = colDao.selectAddressByColPostType(post_type);//访问者
        //验证发帖者身份
        if (address.equals(post_address)){
            user_status = "m";
        }else if (address.equals(col_address)){
            user_status = "y";//已收藏
        }else {
            user_status = "c";
        }
        modelMap.put("username", map.get("username"));
        modelMap.put("post_username", map2.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("post_grade_num",map2.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("post_address", post_address);
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("post_profile_photo", map2.get("profile_photo"));
        modelMap.put("click_num",map1.get("click_num"));
        modelMap.put("title", map1.get("title"));
        modelMap.put("main_spec", map1.get("main_spec"));
        modelMap.put("post_bounty", map1.get("post_bounty"));
        modelMap.put("post_type",map1.get("post_type"));
        modelMap.put("type",type);
        modelMap.put("user_status",user_status);
        modelMap.put("replyList", replyList);
        return "/html/jie/detail";
    }

    //    会员修改贴子
    @RequestMapping(value = "/html/jie/editAdd")
    public String htmlJieEditAdd(ModelMap modelMap, String post_type){
        String address = Base64ToString(post_type.substring(3,post_type.length()-10));
        String timestamp = post_type.substring(post_type.length()-10,post_type.length());
        String select_value = post_type.substring(0,3);
        Map map = userDao.selectByAddress(address);
        Map map1 = postDao.selectByPostTypeAndAddTime(post_type, timestamp);
        modelMap.put("username", map.get("username"));
        modelMap.put("password", map.get("password"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("address", map.get("address"));
        modelMap.put("personal_note", map.get("personal_note"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("select_value", select_value);
        modelMap.put("title", map1.get("title"));
        modelMap.put("main_spec", map1.get("main_spec"));
        modelMap.put("post_bounty", map1.get("post_bounty"));
        modelMap.put("post_type",map1.get("post_type"));
        return "/html/jie/editAdd";
    }

    //    会员个人中心主页
    @RequestMapping(value = "/html/user/home")
    public String htmlUserHome(ModelMap modelMap, String home_key){
        String str = Base64ToString(home_key);
        String[] temp;
        String delimeter = "&";
        temp = str.split(delimeter);
        String home_address = URLDecoder.decode(temp[0]);
        String access_address = URLDecoder.decode(temp[1]);
        Map AccessMap = userDao.selectByAddress(access_address);
        Map HomeMap = userDao.selectByAddress(home_address);
        //获取该用户发表最近的至多7张贴子
        String address1 = "%"+StringToBase64(home_address)+"%";
        List<Map<String, Object>> postList7 = postDao.select7PostByAddressOrderTime(address1);
        for (int i = 0;i < postList7.size();i++) {
            int reply_count = replyDao.selectCountReplyByPostType(String.valueOf(postList7.get(i).get("post_type")));
            postList7.get(i).put("reply_count",reply_count);
        }
        //获取该用户最近至多3个回帖
        List<Map<String, Object>> replyList3 = replyDao.select3ByAddress(StringToBase64(home_address));
        for (int i = 0;i < replyList3.size();i++) {
            String post_title = postDao.selectTitleByPostType(String.valueOf(replyList3.get(i).get("post_type")));
            replyList3.get(i).put("post_title", post_title);
        }
        //循环将时间戳转为日期格式
        for (int i = 0;i < postList7.size();i++) {
            String oldtime = String.valueOf(postList7.get(i).get("add_time"));
            String newtime = stampToTime(Long.valueOf(String.valueOf(oldtime)));
            postList7.get(i).put("add_time", newtime);
        }
        for (int i = 0;i < replyList3.size();i++) {
            String oldtime1 = String.valueOf(replyList3.get(i).get("add_time"));
            String newtime1 = stampToTime(Long.valueOf(String.valueOf(oldtime1)));
            replyList3.get(i).put("add_time", newtime1);
            String reply_address = Base64ToString(String.valueOf(replyList3.get(i).get("reply_address")));
            replyList3.get(i).put("reply_address", reply_address);
        }
        modelMap.put("access_username", AccessMap.get("username"));
        modelMap.put("access_total", AccessMap.get("total"));
        modelMap.put("home_username", HomeMap.get("username"));
        modelMap.put("home_address",home_address);
        modelMap.put("access_address",access_address);
        modelMap.put("access_grade_num",AccessMap.get("grade_num"));
        modelMap.put("home_grade_num",HomeMap.get("grade_num"));
        modelMap.put("access_profile_photo", AccessMap.get("profile_photo"));
        modelMap.put("home_profile_photo", HomeMap.get("profile_photo"));
        modelMap.put("city", HomeMap.get("city"));
        modelMap.put("personal_note", HomeMap.get("personal_note"));
        modelMap.put("add_time", stampToTime(Long.valueOf(String.valueOf(HomeMap.get("add_time")))));
        modelMap.put("postList7",postList7);
        modelMap.put("replyList3",replyList3);
        return "html/user/home";
    }

    @RequestMapping(value = "/html/user/message")
    public String htmlUserMessage(ModelMap modelMap, String address){
        Map map = userDao.selectByAddress(address);
        List<Map<String, Object>> messageList = messageDao.selectMessageByLikePostTypeAndClearState('%'+StringToBase64(address)+'%');
        for (int i = 0;i < messageList.size();i++){
            String new_time = stampToTime(Long.valueOf(String.valueOf(messageList.get(i).get("add_time"))));
            messageList.get(i).put("add_time",new_time);
            //获取回帖者的address
            String address1 = Base64ToString(String.valueOf(messageList.get(i).get("address")));
            String reply_username = userDao.selectUsernameByAddress(address1);
            messageList.get(i).put("reply_username",reply_username);
            //获取贴子名称
            String title = postDao.selectTitleByPostType(String.valueOf(messageList.get(i).get("post_type")));
            messageList.get(i).put("title",title);
            //获取home_key
            String home_key = StringToBase64(address1+"&"+address);
            messageList.get(i).put("home_key",home_key);
        }
        messageDao.updateBrowsedByLikePostType('%'+StringToBase64(address)+'%');
        modelMap.put("username", map.get("username"));
        modelMap.put("address", map.get("address"));
        modelMap.put("grade_num",map.get("grade_num"));
        modelMap.put("profile_photo", map.get("profile_photo"));
        modelMap.put("my_home_key", StringToBase64(address+"&"+address));
        modelMap.put("messageList", messageList);
        return "/html/user/message";
    }

    @RequestMapping(value = "/webuploader")
    public String webuploader(){
        return "webuploader";
    }
}
