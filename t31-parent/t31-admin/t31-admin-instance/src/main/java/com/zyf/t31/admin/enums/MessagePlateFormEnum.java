package com.zyf.t31.admin.enums;


/**
 * @author zhengweimin
 */

public enum MessagePlateFormEnum {

    // 邮件平台
    MAIL("mail"),
    // 短信平台
    SHORT_MESSAGE("shortMessage");

    /**
     * 平台名称
     */
    private String name;

    MessagePlateFormEnum(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
