package com.hust.yongpagani.miaosha.service;

import com.hust.yongpagani.miaosha.dao.MiaoshaUserMapper;
import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.exception.GlobalException;
import com.hust.yongpagani.miaosha.result.CodeMsg;
import com.hust.yongpagani.miaosha.util.MD5Util;
import com.hust.yongpagani.miaosha.util.MiaoshaUserKey;
import com.hust.yongpagani.miaosha.util.UUIDUtil;
import com.hust.yongpagani.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Created by Divo
 * @date 2020/10/31
 */

@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    @Autowired
    private RedisService redisService;

    public MiaoshaUser getMiaoshaUserByMobile(Long mobile) {
        return miaoshaUserMapper.getMiaoshaUserByMobile(mobile);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();

        MiaoshaUser user = getMiaoshaUserByMobile(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        //数据库密码
        String passDB = user.getPassword();
        String saltDB = user.getSalt();

        //客户端加密密码 ---> 数据库密码
        String formPassToDB = MD5Util.formPassToDBPass(formPass, saltDB);

        if (!formPassToDB.equals(passDB)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        //生成Cookie
        String token = UUIDUtil.gernateUUID();
        redisService.set(MiaoshaUserKey.token, token, user); //tk:SDSDSCDCDFC
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds()); //设置时间
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }
}
