package com.example.administrator.mysharedumbrella01.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/*
* 获取当前系统·时间的 工具类
*
* */

@SuppressLint("SimpleDateFormat")
public class DateUtil {


    public static String getCurrentDatatime() {
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        return format;
    }
    public static String getCurrentTimeYearMothDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        return format;
    }
    /*
    * 获取秒
    * */
    public static String getCurrentTimeMillis() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);

    }

    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;

    }

    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);

    }

    public static String getMonth(Date date, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM", locale);
        return sdf.format(date);

    }

    public static int getMonthWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_MONTH);

    }

    public static int getYearWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static String toDateYYYYMMDD(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static String toDateYYYYMM(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        return df.format(date);
    }

    public static Date dateAddDay(Date date, int addDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, addDay);
        return c.getTime();
    }

    public static Date dateAddMonth(Date date, int addMonth) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, addMonth);
        return c.getTime();
    }

    public static int getCurrentMinutes() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY) * 60 + c.get(Calendar.MINUTE);
    }

    /*
    * 获取当前系统时间戳
    * */
    public static long getCurrentTimeChuo() {
        long shijianchuo =  System.currentTimeMillis() / 1000;
        return shijianchuo;
    }


    /*
    * 将时间戳转成时间
    * */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt *1000);
        res = simpleDateFormat.format(date);
        return res;
    }


}
