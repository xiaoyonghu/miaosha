package com.hust.yongpagani.miaosha.vo;


import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import com.hust.yongpagani.miaosha.validator.IsMobile;

/**
 * @author Created by Divo
 * @date 2020/10/31
 *  存储登录过程中产生的VO
 */
public class LoginVo {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
