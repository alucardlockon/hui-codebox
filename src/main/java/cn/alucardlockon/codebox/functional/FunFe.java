package cn.alucardlockon.codebox.functional;

import java.util.List;

/**
 * normal function interface , for foreach
 */
public interface FunFe<T> {
      void apply(T item, int index, List<T> list);
}
