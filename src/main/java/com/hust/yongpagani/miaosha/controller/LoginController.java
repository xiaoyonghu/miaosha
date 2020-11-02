package com.hust.yongpagani.miaosha.controller;

import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.result.CodeMsg;
import com.hust.yongpagani.miaosha.result.Result;
import com.hust.yongpagani.miaosha.service.MiaoshaUserService;
import com.hust.yongpagani.miaosha.service.RedisService;
import com.hust.yongpagani.miaosha.util.ValidatorUtil;
import com.hust.yongpagani.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result<Boolean> doLogin(LoginVo loginVo) {
        //log.info(loginVo.toString());  //测试到底传过来的是什么
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //手机验证
        if (StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.MOBLE_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)) {
            return Result.error(CodeMsg.MOBLE_PATTERN_ERROR);
        }

        //密码验证
        if (StringUtils.isEmpty(password)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }

        CodeMsg cm = miaoshaUserService.login(loginVo);
        if (cm.getCode() == 0) {
            return Result.success(true);
        } else {
            return Result.error(cm);
        }
    }

}
