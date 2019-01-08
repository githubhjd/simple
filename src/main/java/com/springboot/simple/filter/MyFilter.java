//package com.springboot.simple.filter;
//
//import org.apache.ibatis.annotations.Mapper;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.logging.Filter;
//
//@Component
//@Mapper
//@WebFilter(urlPatterns = {"/**"}, filterName = "MyFilter")
//public class MyFilter implements Filter {
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse req = (HttpServletResponse)response;
//        req.setHeader("X-Frame-Options", "SAMEORIGIN");// 解决IFrame拒绝的问题
//        chain.doFilter(request, response);
//    }
//}
