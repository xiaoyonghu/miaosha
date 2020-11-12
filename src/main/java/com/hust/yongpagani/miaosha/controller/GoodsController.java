package com.hust.yongpagani.miaosha.controller;

import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.service.MiaoshaUserService;
import com.hust.yongpagani.miaosha.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser user){
        //System.out.println("GoodsController层中user对象："+user.toString());
        model.addAttribute("user", user);
        return "goods_list";
    }

}
