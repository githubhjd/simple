package com.springboot.simple.dao;

import com.springboot.simple.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodsDao {

    void saveOneGoods(Goods goods);

    void delOneGoodsById(Integer id);

    void updateOneGoodsById(Goods goods);

    Goods queryOneGoodsByName(String name);

    List<Goods> queryAllGoods();
}
