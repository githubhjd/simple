package com.springboot.simple.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.simple.dao.ColDao;
import com.springboot.simple.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ColController {

    @Autowired
    private ColDao colDao;

    @Autowired
    private PostDao postDao;

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

    //收藏贴子
    @RequestMapping(value = "/addCol")
    public String addCol(String col_post_type,String address){
        int col_time = (int)(System.currentTimeMillis()/1000);//时间戳
        colDao.insertCol(col_post_type,address,col_time);
        return col_post_type;
    }

    //用户收藏贴子信息
    @RequestMapping(value = "/myColListShow", method = RequestMethod.GET)
    public String myPostListShow(String address, int page, int limit ){

        String result = "";
        try {
            List<Map<String, Object>> postList = colDao.selectColByAddress(address);
            //循环将时间戳转为日期格式
            for (int i = 0;i < postList.size();i++) {
                String oldtime = String.valueOf(postList.get(i).get("col_time"));
                String newtime = stampToTime(Long.valueOf(String.valueOf(oldtime)));
                postList.get(i).put("col_time", newtime);
                String title = postDao.selectTitleByPostType(String.valueOf(postList.get(i).get("col_post_type")));
                postList.get(i).put("title",title);
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
}
