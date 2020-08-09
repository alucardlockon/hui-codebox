package cn.alucardlockon.codebox.functional;

import java.util.List;

/**
 * normal function interface with args and return
 *
 * @since 1.0
 */
public interface FunR<T> {
    T apply(T t, int index, List<T> list);
}
