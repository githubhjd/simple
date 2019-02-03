package com.springboot.simple.service;


import com.springboot.simple.domain.Goods;

import java.util.List;

public interface GoodsService {

    void saveOneGoods(Goods goods);

    void delOneGoodsById(Integer id);

    void updateOneGoodsById(Goods goods);

    Goods queryOneGoodsByName(String name);

    List<Goods> queryAllGoods();
}
