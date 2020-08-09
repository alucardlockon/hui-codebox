package cn.alucardlockon.codebox.functional;

/**
 * normal function interface with args and return, for map function
 */
public interface FunMap<T,R> {
     R apply(T t);
}
