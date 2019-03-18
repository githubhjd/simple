package com.springboot.simple;

import com.alibaba.fastjson.JSON;
import com.sun.glass.ui.Application;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
//@SpringBootTest
public class SimpleApplicationTests {

//	@Test
//	public void contextLoads() {
//		String filName = changName("卡卡西_看图王.jpg");
//		System.out.println(filName);
//	}
//
//	//修改上传头像的照片，利用随机数防止重复
//	public static String changName(String oldName){
//		Random r = new Random();
//		Date d = new Date();
//		String newName = oldName.substring(oldName.indexOf('.'));
//		newName = r.nextInt(99999999) + d.getTime() + newName;
//		return newName;
//	}

	@Test
	public void MyTest(){
//		Subject currentUser = SecurityUtils.getSubject();
//		Object o = currentUser.getPrincipals();
//		String str = o.toString();
//		System.out.println(str);

//		String str=" a(http://www.baidu.com)[http://www.baidu.com] ";
//		String pattern = "a+\\(+((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+\\)+\\[+((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+\\]";
//
//		Pattern p = Pattern.compile(pattern);
//		Matcher m = p.matcher(str);
//		if (m.find())
//		{
//			String sbraces1=m.group();
//			System.out.print("("+sbraces1.toString()+")"+"\n");
//		}
//		String str = "face[微笑]";
//
//		String pattern = "face+\\[+[\\u4e00-\\u9fa5]+\\]";
//		Pattern p = Pattern.compile(pattern);
//		Matcher m = p.matcher(str);
//		if (m.find())
//		{
//			String sbraces4=m.group().substring(5,m.group().length()-1);
//			System.out.print("("+sbraces4.toString()+")"+"\n");
//		}
//		String str = "img[http://172.6.1.243:8100/upload/1547695138522卡卡西_看图王.jpg] ";
//
//		String pattern = "img+\\[+(.*?)+\\]";
//		Pattern p = Pattern.compile(pattern);
//		Matcher m = p.matcher(str);
//		if (m.find())
//		{
//			String sbraces4=m.group().substring(4,m.group().length()-1);
//			System.out.print("("+sbraces4.toString()+")"+"\n");
//		}
//		String str2 = "http://172.6.1.243:8100/upload/1547695138522卡卡西_看图王.jpg";
//		String str1 = "<img src=\"http://172.6.1.243:8100/upload/1547695138522卡卡西_看图王.jpg\">";
//		String str3 = "<img src=\""+str2+"\">";
//		System.out.println(str3);

//		Map<String, Object> map=new HashMap<String, Object>();
//
//		map.put("id", "sunny");
//
//		map.put("value", "syl");
//
//		System.out.println(map.get("id"));
//
//		System.out.println(map.get("value"));

//		String str = "100MjYzNTgzMTY1MkBxcS5jb20=1550416501";
//
//		String pattern = "img+\\[+(.*?)+\\]";
//		Pattern p = Pattern.compile(pattern);
//		Matcher m = p.matcher(str);
//		if (m.find())
//		{
//			sbraces10=m.group().substring(4,m.group().length()-1);
//			System.out.print("("+sbraces10.toString()+")"+"\n");
//		}
//		return sbraces10;

//		String str = "MjYzNTgzMTY1MkBxcS5jb20=";
//		byte[] textbyte = str.getBytes();
//		byte[] byte1 = Base64.getDecoder().decode(textbyte);
//		String str1 = new String(byte1);
//		System.out.println(str1);

//		String str = "@hjd";
//		String pattern = "@+(.*)";
//		Pattern p = Pattern.compile(pattern);
//		Matcher m = p.matcher(str);
//		if (m.find()){
//
//			System.out.println(m.group());
//		}
		String str = "[{'post_type':'000MjYzNTgzMTY1MkBxcS5jb20=1551667264', 'click_num':'105', 'title':'layui真的好用吗', 'add_time':'1551667264', 'status':'1'}, {'post_type':'099MjYzNTgzMTY1MkBxcS5jb20=1551319823', 'click_num':'14', 'title':'fddf', 'add_time':'1551319823', 'status':'1'}, {'post_type':'100MjYzNTgzMTY1MkBxcS5jb20=1551007025', 'click_num':'13', 'title':'test', 'add_time':'1551007025', 'status':'1'}]";
		String str1 = "[{post_type=000MjYzNTgzMTY1MkBxcS5jb20=1551667264, click_num=105, title=layui真的好用吗, add_time=1551667264, status=1}, {post_type=099MjYzNTgzMTY1MkBxcS5jb20=1551319823, click_num=14, title=fddf, add_time=1551319823, status=1}, {post_type=100MjYzNTgzMTY1MkBxcS5jb20=1551007025, click_num=13, title=test, add_time=1551007025, status=1}]";
		String str2 = str1.replace(",","',").replace("post_type=","'post_type':'").replace("click_num=","'click_num':'").replace("title=","'title':'").replace("add_time=","'add_time':'").replace("status=","'status':'").replace("}'","'}");
		StringBuilder stringBuilder=new StringBuilder(str2);
 		stringBuilder.insert(str2.length()-2,"'");
		String str3 = stringBuilder.toString();

//		List<Object> list = JSON.parseArray(str);
		System.out.println(str3);
		List<Object> list = JSON.parseArray(str3);
		System.out.println(list);
	}
}
