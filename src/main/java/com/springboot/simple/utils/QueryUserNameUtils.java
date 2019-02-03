package com.springboot.simple.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class QueryUserNameUtils {

    public static String getUserName(){
        Subject currentUser = SecurityUtils.getSubject();
        Object o = currentUser.getPrincipals();
        String str = o.toString();
        int start = str.indexOf("username=") + 9;
        int end = str.indexOf(", password");
            String sub  = str.substring(start, end);
            System.out.println("当前用户：" + sub);
        return sub ;
    }

}