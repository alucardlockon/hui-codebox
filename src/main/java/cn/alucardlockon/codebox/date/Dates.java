package cn.alucardlockon.codebox.date;

import cn.alucardlockon.codebox.core.Langs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {

    public enum DateFormats {
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

        DateFormats(String formatStr) {
            this.formatStr = formatStr;
        }

        String getValue() {
            return this.formatStr;
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

    public static Date parse(String str, DateFormats format) {
        return parse(str, format.getValue());
    }

    public static Date parse(String str) {
        return parse(str, DateFormats.NORMAL_DATE_FORMAT);
    }

    public static String toStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String toStr(Date date, DateFormats format) {
        return toStr(date, format.getValue());
    }

    public static String toStr(Date date) {
        return toStr(date, DateFormats.NORMAL_DATE_FORMAT);
    }

    public static long getTime(String str, String format) {
        Date date = parse(str, format);
        return Langs.isEmpty(date) ? date.getTime() : 0L;
    }

    public static long getTime(String str, DateFormats format) {
        return getTime(str, format.getValue());
    }

    public static long getTime(String str) {
        return getTime(str, DateFormats.NORMAL_DATE_FORMAT);
    }
}
