package com.hust.yongpagani.miaosha.config;

import com.hust.yongpagani.miaosha.domain.MiaoshaUser;
import com.hust.yongpagani.miaosha.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Created by Divo
 * @date 2020/11/5
 * Description: 把GoodsController里面判断的逻辑都提取出来
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    public static Logger log = LoggerFactory.getLogger(UserArgumentResolver.class);

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        //Boolean ok = (clazz == MiaoshaUser.class);
        //System.out.println("UserArgumentResolver层中的boolean方法是否执行"+ ok);
        return clazz == MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);  //心累，这里敲错代码了，调试了半天也不知道哪里出现问题了，那边也一直不显示出了什么问题

        String paramToken = request.getParameter(MiaoshaUserService.COOKIE_NAME_TOKEN);
        String cookieToken = getCookieValue(request, MiaoshaUserService.COOKIE_NAME_TOKEN);

        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        MiaoshaUser user = miaoshaUserService.getByToken(response, token);
        //log.info("UserArgumentResolver层拿到的User信息：" + user.toString());
        return user;
    }

    private String getCookieValue(HttpServletRequest request, String cookieNameToken) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieNameToken)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
