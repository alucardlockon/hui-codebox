package cn.alucardlockon.codebox.functional;

import java.util.List;

/**
 * normal function interface with args and return, for reduce function
 * @since 1.0
 */
public interface FunReduce<T,R> {
     R apply(T t, R accumulator, int index);
}
