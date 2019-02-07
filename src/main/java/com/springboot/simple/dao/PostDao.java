package com.springboot.simple.dao;

import com.springboot.simple.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Map;

@Mapper
@Component
public interface PostDao {

    void insertPost(@Param("post_type") String post_type,@Param("title") String title,@Param("main_spec") String main_spec,@Param("post_bounty") String post_bounty,@Param("add_time") int add_time);

    Map selectByPostTypeAndAddTime(@Param("post_type") String post_type, @Param("add_time") String add_time);

    void updatePost(@Param("post_type") String post_type, @Param("title") String title, @Param("main_spec") String main_spec, @Param("post_bounty") String post_bounty, @Param("add_time") int add_time, @Param("old_post_type") String old_post_type);


}
