package com.zhouwenguang.timeselector.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date parse(String strDate, String pattern) {
        if (TextUtils.isEmpty(strDate)) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     *  yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     *  yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String formatDateTime(long time) {
        return format(new Date(time), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回时间yy-MM-dd HH:mm:ss格式
     * @return
     */
    public static String formatDate(){
        return format(new Date(),"yy-MM-dd HH:mm:ss");
    }

    /**
     * 返回 yyyy-MM-dd格式日期时间
     */
    public static String formatDateWithoutTime(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String formatTrackQueryTime(String s) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMddhhmmss").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return DateUtil.format(date,"yyyy-MM-dd HH:mm:ss");
    }
//    /**
//     * 获取毫秒数
//     * @param dateTime (格式：yyyy-MM-dd HH:mm:ss)
//     * @return
//     */
    public static String getLongTimeByYearMonthDay(String dateTime){
        String time = "";
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        try {
            time = sDateFormat.parse(dateTime).getTime()+ "";
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return time;
    }

//    /**
//     * 获取时间差
//     */
    public static boolean isNeedFixTime(String serverTime, long resultTime){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String now = DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        long nowTime = 0;
        long fixTime = 0;
        try {
            nowTime = sDateFormat.parse(now).getTime();
            fixTime = sDateFormat.parse(serverTime).getTime();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Math.abs(fixTime - nowTime) > resultTime;
    }

}
