package com.zyf.t31.core.exception;

import com.zyf.t31.core.vo.ResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhengweimin
 */
public class T31ExceptionHandler {

    @ExceptionHandler(value = T31SystemException.class)
    @ResponseBody
    public ResponseBean t31SystemException(T31SystemException exception) {
        return exception.getResponseBean();
    }
}
