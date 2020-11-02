package com.hust.yongpagani.miaosha.service;

import com.hust.yongpagani.miaosha.dao.MiaoshaUserMapper;
import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.result.CodeMsg;
import com.hust.yongpagani.miaosha.util.MD5Util;
import com.hust.yongpagani.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by Divo
 * @date 2020/10/31
 */

@Service
public class MiaoshaUserService {

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    public MiaoshaUser getMiaoshaUserByMobile(Long mobile) {
        return miaoshaUserMapper.getMiaoshaUserByMobile(mobile);
    }

    public CodeMsg login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();

        MiaoshaUser user = getMiaoshaUserByMobile(Long.parseLong(mobile));
        if (user == null) {
            return CodeMsg.MOBLE_NOT_EXIST;
        }

        //数据库密码
        String passDB = user.getPassword();
        String saltDB = user.getSalt();

        //客户端加密密码 ---> 数据库密码
        String formPassToDB = MD5Util.formPassToDBPass(formPass, saltDB);

        if (!formPassToDB.equals(passDB)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
