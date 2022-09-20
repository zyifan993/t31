package com.zyf.t31.core.exception.enums;

/**
 * @author zhengweimin
 */

public enum ResponseEnum {
    USER_REGISTER_ERROR("A0111","user is already registered","用户已注册"),
    LOGIN_ERROR("A0210","wrong user name and password","用户名密码错误"),
    RESPONSE_TIMEOUT("B0100","system execution timeout","系统执行超时"),
    THIRD_PARTY_SERVICE_ERROR("C0001","Error calling third party service ","调用第三方服务出错"),
    QUERY_PARAMETER_ERROR("D0001","query parameter error","查询参数错误")
    ;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 用户提示
     */
    private String userTips;

    ResponseEnum(String code, String errorMessage, String userTips){
        this.code = code;
        this.errorMessage = errorMessage;
        this.userTips = userTips;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUserTips() {
        return userTips;
    }
}
