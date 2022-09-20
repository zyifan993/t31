package com.zyf.t31.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author zhengweimin
 */
public class NumberUtils {
    private static String[] hanArr = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static String[] unitArr = {"十", "百", "千", "万", "十", "白", "千", "亿", "十", "百", "千"};

    /**
     * String转成int的值， 若无法转换，默认返回0
     */
    public static int stoi(String string) {
        return stoi(string, 0);
    }

    public static int stoi(String string, int defaultValue) {
        if ((string == null) || (string.equalsIgnoreCase("") || (string.equals("null")))) {
            return defaultValue;
        }
        int id;
        try {
            id = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }
        return id;
    }

    /**
     * String转成long的值， 若无法转换，默认返回0
     */
    public static long stol(String string) {
        return stol(string, 0);
    }

    public static long stol(String string, long defaultValue) {
        if ((string == null) || (string.equalsIgnoreCase(""))) {
            return defaultValue;
        }
        long ret;
        try {
            ret = Long.parseLong(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }

        return ret;
    }

    /**
     * String转成double的值， 若无法转换，默认返回0.00
     */
    public static double stod(String string) {
        return stod(string, 0.00);
    }

    public static double stod(String string, double defaultValue) {
        if ((string == null) || (string.equalsIgnoreCase(""))) {
            return defaultValue;
        }
        double ret;
        try {
            ret = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return defaultValue;
        }

        return ret;
    }

    /**
     * 将整数转成中文表示
     */
    public static String toChineseNum(int number) {
        String numStr = String.valueOf(number);
        String result = "";
        int numLen = numStr.length();
        for (int i = 0; i < numLen; i++) {
            int num = numStr.charAt(i) - 48;
            if (i != numLen - 1 && num != 0) {
                result += hanArr[num] + unitArr[numLen - 2 - i];
                if (number >= 10 && number < 20) {
                    result = result.substring(1);
                }
            } else {
                if (!(number >= 10 && number % 10 == 0)) {
                    result += hanArr[num];
                }
            }
        }
        return result;
    }

    /**
     * 获取一个属于[min, max)中的随机数
     */
    public static int random(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }
    public static boolean isInt(Double num) {
        return num.intValue() == num;
    }

    /**
     * 判断字符串是否是数值格式
     * @param str
     * @return
     */
    public static boolean isDigit(String str){
        if(StringUtils.isBlank(str)){
            return false;
        }
        return str.matches("^\\d+$");
    }

    /**
     * 将一个小数精确到指定位数
     * @param num
     * @param scale
     * @return
     */
    public static double scale(double num, int scale) {
        BigDecimal bd = new BigDecimal(num);
        return bd.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 从字符串中根据正则表达式寻找，返回找到的数字数组
     * @param value
     * @param regex
     * @return
     */
    public static Double[] searchNumber(String value, String regex){
        List<Double> doubles = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if(matcher.find()) {
            MatchResult result = matcher.toMatchResult();
            for (int i = 1; i <= result.groupCount(); i++) {
                doubles.add(Double.valueOf(result.group(i)));
            }
        }
        return doubles.toArray(new Double[doubles.size()]);
    }

    /**
     * 生成指定位数的随机数字
     * @param len
     * @return
     */
    public static String generateCode(int len){
        len = Math.min(len, 8);
        int min = Double.valueOf(Math.pow(10, len - 1)).intValue();
        int num = new Random().nextInt(Double.valueOf(Math.pow(10, len + 1)).intValue() - 1) + min;
        return String.valueOf(num).substring(0,len);
    }

}
