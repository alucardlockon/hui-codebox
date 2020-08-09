package cn.alucardlockon.codebox.linq;

import java.util.List;

public class Linqs {

    public static <T> Linq<T> from(List<T> list) {
        return new Linq<>(list);
    }
}
