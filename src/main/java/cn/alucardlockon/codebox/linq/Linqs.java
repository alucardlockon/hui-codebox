package cn.alucardlockon.codebox.linq;

import java.util.List;

/**
 * Linq Util
 * @since 1.0
 */
public class Linqs {

    public static <T> Linq<T> from(List<T> list) {
        return new Linq<>(list);
    }
}
