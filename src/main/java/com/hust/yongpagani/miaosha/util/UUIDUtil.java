package com.hust.yongpagani.miaosha.util;

import java.util.UUID;

/**
 * @author Created by Divo
 * @date 2020/11/2
 * Description:UUID生成器
 */
public class UUIDUtil {
    public static String gernateUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
