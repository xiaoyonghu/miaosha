package com.hust.yongpagani.miaosha.service;


import com.hust.yongpagani.miaosha.dao.OrderMapper;
import com.hust.yongpagani.miaosha.domain.MiaoshaOrder;
import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.domain.OrderInfo;
import com.hust.yongpagani.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * @author Created by Divo
 * @date 2020/10/31
 */

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Long userId, long goodsId) {
        return orderMapper.getMiaoshaOrderByUserIdGoodsId(userId,goodsId);
    }

    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1); // android方式下单
        orderInfo.setStatus(0); //未支付
        orderInfo.setUserId(user.getId());
        long orderId = orderMapper.insertOrderInfo(orderInfo);

        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        orderMapper.insertMiaoshaOrder(miaoshaOrder);

        //TODO 这里有点小问题 为什么order是3，我连续插入2次失败后
        //miaoshaorder中的 orderId 为1 ，而orderinfo中的id为3，我吐了

        return orderInfo;
    }
}
