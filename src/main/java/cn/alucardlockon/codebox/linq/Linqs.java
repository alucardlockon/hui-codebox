package cn.alucardlockon.codebox.linq;

import cn.alucardlockon.codebox.functional.FunMap;
import cn.alucardlockon.codebox.reflect.Reflects;
import cn.alucardlockon.codebox.string.Strings;

import java.util.List;

/**
 * Linq Util
 *
 * @since 1.0
 */
public class Linqs {

    /**
     * create a new linq object from list
     */
    public static <T> Linq<T> from(List<T> list) {
        return new Linq<>(list);
    }

    /**
     * return function that get Object 's porp
     */
    public static <T> FunMap<T, Object> mapProp(final String propName) {
        return new FunMap<T, Object>() {
            @Override
            public Object apply(T t, int index, List<T> list) {
                return Reflects.propGetter(t, propName);
            }
        };
    }
}
