package com.springboot.simple;

import com.sun.glass.ui.Application;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Random;
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

		String txt=" a(http://www.baidu.com)[http://www.baidu.com] ";

//		String re1=".*?";	// Non-greedy match on filler
		String re2="^a((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";	// Square Braces 1

		Pattern p = Pattern.compile(re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(txt);
		if (m.find())
		{
			String sbraces1=m.group(1);
			System.out.print("("+sbraces1.toString()+")"+"\n");
		}
	}

}
