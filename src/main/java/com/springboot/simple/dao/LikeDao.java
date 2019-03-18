package com.springboot.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LikeDao {

    int selectByReplyIdAndAddress(@Param("reply_id") int reply_id,@Param("address") String address);

    int selectCountByReplyIdAndAddress(@Param("reply_id") int reply_id,@Param("address") String address);

    void insertLike(@Param("reply_id") int reply_id, @Param("address") String address, @Param("liked") int liked,@Param("change_time") int change_time,@Param("add_time") int add_time);

    void updateLiked1(@Param("reply_id") int reply_id,@Param("address") String address);

    void updateLiked2(@Param("reply_id") int reply_id,@Param("address") String address);
}
