package com.hust.yongpagani.miaosha.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Created by Divo
 * @date 2020/11/1
 * Description:用来校验的工具
 */
public class ValidatorUtil {

    public static final Pattern moble_pattern = Pattern.compile("1\\d{10}"); //定义匹配的模式

    /**
     * 手机号是否匹配
     * @param mobileStr
     * @return
     */
    public static boolean isMobile(String mobileStr){
        if (StringUtils.isEmpty(mobileStr)){
            return false;
        }
        Matcher matcher = moble_pattern.matcher(mobileStr);
        return matcher.matches();
    }

//    public static void main(String[] args) {
//        String mobile1 = "12345678910";
//        String mobile2 = "1234567891";
//
//        System.out.println(isMobile(mobile1));
//        System.out.println(isMobile(mobile2));
//    }
}
