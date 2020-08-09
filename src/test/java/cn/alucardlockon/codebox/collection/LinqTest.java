package cn.alucardlockon.codebox.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LinqTest {

    @Test
    public void testFrom() {
        List<String> list = Collections.newArrayList("1","3","2","4","2");

        System.out.println(Linqs.from(list).distinct().toList());
    }
}
