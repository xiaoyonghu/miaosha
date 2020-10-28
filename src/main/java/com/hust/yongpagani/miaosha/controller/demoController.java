package com.hust.yongpagani.miaosha.controller;

import com.hust.yongpagani.miaosha.result.CodeMsg;
import com.hust.yongpagani.miaosha.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;

/**
 * @author Created by Divo
 * @date 2020/10/28
 */
@Controller
@RequestMapping("/demo")
public class demoController {

    @RequestMapping("/home")
    @ResponseBody
    String home() {
        return "hello world";
    }

    //成功
    @RequestMapping("/msg_sucess")
    @ResponseBody
    Result<String> msgSucess() {
        return Result.success("hello ,world");
    }

    //失败
    @RequestMapping("/msg_error")
    @ResponseBody
    Result<String> msgError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    //失败
    @RequestMapping("/thymeleaf")
    String thymeleaf(Model model) {
        model.addAttribute("name","Pangani、BC");
        return "hello";
    }
}
