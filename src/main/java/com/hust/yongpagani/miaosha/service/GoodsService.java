package com.hust.yongpagani.miaosha.service;

import com.hust.yongpagani.miaosha.dao.GoodsMapper;
import com.hust.yongpagani.miaosha.dao.MiaoshaUserMapper;
import com.hust.yongpagani.miaosha.domain.MiaoshaGoods;
import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.exception.GlobalException;
import com.hust.yongpagani.miaosha.result.CodeMsg;
import com.hust.yongpagani.miaosha.util.MD5Util;
import com.hust.yongpagani.miaosha.util.MiaoshaUserKey;
import com.hust.yongpagani.miaosha.util.UUIDUtil;
import com.hust.yongpagani.miaosha.vo.GoodsVo;
import com.hust.yongpagani.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Created by Divo
 * @date 2020/10/31
 */

@Service
public class GoodsService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    GoodsMapper goodsMapper;

    public List<GoodsVo> goodsVoList() {
        return goodsMapper.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsMapper.reduceStock(g);
    }
}
