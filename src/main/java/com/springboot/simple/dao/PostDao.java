package com.springboot.simple.dao;

import com.springboot.simple.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface PostDao {

    void insertPost(@Param("post_type") String post_type,@Param("title") String title,@Param("main_spec") String main_spec,@Param("post_bounty") String post_bounty,@Param("add_time") int add_time);

    Map selectByPostTypeAndAddTime(@Param("post_type") String post_type, @Param("add_time") String add_time);

    void updatePost(@Param("post_type") String post_type, @Param("title") String title, @Param("main_spec") String main_spec, @Param("post_bounty") String post_bounty, @Param("add_time") int add_time, @Param("old_post_type") String old_post_type);

    List<Map<String, Object>> selectByLikePostType(@Param("address") String address);

    List<Map<String, Object>> selectByLikePostType3(@Param("address") String address);

    String selectTitleByPostType(@Param("post_type") String post_type);

    Map<String, Object> selectByPostType(@Param("post_type") String post_type);

    List<Map<String, Object>> selectAllPost();

    List<Map<String, Object>> selectAllOutStandingPost();

    List<Map<String, Object>> selectAllOverPost();

    List<Map<String, Object>> selectQuizPost();

    List<Map<String, Object>> selectOutStandingQuizPost();

    List<Map<String, Object>> selectOverQuizPost();

    List<Map<String, Object>> selectSharePost();

    List<Map<String, Object>> selectOutStandingSharePost();

    List<Map<String, Object>> selectOverSharePost();

    List<Map<String, Object>> selectDiscussPost();

    List<Map<String, Object>> selectOutStandingDiscussPost();

    List<Map<String, Object>> selectOverDiscussPost();

    List<Map<String, Object>> selectSuggestPost();

    List<Map<String, Object>> selectNewsPost();

    List<Map<String, Object>> selectNoticePost();

    List<Map<String, Object>> select7PostByAddressOrderTime(@Param("address") String address);

    void updateClickNumByPostTypeAdd(@Param("post_type") String post_type);

    //结贴
    void updateStatusOver(@Param("clear_time") int clear_time,@Param("post_type") String post_type);

    //查找浏览量和回答量top10
    List<Map<String, Object>> selectTop10();

    //获取3篇最近文章
    List<Map<String, Object>> select3PostByPostType(@Param("post_type") String post_type);

}
