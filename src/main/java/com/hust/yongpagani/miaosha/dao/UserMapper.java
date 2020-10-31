package com.hust.yongpagani.miaosha.dao;

import com.hust.yongpagani.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author Created by Divo
 * @date 2020/10/30
 */
@Mapper
public interface UserMapper {
    //@Select("select * from user where id =#{id}")
    User getById(@Param("id") int id);

    @Insert("insert into user(id,name) values (#{id},#{name})")
    int insertUser(User user);

}
