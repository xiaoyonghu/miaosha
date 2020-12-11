package com.hust.yongpagani.miaosha.controller;

import com.hust.yongpagani.miaosha.domain.MiaoshaOrder;
import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.domain.OrderInfo;
import com.hust.yongpagani.miaosha.result.CodeMsg;
import com.hust.yongpagani.miaosha.service.*;
import com.hust.yongpagani.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Created by Divo
 * @date 2020/10/31
 * Description:登录控制器
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String list(Model model, MiaoshaUser user,
                       @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);

        //判断是否登录
        if (user == null) {
            return "login";
        }

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errMsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }

        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errMsg", CodeMsg.REPEAT_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        //减库存、下订单、写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods); //需要用户信息和 商品信息
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goods);
        return "order_detail";
    }

}
