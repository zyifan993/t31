package com.zyf.t31.core.exception;

import com.zyf.t31.core.exception.enums.ResponseEnum;
import com.zyf.t31.core.vo.ResponseBean;

/**
 * @author zhengweimin
 */
public class T31SystemException extends RuntimeException{

    private ResponseBean responseBean;

    T31SystemException(){

    }

    public T31SystemException(ResponseEnum responseEnum){
        this.responseBean  = new ResponseBean(responseEnum.getCode(), responseEnum.getErrorMessage(), responseEnum.getUserTips());
    }

    public ResponseBean getResponseBean() {
        return responseBean;
    }

    public void setResponseBean(ResponseBean responseBean) {
        this.responseBean = responseBean;
    }
}
