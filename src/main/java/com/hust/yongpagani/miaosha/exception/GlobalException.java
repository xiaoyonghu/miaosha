package com.hust.yongpagani.miaosha.exception;

import com.hust.yongpagani.miaosha.result.CodeMsg;

/**
 * @author Created by Divo
 * @date 2020/11/2
 * Description:全局的异常类
 */
public class GlobalException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
