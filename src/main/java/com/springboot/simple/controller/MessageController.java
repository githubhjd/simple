package com.springboot.simple.controller;

import com.springboot.simple.dao.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.springboot.simple.controller.IndexController.StringToBase64;

@RestController
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @RequestMapping(value = "/message/nums", method = RequestMethod.POST)
    public Map MessageNums(String access_address){
//        System.out.println(_);
        int count = messageDao.selectCountMessageByLikePostType('%'+ StringToBase64(access_address)+'%');
        Map map = new HashMap();
        map.put("status",0);
        map.put("count",count);
        map.put("access_address",access_address);
        return map;
    }

    @RequestMapping(value = "/message/remove", method = RequestMethod.POST)
    public Map MessageRemove(String id,String all,String access_address){
        if (all.equals("true")){
            messageDao.updateClearStateByLikePostType('%'+ StringToBase64(access_address)+'%');
        }else {
            messageDao.updateClearStateById(Integer.valueOf(id));
        }
        Map map = new HashMap();
        map.put("status",0);
        return map;
    }
}
