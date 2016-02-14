package com.hpdxay.hpd3dmgame.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author PineTree
 * @version 2.0
 *          2014-11-24
 * @Description: long转成时间
 */
public class DateUtil {

    /**
     * @param lo 毫秒数
     * @return String yyyy-MM-dd HH:mm:ss
     * @Description: String类型毫秒数转换成日期
     */
    public static String stringToDate(String lo) {
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    /**
     * @param lo 毫秒数
     * @return String yyyy-MM-dd HH:mm:ss
     * @Description: long类型转换成日期
     */
    public static String longToDate(long lo) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    /**
     * @param lo 日期毫秒数
     * @return String yyyyMMddHHmmss
     * @Description: long类型生成没有符号的日期格式
     */
    public static String getLongToDate(long lo) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        return sd.format(date);
    }

    /**
     * @param lo 日期毫秒数（字符串形式）
     * @return String yyyyMMddHHmmss
     * @Description: String类型生成没有符号的日期格式
     */
    public static String getStringToDate(String lo) {
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        return sd.format(date);
    }

    /**
     * @param lo 日期毫秒数
     * @return String yyyy.MM.dd
     * @Description: long类型转换成点形式的日期格式
     */
    public static String getLongPointDate(long lo) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd");
        return sd.format(date);
    }

    /**
     * @param lo String类型日期毫秒数
     * @return String yyyy.MM.dd
     * @Description: String类型转换成点形式的日期格式
     */
    public static String getStringPointDate(String lo) {
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd");
        return sd.format(date);
    }

    /**
     * @param lo long类型日期好藐视
     * @return String yyyyMMdd
     * @Description: long类型转成日期格式
     */
    public static String getloToDate(long lo) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        return sd.format(date);
    }

    /**
     * @param lo String类型日期好藐视
     * @return String yyyyMMdd
     * @Description: String类型转成日期格式
     */
    public static String getStrToDate(String lo) {
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        return sd.format(date);
    }

    /**
     * @param lo 日期毫秒数
     * @return String yyyy.MM.dd  HH:mm:ss
     * @Description: long类型转换成点形式的日期格式
     */
    public static String longPointDate(long lo) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sd.format(date);
    }

    /**
     * @param lo String类型日期毫秒数
     * @return String yyyy.MM.dd HH:mm:ss
     * @Description: String类型转换成点形式的日期格式
     */
    public static String stringPointDate(String lo) {
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sd.format(date);
    }
}