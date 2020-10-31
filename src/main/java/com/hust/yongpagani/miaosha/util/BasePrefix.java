package com.hust.yongpagani.miaosha.util;

/**
 * @author Created by Divo
 * @date 2020/10/30
 */
public class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String classname = getClass().getSimpleName(); //得到方法名
        return classname + ":" + prefix;
    }
}
