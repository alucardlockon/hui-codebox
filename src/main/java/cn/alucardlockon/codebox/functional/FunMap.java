package cn.alucardlockon.codebox.functional;

import java.util.List;

/**
 * normal function interface with args and return, for map function
 * @since 1.0
 */
public interface FunMap<T,R> {
     R apply(T t, int index, List<T> list);
}
