package com.springboot.simple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    UserDetailsService customUserService(){//注册UserDetailsService
//        return new CustomUserService();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(customUserService());//user Details Service 验证
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/js/**","/css/**","/img/**","/layui/**").permitAll()//此处存在路径疑惑
                .anyRequest().authenticated()  //任何请求，登录后可以访问
                .and()
                .headers().frameOptions().disable()//X-Frame-Options deny，解决浏览器拒绝iframe
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll() //登录界面用户任意访问
                .and()
                .logout().permitAll();//注销行为任意访问
    }


}
