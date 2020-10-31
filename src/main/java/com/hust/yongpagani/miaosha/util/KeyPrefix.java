package com.hust.yongpagani.miaosha.util;

/**
 * @author Created by Divo
 * @date 2020/10/30
 *  redis的前缀
 */
public interface KeyPrefix {
     int expireSeconds();
     String getPrefix();
}
