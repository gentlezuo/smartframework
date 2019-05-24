package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 操作日期的工具类
 */
public class DateUtil {
    private static final Logger logger= LoggerFactory.getLogger(DateUtil.class);
    private static final SimpleDateFormat datetimeFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");

    /**
     * 根据时间戳格式化时与时间
     * @param timestamp
     * @return
     */
    public static String formatDatetime(long timestamp){
        return datetimeFormat.format(new Date(timestamp));
    }

    public static String formatDate(long timestamp){
        return dateFormat.format(new Date(timestamp));
    }

    public static String formatTime(long timestamp){
        return timeFormat.format(new Date(timestamp));
    }

    /**
     * 获取当前日期与时间
     * @return
     */
    public static String getCurrentDatetime(){
        return datetimeFormat.format(new Date());
    }

    public static String getCurrentDate(){
        return dateFormat.format(new Date());
    }

    public static String getCurrentTime(){
        return timeFormat.format(new Date());
    }

    /**
     * 根据字符串解析出Date
     * @param str
     * @return
     */
    public static Date parseDatetime(String str){
        Date date=null;
        try {
            date=datetimeFormat.parse(str);
        }catch (ParseException e){
            logger.error("解析字符串出错！格式：yyyy-MM-dd HH:mm:ss",e);

        }
        return date;
    }

    public static Date parseDate(String str) {
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期字符串出错！格式：yyyy-MM-dd", e);
        }
        return date;
    }

    public static Date parseTime(String str) {
        Date date = null;
        try {
            date = timeFormat.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期字符串出错！格式：HH:mm:ss", e);
        }
        return date;
    }

}
