package cn.alucardlockon.codebox.core;

/**
 * type convert util
 */
public class Types {

    /**
     * convert to Type R with defaultValue
     */
    public static <T, R> R convertTo(T t, Class<R> r, R defaultVal) {
        try {
            return (R) t;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return defaultVal;
    }

    public static <T, R> R convertTo(T t, Class<R> r) {
        return convertTo(t, r, Langs.defaultVal(r));
    }

    /**
     * convert to Type R with defaultValue is null
     */
    public static <T, R> R convertToOrNull(T t, Class<R> r) {
        return convertTo(t, r, null);
    }

}
