package com.zyf.t31.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RexUtil {

    /**
     * @param rex 正则校验规则
     * @param str 要校验的字符串
     * @return 返回校验结果，若满足校验规则，则返回true，否则返回false
     */
    public static Boolean match(String rex, String str) {
        // TODO Auto-generated method stub
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
