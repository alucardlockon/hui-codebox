package cn.alucardlockon.codebox.functional;

import java.util.List;

/**
 * normal function interface with args and return, for filter function
 * @since 1.0
 */
public interface FunFilter<T> {
     boolean apply(T t, int index, List<T> list);
}
