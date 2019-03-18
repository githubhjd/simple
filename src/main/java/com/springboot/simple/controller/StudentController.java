package com.springboot.simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("studentMgmt")
public class StudentController {
    @RequestMapping("queryStudentInfo")
    public String queryStudentInfo(Model model) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Object> student = new HashMap<String, Object>(){{
            put("sid", "101");
            put("sname", "张三");
            put("sage", "20");
            put("scourse", new HashMap<String, String>(){{
                put("cname", "语文,数学,英语");
                put("cscore", "93,95,98");
            }});
        }};
        resultList.add(student);
        student = new HashMap<String, Object>(){{
            put("sid", "102");
            put("sname", "李四");
            put("sage", "30");
            put("scourse", new HashMap<String, String>(){{
                put("cname", "物理,化学,生物");
                put("cscore", "92,93,97");
            }});
        }};
        resultList.add(student);
        model.addAttribute("resultList", resultList);
        return "studentInfo";
    }
}
