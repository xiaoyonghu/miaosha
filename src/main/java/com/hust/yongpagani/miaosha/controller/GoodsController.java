package com.hust.yongpagani.miaosha.controller;

import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.service.GoodsService;
import com.hust.yongpagani.miaosha.service.MiaoshaUserService;
import com.hust.yongpagani.miaosha.service.RedisService;
import com.hust.yongpagani.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * @author Created by Divo
 * @date 2020/10/31
 * Description:登录控制器
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;


    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser user) {
        //System.out.println("GoodsController层中user对象："+user.toString());
        model.addAttribute("user", user);
        List<GoodsVo> goods = goodsService.goodsVoList();
        model.addAttribute("goodsList", goods);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, MiaoshaUser user, @PathVariable("goodsId") long goodsId) {

        model.addAttribute("user", user);

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        //计算时间
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0; //秒杀状态  0 未开始、1 开始了、2 结束
        int remainSeconds = 0; //秒杀开始还剩多少秒

        if (now < startAt) { //秒杀未开始
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) { //秒杀已结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else { //秒杀正在进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }

        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        return "goods_detail";
    }

}
