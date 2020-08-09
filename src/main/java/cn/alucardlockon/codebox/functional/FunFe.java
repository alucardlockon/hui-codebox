package cn.alucardlockon.codebox.functional;

import java.util.List;

/**
 * normal function interface , for foreach
 *
 * @since 1.0
 */
public interface FunFe<T> {
    void apply(T item, int index, List<T> list);
}
