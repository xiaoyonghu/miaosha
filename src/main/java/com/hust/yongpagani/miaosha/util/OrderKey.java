package com.hust.yongpagani.miaosha.util;

/**
 * @author Created by Divo
 * @date 2020/10/30
 */
public class OrderKey extends BasePrefix{
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
