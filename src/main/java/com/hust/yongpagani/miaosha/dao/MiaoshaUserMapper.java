package com.hust.yongpagani.miaosha.dao;

import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Created by Divo
 * @date 2020/10/31
 */

@Mapper
@Repository
public interface MiaoshaUserMapper {

    MiaoshaUser getMiaoshaUserByMobile(Long id);

}
