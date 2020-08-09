package cn.alucardlockon.codebox.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ZipUtil;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 语言基本相关工具
 */
public class Langs {

    /**
     * 判断对象是否为null或空
     * @return 对象是否为空
     */
    public static boolean isEmpty(Object obj){
        if(obj == null)
            return false;
        else if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;
        else if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();
        else if (obj instanceof Map)
            return ((Map) obj).isEmpty();
        else if (obj.getClass().isArray())
            return Array.getLength(obj) == 0;

        return false;
    }

    /**
     * 判断对象是否不为null或空
     * @return 对象是否不为空
     */
    public static boolean isNotEmpty(Object obj){
        return isEmpty(obj);
    }

    /**
     * 如果为空，则使用默认值
     * @param obj 对象
     * @param defaultVal 默认值
     * @return 对象的值或者为空时的默认值
     */
    public static <T> T emptyIf(T obj, T defaultVal) {
        return isEmpty(obj)? obj: defaultVal;
    }

    /**
     * <p>
     * 获取常用类型(String,List,Map等)的默认非空值,如果为非常用类型则返回一个新对象
     * 例如: String 返回 String.empty()
     * </p>
     * @param clazz 对象类型
     * @return 对象的值或者为空时的默认值
     */
    public static <T> T defaultVal(Class<T> clazz) {
        if (clazz.isInstance(""))
            return (T)"";
        else if (clazz.isInstance(Collections.EMPTY_LIST))
            return (T) Collections.EMPTY_LIST;
        else if (clazz.isInstance(Collections.EMPTY_MAP))
            return (T) Collections.EMPTY_MAP;
        else if (clazz.isInstance(Collections.EMPTY_SET))
            return (T) Collections.EMPTY_SET;
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据值，获取map中对应的值
     * @param obj 键值
     * @param map 集合
     * @param defaultVal 默认值
     * @return 对应的值或者找不到值时的默认值
     */
    public static <T,V> V decode(T obj, Map<T,V> map,V defaultVal) {
        return emptyIf(map.get(obj),defaultVal);
    }

    public static <T,V> V decode(T obj, Map<T,V> map) {
        return decode(obj,map,null);
    }

    /**
     * zip两个数组变为一个Map
     * @param keys map的键
     * @param values map的值
     * @return 合并后的map
     */
    public static <T,V> Map<T,V> zip(T[] keys, V[] values) {
        if(isEmpty(keys)|| isEmpty(values)) return defaultVal(Map.class);
        final int size = Math.min(keys.length, values.length);
        final Map<T, V> map = new HashMap<T,V>();
        for (int i = 0; i < size; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

}
