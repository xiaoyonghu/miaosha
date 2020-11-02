package com.hust.yongpagani.miaosha.controller;

import com.hust.yongpagani.miaosha.domain.User;
import com.hust.yongpagani.miaosha.result.CodeMsg;
import com.hust.yongpagani.miaosha.result.Result;
import com.hust.yongpagani.miaosha.service.RedisService;
import com.hust.yongpagani.miaosha.service.UserService;
import com.hust.yongpagani.miaosha.util.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Created by Divo
 * @date 2020/10/28
 */
@RestController
@RequestMapping("/demo")
public class demoController {

    @Autowired
    public UserService userService;

    @Autowired
    public RedisService redisService;

    @RequestMapping("/home")
    String home() {
        return "hello world";
    }

    //成功
    @RequestMapping("/msg_sucess")
    Result<String> msgSucess() {
        return Result.success("hello ,world");
    }

    //失败
    @RequestMapping("/msg_error")
    Result<String> msgError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }


    @RequestMapping("/thymeleaf")
    String thymeleaf(Model model) {
        model.addAttribute("name","Pangani、BC");
        return "hello";
    }

    @RequestMapping("/db/get")
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    //测试事务
    @RequestMapping("/db/tx")
    public Result<Boolean> tx() {
        userService.tx();
        return Result.success(true);
    }

    //测试整合Redis
    @RequestMapping("/redis/set")
    public Result<Boolean> redisSet() {
        User user = new User(1,"1111");

        redisService.set(UserKey.getById,""+1,user);

        return Result.success(true);
    }

    //测试整合Redis
    @RequestMapping("/redis/get")
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }


}
