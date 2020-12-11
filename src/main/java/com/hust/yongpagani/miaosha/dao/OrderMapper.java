package com.hust.yongpagani.miaosha.dao;

import com.hust.yongpagani.miaosha.domain.MiaoshaOrder;
import com.hust.yongpagani.miaosha.domain.OrderInfo;
import com.hust.yongpagani.miaosha.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * @author Created by Divo
 * @date 2020/10/30
 */
@Mapper
@Repository
public interface OrderMapper {

    @Select("select* from miaosha_order where user_id = #{userId} and goods_id = #{goodsId}")
    MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") Long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id,goods_id,goods_name,goods_count,goods_price,order_channel,status,create_date) values(" +
            "#{userId},#{goodsId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    long insertOrderInfo(OrderInfo orderInfo);

    @Insert("insert into miaosha_order(user_id,goods_id,order_id) values(" +
            "#{userId},#{goodsId},#{orderId})")
    int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
