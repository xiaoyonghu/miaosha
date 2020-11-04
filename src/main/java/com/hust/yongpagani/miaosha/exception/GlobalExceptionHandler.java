package com.hust.yongpagani.miaosha.exception;

import com.hust.yongpagani.miaosha.result.CodeMsg;
import com.hust.yongpagani.miaosha.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Created by Divo
 * @date 2020/11/2
 * Description:全局的异常处理器
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest req, Exception e) {

        //自定义全局异常
        if (e instanceof GlobalException) {
            GlobalException ge = (GlobalException) e;
            CodeMsg cm = ge.getCm();
            return Result.error(cm);
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> allErrors = ex.getAllErrors(); //可能有很多异常
            ObjectError firstError = allErrors.get(0);  //得到第一个异常
            String defaultMessage = firstError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(defaultMessage));
        } else {
            //别的异常
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
