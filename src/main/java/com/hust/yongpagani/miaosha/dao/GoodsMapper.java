package com.hust.yongpagani.miaosha.dao;

import com.hust.yongpagani.miaosha.domain.MiaoshaGoods;
import com.hust.yongpagani.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author Created by Divo
 * @date 2020/10/30
 */
@Repository
@Mapper
public interface GoodsMapper {

    @Select("select g.* ," +
            "mg.stock_count, mg.start_date, mg.end_date, mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    List<GoodsVo> listGoodsVo();

    //    @Select("select g.* ," +
//            "mg.stock_count, mg.start_date, mg.end_date, mg.miaosha_price " +
//            "from miaosha_goods mg left join goods g on mg.goods_id = g.id" +
//            "where g.id = #{goodsId}")
    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);


    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    int reduceStock(MiaoshaGoods g);

}
