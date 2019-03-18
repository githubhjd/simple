package com.springboot.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ReplyDao {
    void InsertReply(@Param("post_type") String post_type, @Param("reply_main_spec") String reply_main_spec, @Param("status") int status, @Param("like_num") int like_num, @Param("add_time") int add_time, @Param("reply_address") String reply_address);

    List<Map<String, Object>> selectByPostType(@Param("post_type") String post_type);

    void updateStatusReplyById(@Param("reply_id") int reply_id);

    void updateLikeNumReplyByIdAdd(@Param("reply_id") int reply_id);

    void updateLikeNumReplyByIdSub(@Param("reply_id") int reply_id);

    List<Map<String, Object>> select3ByAddress(@Param("reply_address") String reply_address);

    int selectCountReplyByPostType(@Param("post_type") String post_type);

    List<Map<String, Object>> selectCountTop12();

    List<Map<String, Object>> selectCountTop10();

    List<Map<String, Object>> selectCountTop4();


}
