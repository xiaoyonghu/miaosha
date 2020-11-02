package com.hust.yongpagani.miaosha.service;

import com.alibaba.fastjson.JSON;
import com.hust.yongpagani.miaosha.util.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author Created by Divo
 * @date 2020/10/30
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取当个对象
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        //生成真正的key
        String realKey = prefix.getPrefix() + key;
        String str = (String) redisTemplate.opsForValue().get(realKey);
        T t = stringToBean(str, clazz);
        return t;
    }

    /**
     * 判断是否存在
     * @param prefix
     * @param key
     * @return
     */
    public boolean exists(KeyPrefix prefix, String key){
        String realKey = prefix.getPrefix() + key;
        String s = (String) redisTemplate.opsForValue().get(realKey);
        if (StringUtils.isEmpty(s)){
            return false;
        }
        return true;
    }

    /**
     * 增加
     * @param prefix
     * @param key
     * @return
     */
    public Long incr(KeyPrefix prefix, String key){
        String realKey = prefix.getPrefix() + key;
        return redisTemplate.opsForValue().increment(realKey);
    }

    /**
     * 减少
     * @param prefix
     * @param key
     * @return
     */
    public Long decr(KeyPrefix prefix, String key){
        String realKey = prefix.getPrefix() + key;
        return redisTemplate.opsForValue().decrement(realKey);
    }

    /**
     * 设置过期时间
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        String str = beanToString(value);

        if (str == null || str.length() <= 0) {
            return false;
        }
        String realKey = prefix.getPrefix() + key;

        int seconds = prefix.expireSeconds();
        if (seconds <= 0) {
            redisTemplate.opsForValue().set(realKey, str);
        } else {
            redisTemplate.opsForValue().set(realKey, str, seconds, TimeUnit.SECONDS);
        }
        return true;
    }


    /**
     * 把str装换为对应的实体类，其实可以使用BeanUtils来进行转换
     *
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str.length() == 0 || str == null || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }
    
    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;   //int --> string 只需要在前面加上""即可
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }


}
