package com.hust.yongpagani.miaosha.controller;

import com.hust.yongpagani.miaosha.result.Result;
import com.hust.yongpagani.miaosha.service.MiaoshaUserService;
import com.hust.yongpagani.miaosha.service.RedisService;
import com.hust.yongpagani.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author Created by Divo
 * @date 2020/10/31
 * Description:登录控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    //一般都是这种写法
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }


    @RequestMapping("do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info("前端传过来的用户信息：" + loginVo.toString());  //测试到底传过来的是什么

        miaoshaUserService.login(response, loginVo);
        return Result.success(true);
    }

}
