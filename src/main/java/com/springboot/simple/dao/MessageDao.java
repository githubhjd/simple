package com.springboot.simple.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface MessageDao {

    void insertMessage(@Param("post_type") String post_type, @Param("address") String address,@Param("browsed") int browsed,@Param("clear_state") int clear_state, @Param("state") int state, @Param("mention_person") String mention_person, @Param("add_time") int add_time);

    List<Map<String, Object>> selectAllMessage(@Param("address") String address);

    List<Map<String, Object>> selectMessageByLikePostTypeAndClearState(@Param("like_post_type") String like_post_type);

    int selectCountMessageByLikePostType(@Param("like_post_type") String like_post_type);

    void updateBrowsedByLikePostType(@Param("like_post_type") String like_post_type);

    void updateClearStateByLikePostType(@Param("like_post_type") String like_post_type);

    void updateClearStateById(@Param("id") int id);
}
