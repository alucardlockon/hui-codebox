package cn.alucardlockon.codebox.reflect;

import cn.alucardlockon.codebox.string.Strings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        return getProp(obj,"get"+ Strings.upperFirst(propName));
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
