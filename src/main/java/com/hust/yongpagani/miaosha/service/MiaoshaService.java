package com.hust.yongpagani.miaosha.service;

import com.hust.yongpagani.miaosha.dao.GoodsMapper;
import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.domain.OrderInfo;
import com.hust.yongpagani.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Created by Divo
 * @date 2020/10/31
 */

@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    /***
     * 需要进行原子操作
     * @param user
     * @param goods
     * @return
     */
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存、下订单、写入秒杀订单
        goodsService.reduceStock(goods);

        //order_info miaosha_order
        OrderInfo orderInfo = orderService.createOrder(user, goods);

        return orderInfo;
    }
}
