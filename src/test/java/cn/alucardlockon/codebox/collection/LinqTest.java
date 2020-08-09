package cn.alucardlockon.codebox.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LinqTest {

    @Test
    public void testFrom() {
        List<String> list = new ArrayList<>();

        new Linq<>(list);
    }
}
