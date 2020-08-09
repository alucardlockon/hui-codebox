package cn.alucardlockon.codebox.linq;

import cn.alucardlockon.codebox.collection.Collections;
import cn.alucardlockon.codebox.functional.FunFe;
import cn.alucardlockon.codebox.functional.FunMap;
import org.junit.Test;

import java.util.List;

public class LinqTest {

    @Test
    public void testFrom() {
        List<String> list = Collections.newArrayList("1", "3", "2", "4", "2");

        System.out.println(Linqs.from(list)
                .distinct()
                .forEach(new FunFe<String>() {
                    @Override
                    public void apply(String item, int index, List<String> list) {
                        System.out.println(index);
                    }
                })
                .map(new FunMap<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s) + 10;
                    }
                })
                .toList());
    }
}
