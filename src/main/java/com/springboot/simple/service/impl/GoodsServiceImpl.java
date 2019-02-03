package com.springboot.simple.service.impl;

import com.springboot.simple.dao.GoodsDao;
import com.springboot.simple.domain.Goods;
import com.springboot.simple.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public void saveOneGoods(Goods goods) {
        goodsDao.saveOneGoods(goods);
    }

    @Override
    public void delOneGoodsById(Integer id) {
        goodsDao.delOneGoodsById(id);
    }

    @Override
    public void updateOneGoodsById(Goods goods) {
        goodsDao.updateOneGoodsById(goods);
    }

    @Override
    public Goods queryOneGoodsByName(String name) {
        return goodsDao.queryOneGoodsByName(name);
    }

    @Override
    public List<Goods> queryAllGoods() {
        return goodsDao.queryAllGoods();
    }
}
