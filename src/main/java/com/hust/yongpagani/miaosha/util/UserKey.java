package com.hust.yongpagani.miaosha.util;

/**
 * @author Created by Divo
 * @date 2020/10/30
 */
public class UserKey extends BasePrefix{
    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

//    public static UserKey getById = new UserKey("id");
//    public static UserKey getByName = new UserKey("name");
}
