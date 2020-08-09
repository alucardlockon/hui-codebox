package cn.alucardlockon.codebox.map;

import cn.alucardlockon.codebox.core.Langs;
import cn.alucardlockon.codebox.core.Types;

import java.util.Date;
import java.util.Map;

public class Maps {

    /**
     * check map is null or empty
     */
    public static boolean isEmpty(Map obj) {
        return obj == null || obj.isEmpty();
    }


    /**
     * check map is not null and not empty
     */
    public static boolean isNotEmpty(Map obj) {
        return !isEmpty(obj);
    }

    // getValues

    public static Object get(Map map, Object key) {
        if (Langs.isEmpty(map)) return null;
        return map.get(key);
    }

    public static <T> T get(Map map, Object key, Class<T> clazz) {
        return Langs.emptyIf(Types.convertTo(get(map, key), clazz), null);
    }

    public static <T> T get(Map map, Object key, Class<T> clazz, T defaultValue) {
        return Langs.emptyIf(Types.convertTo(get(map, key), clazz), defaultValue);
    }

    public static int getInt(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Integer.class), 0);
    }

    public static Integer getInteger(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Integer.class), 0);
    }

    public static long getLong(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Long.class), 0L);
    }

    public static Long getLongBoxing(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Long.class), 0L);
    }

    public static double getDouble(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Double.class), 0d);
    }

    public static Double getDoubleBoxing(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Double.class), 0d);
    }

    public static float getFloat(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Float.class), 0f);
    }

    public static Float getFloatBoxing(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Float.class), 0f);
    }

    public static boolean getBool(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Boolean.class), false);
    }

    public static Boolean getBoolean(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Boolean.class), false);
    }

    public static Date getDate(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Date.class), null);
    }

    public static String getString(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), String.class), "");
    }

    public static Map getMap(Map map, Object key) {
        return Langs.emptyIf(Types.convertTo(get(map, key), Map.class), null);
    }

}
