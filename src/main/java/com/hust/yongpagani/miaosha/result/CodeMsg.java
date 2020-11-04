package com.hust.yongpagani.miaosha.result;

/**
 * @author Created by Divo
 * @date 2020/10/28
 * Description:To Do
 * Status:new
 */
public class CodeMsg {
    public int code;
    public String msg;

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常:%s");

    //登录模块 5002XX
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500201, "密码不能为空");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500202, "密码错误");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500203, "手机号不能为空");
    public static CodeMsg MOBILE_PATTERN_ERROR = new CodeMsg(500204, "手机号格式不正确");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500205, "手机号不存在");


    //商品模块 5003XX
    //订单模块 5004XX
    //秒杀模块 5005XX


    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String msg = String.format(this.msg, args); //拼接了以后的消息
        return new CodeMsg(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
