//package com.springboot.simple.config;
//
//import com.springboot.simple.filter.MyFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@Component
//public class FilterConfig {
//    //注册filter拦截器
//
//    @Autowired
//    private MyFilter filter;
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(filter);
//        List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/*");// 设置匹配的url
//        registrationBean.setUrlPatterns(urlPatterns);
//        return registrationBean;
//    }
//}
