package com.springboot.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Mapper
@Component
public interface UserDao {

    String selectPasswordByUsername(@Param("username") String username);

    String selectPasswordByAddress(@Param("address") String address);

    Map selectByUsername(@Param("username") String username);

    Map selectByAddress(@Param("address") String address);

    void insertUser(@Param("username") String username, @Param("password") String password, @Param("address") String address, @Param("add_time") int add_time, @Param("grade_num") int grade_num, @Param("grade_name") String grade_name);

    void updateUser(@Param("username") String username, @Param("sex") int sex, @Param("city") String city, @Param("personal_note") String personal_note, @Param("address") String address);

    void updateUserPassword(@Param("password") String password, @Param("address") String address);

    void updateUserProfilePhoto(@Param("profile_photo") String profile_photo, @Param("address") String address);

}
