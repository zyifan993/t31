package com.zyf.t31.core.utils;

import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * @author 雪松
 * @date 2021/10/28 11:47
 */
public class DateUtil {
    /**
     * 自定义格式化
     */
    public static final String YEAR_MONTH_FORMATTER = "yyyy-MM";
    public static final String DATE_FORMATTER = "yyyy-MM-dd";
    public static final String DATETIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMATTER = "HH:mm:ss";
    public static final String YEAR_MONTH_FORMATTER_SHORT = "yyyyMM";
    public static final String DATE_FORMATTER_SHORT = "yyyyMMdd";
    public static final String DATETIME_FORMATTER_SHORT = "yyyyMMddHHmmss";
    public static final String TIME_FORMATTER_SHORT = "HHmmss";
    /**
     * 24小时时间正则表达式
     */
    public static final String MATCH_TIME_24 = "(([0-1][0-9])|2[0-3]):[0-5][0-9]:[0-5][0-9]";
    /**
     * 日期正则表达式
     */
    public static final String REGEX_DATA = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";


    public static final String PATTERN_YYYYMMDDHH = "yyyyMMddHH";
    public static final String PATTERN_YYYY_MM_DDHHMM = "yyyy-MM-dd HH:mm";

    /***
     * 从yyyy-MM-dd HH:mm格式转成yyyyMMddHH格式
     * @param dateStr
     * @return
     */
    public static String formatStr(String dateStr,String opattern,String npattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(opattern);
        try {
            Date date = simpleDateFormat.parse(dateStr);
            simpleDateFormat = new SimpleDateFormat(npattern);
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 获取指定日期的凌晨
     * @return
     */
    public static Date toDayStartHour(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date start = calendar.getTime();
        return start;
    }


    /***
     * 时间增加N分钟
     * @param date
     * @param minutes
     * @return
     */
    public static Date addDateMinutes(Date date,int minutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 24小时制
        calendar.add(Calendar.MINUTE, minutes);
        date = calendar.getTime();
        return date;
    }

    /***
     * 时间递增N小时
     * @param hour
     * @return
     */
    public static Date addDateHour(Date date,int hour){//Jota-time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 24小时制
        calendar.add(Calendar.HOUR, hour);
        date = calendar.getTime();
        return date;
    }

    /***
     * 获取时间菜单
     * @return
     */
    public static List<Date> getDateMenus(){
        // 定义一个List<Date>集合，存储所有时间段
        List<Date> dates = getDates(12);
        // 判断当前时间属于哪个时间范围
        Date now = new Date();
        for (Date cdate : dates) {
            // 开始时间<=当前时间<开始时间+2小时
            if(cdate.getTime()<=now.getTime() && now.getTime()<addDateHour(cdate,2).getTime()){
                now = cdate;
                break;
            }
        }

        // 当前需要显示的时间菜单
        List<Date> dateMenus = new ArrayList<Date>();
        for (int i = 0; i <5 ; i++) {
            dateMenus.add(addDateHour(now,i*2));
        }
        return dateMenus;
    }

    /***
     * 指定时间往后N个时间间隔
     * @param hours
     * @return
     */
    public static List<Date> getDates(int hours) {
        List<Date> dates = new ArrayList<Date>();

        // 凌晨
        Date date = toDayStartHour(new Date());
        // 循环12次
        for (int i = 0; i <hours ; i++) {
            // 每次递增2小时,将每次递增的时间存入到List<Date>集合中
            dates.add(addDateHour(date,i*2));
        }
        return dates;
    }

    /***
     * 时间转成yyyyMMddHH
     * @param date
     * @param pattern
     * @return
     */
    public static String data2str(Date date, String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 计算当期时间相差的日期
     *
     * @param field  日历字段.<br/>eg:Calendar.MONTH,Calendar.DAY_OF_MONTH,<br/>Calendar.HOUR_OF_DAY等.
     * @param amount 相差的数值
     * @return 计算后的日志
     */
    public static Date addDate(int field, int amount) {
        return addDate(null, field, amount);
    }

    /**
     * 计算当期时间相差的日期
     *
     * @param date   设置时间
     * @param field  日历字段.<br/>eg:Calendar.MONTH,Calendar.DAY_OF_MONTH,<br/>Calendar.HOUR_OF_DAY等.
     * @param amount 相差的数值
     * @return 计算后的日志
     */
    public static Date addDate(Date date, int field, int amount) {
        if (amount == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * @param date    时间。若为空，则返回空串
     * @param pattern 时间格式化
     * @return 格式化后的时间字符串.
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        // TODO ，后面改成缓存
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取指定天结束时间
     *
     * @param date 日期
     * @return 获得该日期的开始
     */
    public static Date getDayBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setCalender(calendar, 0, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天开始时间
     *
     * @return 获得该日期的开始
     */
    public static Date getDayBegin() {
        return getDayBegin(new Date());
    }

    /**
     * 获取指定天结束时间
     *
     * @param date 日期
     * @return 获得该日期的结束
     */
    public static Date getDayEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setCalender(calendar, 23, 59, 59, 999);
        return calendar.getTime();
    }

    /**
     * 获取当天结束时间
     *
     * @return 获得该日期的开始
     */
    public static Date getDayEnd() {
        return getDayEnd(new Date());
    }

    /**
     * 设置Calendar的小时、分钟、秒、毫秒
     *
     * @param calendar    日历
     * @param hour        小时
     * @param minute      分钟
     * @param second      秒
     * @param milliSecond 毫秒
     */
    public static void setCalender(Calendar calendar, int hour, int minute, int second, int milliSecond) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, milliSecond);
    }

    /**
     * 判断当前时间，是否在该时间范围内
     *
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 是否在
     */
    public static boolean isBetween(Date beginTime, Date endTime) {
        Assert.notNull(beginTime, "开始时间不能为空");
        Assert.notNull(endTime, "结束时间不能为空");
        Date now = new Date();
        return beginTime.getTime() <= now.getTime()
                && now.getTime() <= endTime.getTime();
    }

    public static Date max(Date a, Date b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return a.compareTo(b) > 0 ? a : b;
    }
    /**
     * 获取当前时间戳
     *
     * @return timestamp
     */
    public static long getTimestamp() {
        // LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        // LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        // Timestamp.valueOf(LocalDateTime.now()).getTime();
        // Instant.now().toEpochMilli();
        return Instant.now().toEpochMilli();
    }

    /**
     * 获取当前年份
     *
     * @return yyyy
     */
    public static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    /**
     * 获取当前年月
     *
     * @return yyyy-MM
     */
    public static String getCurrentYearMonth() {
        return getCurrentDate(YEAR_MONTH_FORMATTER);
    }

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return getCurrentDate(DATE_FORMATTER);
    }

    /**
     * 获取下一天日期
     *
     * @return yyyy-MM-dd
     */
    public static String getNextDate() {
        return getNextDate(DATE_FORMATTER);
    }

    /**
     * 获取当前时间
     *
     * @return HHmmss
     */
    public static String getCurrentTime() {
        return getCurrentTime(TIME_FORMATTER);
    }

    /**
     * 获取当前日期时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime() {
        return getCurrentDateTime(DATETIME_FORMATTER);
    }

    /**
     * 获取当前年月
     *
     * @return yyyyMM
     */
    public static String getCurrentYearMonthShort() {
        return getCurrentDate(YEAR_MONTH_FORMATTER_SHORT);
    }

    /**
     * 获取当前日期
     *
     * @return yyyyMMdd
     */
    public static String getCurrentDateShort() {
        return getCurrentDate(DATE_FORMATTER_SHORT);
    }

    /**
     * 获取下一天日期
     *
     * @return yyyy-MM-dd
     */
    public static String getNextDateShort() {
        return getNextDate(DATE_FORMATTER_SHORT);
    }

    /**
     * 获取当前时间
     *
     * @return HHmmss
     */
    public static String getCurrentTimeShort() {
        return getCurrentTime(TIME_FORMATTER_SHORT);
    }

    /**
     * 获取当前日期时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String getCurrentDateTimeShort() {
        return getCurrentDateTime(DATETIME_FORMATTER_SHORT);
    }

    /**
     * 获取当前日期
     *
     * @param pattern 格式化
     */
    public static String getCurrentDate(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取下一天日期
     *
     * @param pattern 格式化
     */
    public static String getNextDate(String pattern) {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间
     *
     * @param pattern 格式化
     */
    public static String getCurrentTime(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前日期时间
     *
     * @param pattern 格式化
     */
    public static String getCurrentDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间戳转日期
     *
     * @param timestamp 时间戳
     */
    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 日期转时间戳
     *
     * @param localDateTime 日期
     */
    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 时间戳转日期时间
     *
     * @param timestamp 时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatTimestamp(long timestamp) {
        return formatTimestamp(timestamp, DATETIME_FORMATTER);
    }

    /**
     * 时间戳转日期时间
     *
     * @param timestamp 时间戳
     * @return yyyyMMddHHmmss
     */
    public static String formatTimestampShort(long timestamp) {
        return formatTimestamp(timestamp, DATETIME_FORMATTER_SHORT);
    }

    /**
     * 时间戳转日期
     *
     * @param timestamp 时间戳
     * @param pattern   格式化
     */
    public static String formatTimestamp(long timestamp, String pattern) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return formatLocalDateTime(localDateTime, pattern);
    }

    /**
     * 日期格式化字符串
     *
     * @param localDate 日期
     * @return yyyy-MM-dd
     */
    public static String formatLocalDate(LocalDate localDate) {
        return formatLocalDate(localDate, DATE_FORMATTER);
    }

    /**
     * 日期格式化字符串
     *
     * @param localDate 日期
     * @return yyyyMMdd
     */
    public static String formatLocalDateShort(LocalDate localDate) {
        return formatLocalDate(localDate, DATE_FORMATTER_SHORT);
    }

    /**
     * 时间格式化字符串
     *
     * @param localTime 时间
     * @return HH:mm:ss
     */
    public static String formatLocalTime(LocalTime localTime) {
        return formatLocalTime(localTime, TIME_FORMATTER);
    }

    /**
     * 时间格式化字符串
     *
     * @param localTime 时间
     * @return HHmmss
     */
    public static String formatLocalTimeShort(LocalTime localTime) {
        return formatLocalTime(localTime, TIME_FORMATTER_SHORT);
    }

    /**
     * 日期时间格式化字符串
     *
     * @param localDateTime 日期时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, DATETIME_FORMATTER);
    }

    /**
     * 日期时间格式化字符串
     *
     * @param localDateTime 日期时间
     * @return yyyyMMddHHmmss
     */
    public static String formatLocalDateTimeShort(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, DATETIME_FORMATTER_SHORT);
    }

    /**
     * 日期格式化字符串
     *
     * @param localDate 日期
     * @param pattern   格式化
     */
    public static String formatLocalDate(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间格式化字符串
     *
     * @param localTime 时间
     * @param pattern   格式化
     */
    public static String formatLocalTime(LocalTime localTime, String pattern) {
        return localTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期时间格式化字符串
     *
     * @param localDateTime 日期时间
     * @param pattern       格式化
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期字符串转日期
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate parseLocalDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间字符串转时间
     *
     * @param time    时间字符串
     * @param pattern 格式化
     */
    public static LocalTime parseLocalTime(String time, String pattern) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期时间字符串转日期时间
     *
     * @param dateTime 日期时间字符串
     * @param pattern  格式化
     */
    public static LocalDateTime parseLocalDateTime(String dateTime, String pattern) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取本周第一天
     */
    public static LocalDate getCurrentWeekFirstDate() {
        return LocalDate.now().minusWeeks(0).with(DayOfWeek.MONDAY);
    }

    /**
     * 获取本周最后一天
     */
    public static LocalDate getCurrentWeekLastDate() {
        return LocalDate.now().minusWeeks(0).with(DayOfWeek.SUNDAY);
    }

    /**
     * 获取本月第一天
     */
    public static LocalDate getCurrentMonthFirstDate() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取本月最后一天
     */
    public static LocalDate getCurrentMonthLastDate() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取指定周第一天
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate getWeekFirstDate(String date, String pattern) {
        return parseLocalDate(date, pattern).minusWeeks(0).with(DayOfWeek.MONDAY);
    }

    /**
     * 获取指定周第一天
     *
     * @param localDate 日期
     */
    public static LocalDate getWeekFirstDate(LocalDate localDate) {
        return localDate.minusWeeks(0).with(DayOfWeek.MONDAY);
    }

    /**
     * 获取指定周最后一天
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate getWeekLastDate(String date, String pattern) {
        return parseLocalDate(date, pattern).minusWeeks(0).with(DayOfWeek.SUNDAY);
    }

    /**
     * 获取指定周最后一天
     *
     * @param localDate 日期
     */
    public static LocalDate getWeekLastDate(LocalDate localDate) {
        return localDate.minusWeeks(0).with(DayOfWeek.SUNDAY);
    }


    /**
     * 获取指定月份月第一天
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate getMonthFirstDate(String date, String pattern) {
        return parseLocalDate(date, pattern).with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定月份月第一天
     *
     * @param localDate
     */
    public static LocalDate getMonthFirstDate(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取当前星期
     *
     * @return 1:星期一、2:星期二、3:星期三、4:星期四、5:星期五、6:星期六、7:星期日
     */
    public static int getCurrentWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }


    /**
     * 获取当前星期
     *
     * @param localDate 日期
     */
    public static int getWeek(LocalDate localDate) {
        return localDate.getDayOfWeek().getValue();
    }

    /**
     * 获取指定日期的星期
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static int getWeek(String date, String pattern) {
        return parseLocalDate(date, pattern).getDayOfWeek().getValue();
    }

    /**
     * 日期相隔天数
     *
     * @param startLocalDate 起日期
     * @param endLocalDate   止日期
     */
    public static long intervalDays(LocalDate startLocalDate, LocalDate endLocalDate) {
        return endLocalDate.toEpochDay() - startLocalDate.toEpochDay();
    }

    /**
     * 日期相隔小时
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalHours(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toHours();
    }

    /**
     * 日期相隔分钟
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalMinutes(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toMinutes();
    }

    /**
     * 日期相隔毫秒数
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalMillis(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toMillis();
    }

    /**
     * 当前是否闰年
     */
    public static boolean isCurrentLeapYear() {
        return LocalDate.now().isLeapYear();
    }

    /**
     * 是否闰年
     */
    public static boolean isLeapYear(LocalDate localDate) {
        return localDate.isLeapYear();
    }

    /**
     * 是否当天
     *
     * @param localDate 日期
     */
    public static boolean isToday(LocalDate localDate) {
        return LocalDate.now().equals(localDate);
    }

    /**
     * 获取此日期时间与默认时区<Asia/Shanghai>组合的时间毫秒数
     *
     * @param localDateTime 日期时间
     */
    public static Long toEpochMilli(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取此日期时间与指定时区组合的时间毫秒数
     *
     * @param localDateTime 日期时间
     */
    public static Long toSelectEpochMilli(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * 计算距今天指定天数的日期
     *
     * @param days
     * @return
     */
    public static String getDateAfterDays(int days) {
        Calendar date = Calendar.getInstance();// today
        date.add(Calendar.DATE, days);
        SimpleDateFormat simpleDate = new SimpleDateFormat(DATE_FORMATTER);
        return simpleDate.format(date.getTime());
    }

    /**
     * 在指定的日期的前几天或后几天
     *
     * @param source 源日期(yyyy-MM-dd)
     * @param days   指定的天数,正负皆可
     * @return
     * @throws ParseException
     */
    public static String addDays(String source, int days) {
        Date date = localDateToDate(parseLocalDate(source, DATE_FORMATTER));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATTER);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * LocalDate转换成Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 24小时时间校验
     *
     * @param time
     * @return
     */
    public static boolean isValidate24(String time) {
        Pattern p = Pattern.compile(MATCH_TIME_24);
        return p.matcher(time).matches();
    }

    /**
     * 日期校验
     *
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        Pattern pat = Pattern.compile(REGEX_DATA);
        Matcher mat = pat.matcher(date);
        return mat.matches();
    }

    /**
     * 判断当前时间是否在指定时间范围
     *
     * @param from 开始时间
     * @param to   结束时间
     * @return 结果
     */
    public static boolean between(LocalTime from, LocalTime to) {
        LocalTime now = LocalTime.now();
        return now.isAfter(from) && now.isBefore(to);
    }
    public static void main(String[] args) {
        getDateMenus().forEach( d -> System.out.println(d));
    }

}