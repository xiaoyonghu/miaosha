package com.hust.yongpagani.miaosha.service;

import com.hust.yongpagani.miaosha.util.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Created by Divo
 * @date 2020/10/30
 */
@Service
public class redisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取当个对象
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        //生成真正的key
        String realKey = prefix.getPrefix() + key;
        String str = (String) redisTemplate.opsForValue().get(realKey);
        //T t = stringToBean(str, clazz);
        return t;
    }

}
