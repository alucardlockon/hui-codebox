package cn.alucardlockon.codebox.reflect;

import cn.alucardlockon.codebox.map.Maps;
import cn.alucardlockon.codebox.string.Strings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * reflect util
 */
public class Reflects {

    /**
     * get a class' prop's value, invoke getter method
     */
    public static <T> Object getProp(T obj, String methodName, Class<?>... params) {
        Object result = null;
        try {
            result = getMethod(obj, methodName, params).invoke(obj, (Object[]) params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> Object propGetter(T obj, String propName) {
        return getProp(obj, "get" + Strings.upperFirst(propName));
    }

    public static <T> Object propMapGet(T obj, String propName) {
        return Maps.get((Map) obj, propName);
    }

    /**
     * try to get porp from object
     */
    public static <T> Object propGet(T obj, String propName) {
        if (obj instanceof Map) {
            return propMapGet(obj, propName);
        } else {
            return propGetter(obj, propName);
        }
    }

    public static <T> Object propSet(T obj, String propName, Object val) {
        if (obj instanceof Map) {
            return propMapSet(obj, propName, val);
        } else {
            return propSetter(obj, propName, (Class<?>) val);
        }
    }

    public static <T> Object setProp(T obj, String methodName, Class<?>... params) {
        Object result = null;
        try {
            result = getMethod(obj, methodName, params).invoke(obj , (Object[]) params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> Object propSetter(T obj, String propName, Class<?>... params) {
        return setProp(obj, "set" + Strings.upperFirst(propName), params);
    }

    public static <T> Object propMapSet(T obj, String propName, Object val) {
        return ((Map)obj).put(propName, val);
    }

    /**
     * get a class's method
     */
    public static <T> Method getMethod(Class<T> obj, String methodName, Class<?>... params) {
        Method method = null;
        try {
            method = obj.getMethod(methodName, params);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static <T> Method getMethod(T obj, String methodName, Class<?>... params) {
        return getMethod(obj.getClass(), methodName, params);
    }
}
