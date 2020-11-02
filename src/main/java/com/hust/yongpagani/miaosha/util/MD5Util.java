package com.hust.yongpagani.miaosha.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @author Created by Divo
 * @date 2020/10/31
 * Description: 使用其来对输入的密码进行加密
 */
public class MD5Util {

    public static String md5(String input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    private static final String salt = "1a2b3c4d";  //前端写死的盐

    /**
     * 输入密码转换为表单密文
     * @param inputPass
     * @return
     */
    public static String inputPassToFromPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 表单密文转为数据库密文
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * @param inputPass
     * @param saltDB
     * @return
     */
    public static String inputPassToDBPass(String inputPass, String saltDB) {
        String formPass = inputPassToFromPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        String s1= inputPassToFromPass("282675lovexu");  //ecdeae087bc39978bfc72ce826ff3f60
        System.out.println(s1);

        String ss = formPassToDBPass(s1,"kcjgidkfjgids");
        System.out.println(ss);

        System.out.println(inputPassToDBPass("282675lovexu","kcjgidkfjgids"));  //b98df7e09ccfa3aefbf3553916f92f16
    }

}
