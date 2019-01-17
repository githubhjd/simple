package com.springboot.simple.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //前面為url訪問路徑，後面為在本地保存的路徑
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/IdeaProject/upload/");
        super.addResourceHandlers(registry);
    }
}
