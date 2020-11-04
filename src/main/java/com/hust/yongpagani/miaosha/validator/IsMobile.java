package com.hust.yongpagani.miaosha.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Created by Divo
 * @date 2020/11/2
 * Description:是否是mobile的注解
 * 直接从NotNULL那边借鉴过来
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)
public @interface IsMobile {

    boolean required() default true; //默认需要

    String message() default "手机号码格式有误";  //抛出的就是Bind_Exception

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
