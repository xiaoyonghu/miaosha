package com.hust.yongpagani.miaosha.dao;

import com.hust.yongpagani.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author Created by Divo
 * @date 2020/10/30
 */
@Repository
@Mapper
public interface GoodsMapper {

    @Insert("insert into user(id,name) values (#{id},#{name})")
    int insertUser(User user);


}
