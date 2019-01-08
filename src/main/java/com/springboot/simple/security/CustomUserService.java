//package com.springboot.simple.security;
//
//import com.springboot.simple.dao.UserDao;
//import com.springboot.simple.domain.SysRole;
//import com.springboot.simple.domain.SysUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CustomUserService implements UserDetailsService {
//
//    @Autowired
//    UserDao userDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String username){
//        SysUser user = userDao.findByUserName(username);
//        if (user == null){
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //用于添加用户的权限。只要把用户权限添加到authorities就行
//        for (SysRole role:user.getRoles()){
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//            System.out.println(role.getName());
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
//    }
//}
