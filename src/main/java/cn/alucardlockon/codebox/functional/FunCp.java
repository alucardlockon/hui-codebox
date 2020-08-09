package cn.alucardlockon.codebox.functional;

/**
 * normal function interface with two args and a boolean return, for compare function
 *
 * @since 1.0
 */
public interface FunCp<T> {
    int apply(T t1, T t2);
}
