package com.springboot.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleApplicationTests {

	@Test
	public void contextLoads() {
		String filName = changName("卡卡西_看图王.jpg");
		System.out.println(filName);
	}

	//修改上传头像的照片，利用随机数防止重复
	public static String changName(String oldName){
		Random r = new Random();
		Date d = new Date();
		String newName = oldName.substring(oldName.indexOf('.'));
		newName = r.nextInt(99999999) + d.getTime() + newName;
		return newName;
	}

}
