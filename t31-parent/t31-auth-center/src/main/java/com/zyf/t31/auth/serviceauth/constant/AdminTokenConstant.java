package com.zyf.t31.auth.serviceauth.constant;

/**
 * @author zhengweimin
 */
public class AdminTokenConstant {
    public static final String KEY_LOCATION = "kaikeba.jks";

    public static final String KEYSTORE_PASSWORD = "kaikeba";

    /**
     * 密钥的密码，此密码和别名要匹配
     */
    public static final String KEY_PASSWORD = "kaikeba";

    /**
     * 密钥别名
     */
    public static final String ALIAS = "kaikeba";

    public static final String USER_NAME_KEY = "user_name";

    public static final String USER_NAME_VALUE = "admin";

    public static final String CLIENT_ID_KEY = "client_id";

    public static final String CLIENT_ID_VALUE =  "client";

    public static final String AUTHORITIES_KEY = "authorities";

    public static final String AUTHORITIES_VALUE_ROLE_ADMIN = "ROLE_ADMIN";


}
