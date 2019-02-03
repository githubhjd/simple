package com.springboot.simple.controller;

import com.springboot.simple.domain.Goods;
import com.springboot.simple.service.GoodsService;
import com.springboot.simple.utils.QueryUserNameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/shiro/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/addGoods")
    @RequiresPermissions("user:add")
    public String gotoAddPage(Model model) {
        String username = QueryUserNameUtils.getUserName();
        model.addAttribute("username", username);
        return "/shiro/addGoods";
    }

    @RequestMapping("/updateGoodsPage")
    @RequiresPermissions("user:update")
    public String gotoUpdateGoodsPage(@RequestParam("name") String name, Model model) {
        Goods goods = goodsService.queryOneGoodsByName(name);
        model.addAttribute("goods", goods);
        String username = QueryUserNameUtils.getUserName();
        model.addAttribute("username", username);
        return "/shiro/updateGoods";
    }

    @RequestMapping("/add")
    @RequiresPermissions("user:add")
    public String addGoods(Goods goods) {
        goodsService.saveOneGoods(goods);
        return "redirect:/shiro/queryAll";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("user:delete")
    public String deleteGoods(@RequestParam("id") Integer id) {
        goodsService.delOneGoodsById(id);
        return "redirect:/shiro/queryAll";
    }

    @RequestMapping("/update")
    @RequiresPermissions("user:update")
    public String updateGoods(Goods goods) {
        goodsService.updateOneGoodsById(goods);
        return "redirect:/shiro/queryAll";
    }

    @RequestMapping("/query")
    @RequiresPermissions("user:query")
    public String queryOneGoods(String name, Model model) {
        Goods goods = goodsService.queryOneGoodsByName(name);
        model.addAttribute("goods", goods);
        String username = QueryUserNameUtils.getUserName();
        model.addAttribute("username", username);
        return "/shiro/showGoods";
    }

    @RequestMapping("/queryAll")
    @RequiresPermissions("user:query")
    public String queryAllGoods(Model model) {
        List<Goods> goodsList = goodsService.queryAllGoods();
        model.addAttribute("goodsList", goodsList);
        String username = QueryUserNameUtils.getUserName();
        model.addAttribute("username", username);
        return "/shiro/showGoods";
    }

}
