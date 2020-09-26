package cn.alucardlockon.codebox.date;

import cn.alucardlockon.codebox.core.Langs;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dates {

    public enum DateFormat {
        NORMAL_DATE_FORMAT("yyyy-MM-dd"),
        NORMAL_TIME_FORMAT("hh:mm:ss"),
        NORMAL_DATETIME_FORMAT("yyyy-MM-dd hh:mm:ss"),

        SLASH_DATE_FORMAT("yyyy/MM/dd"),
        SLASH_TIME_FORMAT("hh/mm/ss"),
        SLASH_DATETIME_FORMAT("yyyy/MM/dd hh/mm/ss"),
        SLASH_NORMAL_DATETIME_FORMAT("yyyy/MM/dd hh:mm:ss"),

        LESS_DATE_FORMAT("yyyyMMdd"),
        LESS_TIME_FORMAT("hhmmss"),
        LESS_DATETIME_FORMAT("yyyyMMdd hhmmss");

        private final String formatStr;

        DateFormat(String formatStr) {
            this.formatStr = formatStr;
        }

        String getValue() {
            return this.formatStr;
        }
    }

    public enum DateUnit {
        MS(1),
        SECOND(1000),
        MINUTE(SECOND.getValue() * 60),
        HOUR(MINUTE.getValue() * 60),
        AM_PM(HOUR.getValue() * 12),
        DAY(HOUR.getValue() * 24),
        WEEK(DAY.getValue() * 7),
        MONTH(DAY.getValue() * 30),// don't use this value with add
        YEAR(DAY.getValue() * 365); // don't use this value with add

        private final long millis;

        DateUnit(long millis) {
            this.millis = millis;
        }

        long getValue() {
            return this.millis;
        }
    }

    public static Date parse(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parse(String str, DateFormat format) {
        return parse(str, format.getValue());
    }

    public static Date parse(String str) {
        return parse(str, DateFormat.NORMAL_DATE_FORMAT);
    }

    public static String toStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String toStr(Date date, DateFormat format) {
        return toStr(date, format.getValue());
    }

    public static String toStr(Date date) {
        return toStr(date, DateFormat.NORMAL_DATE_FORMAT);
    }

    public static long getTime(String str, String format) {
        Date date = parse(str, format);
        return Langs.isEmpty(date) ? date.getTime() : 0L;
    }

    public static long getTime(String str, DateFormat format) {
        return getTime(str, format.getValue());
    }

    public static long getTime(String str) {
        return getTime(str, DateFormat.NORMAL_DATE_FORMAT);
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }

    public static Date toDate(Calendar calendar) {
        return new Date(calendar.getTimeInMillis());
    }

    public static Date truncate(Date date, DateUnit dateUnit) {
        Calendar calendar = toCalendar(date);
        if (dateUnit.getValue() <= DateUnit.YEAR.getValue()) {
            calendar.set(Calendar.YEAR, 0);
        }
        if (dateUnit.getValue() <= DateUnit.MONTH.getValue()){
            calendar.set(Calendar.MONTH, 0);
        }
        if (dateUnit.getValue() <= DateUnit.WEEK.getValue()){
            calendar.set(Calendar.DAY_OF_WEEK, 1);
        }
        if (dateUnit.getValue() <= DateUnit.DAY.getValue()){
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        }
        if (dateUnit.getValue() <= DateUnit.AM_PM.getValue()){
            calendar.set(Calendar.AM_PM, 0);
        }
        if (dateUnit.getValue() <= DateUnit.HOUR.getValue()){
            calendar.set(Calendar.HOUR, 0);
        }
        if (dateUnit.getValue() <= DateUnit.MINUTE.getValue()){
            calendar.set(Calendar.MINUTE, 0);
        }
        if (dateUnit.getValue() <= DateUnit.SECOND.getValue()){
            calendar.set(Calendar.SECOND, 0);
        }
        if (dateUnit.getValue() <= DateUnit.MS.getValue()){
            calendar.set(Calendar.MILLISECOND, 0);
        }
        return toDate(calendar);
    }

    public static Date add(Date date, DateUnit dateUnit, int num) {
        Calendar calendar = toCalendar(date);
        if (dateUnit.getValue() <= DateUnit.YEAR.getValue()) {
            calendar.add(Calendar.YEAR, num);
        }
        if (dateUnit.getValue() <= DateUnit.MONTH.getValue()){
            calendar.add(Calendar.MONTH, num);
        }
        if (dateUnit.getValue() <= DateUnit.WEEK.getValue()){
            calendar.add(Calendar.DAY_OF_WEEK, num);
        }
        if (dateUnit.getValue() <= DateUnit.DAY.getValue()){
            calendar.add(Calendar.DAY_OF_MONTH, num);
        }
        if (dateUnit.getValue() <= DateUnit.AM_PM.getValue()){
            calendar.add(Calendar.AM_PM, num);
        }
        if (dateUnit.getValue() <= DateUnit.HOUR.getValue()){
            calendar.add(Calendar.HOUR, num);
        }
        if (dateUnit.getValue() <= DateUnit.MINUTE.getValue()){
            calendar.add(Calendar.MINUTE, num);
        }
        if (dateUnit.getValue() <= DateUnit.SECOND.getValue()){
            calendar.add(Calendar.SECOND, num);
        }
        if (dateUnit.getValue() <= DateUnit.MS.getValue()){
            calendar.add(Calendar.MILLISECOND, num);
        }
        return toDate(calendar);
    }

    public static Date endOfDateUnit(Date date,DateUnit unit) {
        return add(truncate(add(date,unit,1), unit),DateUnit.MS,-1);
    }

    public static Date beginOfYear(Date date) {
        return truncate(date, DateUnit.YEAR);
    }

    public static Date endOfYear(Date date) {
        return endOfDateUnit(date,DateUnit.YEAR);
    }

    public static Date beginOfMonth(Date date) {
        return truncate(date, DateUnit.MONTH);
    }

    public static Date endOfMonth(Date date) {
        return endOfDateUnit(date,DateUnit.MONTH);
    }

    public static Date beginOfWeek(Date date) {
        return truncate(date, DateUnit.WEEK);
    }

    public static Date endOfWeek(Date date) {
        return endOfDateUnit(date,DateUnit.WEEK);
    }

    public static Date beginOfDay(Date date) {
        return truncate(date, DateUnit.DAY);
    }

    public static Date endOfDay(Date date) {
        return endOfDateUnit(date,DateUnit.DAY);
    }

}
