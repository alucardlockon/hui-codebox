package cn.alucardlockon.codebox.collection;

import cn.alucardlockon.codebox.core.Langs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 集合相关
 *
 * @since 1.0
 */
public class Collections {

    /**
     * 判断集合是否为null或空
     *
     * @return 对象是否为空
     */
    public static boolean isEmpty(Collection obj) {
        return obj == null || obj.isEmpty();
    }


    /**
     * 判断集合是否不为null或空
     *
     * @return 对象是否不为空
     */
    public static boolean isNotEmpty(Collection obj) {
        return !isEmpty(obj);
    }

    /**
     * 返回一个新的arrayList
     *
     * @return 新的arrayList
     */
    public static <T> ArrayList<T> newArrayList(T... values) {
        if (Langs.isEmpty(values)) return new ArrayList<>();
        ArrayList<T> list = new ArrayList<>();
        java.util.Collections.addAll(list, values);
        return list;
    }

    /**
     * get List index with null check
     */
    public static <T> T getIndex(List<T> list, int index) {
        return list.size() > index && index > -1 ? list.get(index) : null;
    }
}
