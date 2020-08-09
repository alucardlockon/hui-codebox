package cn.alucardlockon.codebox.linq;

import cn.alucardlockon.codebox.collection.Collections;
import cn.alucardlockon.codebox.functional.*;
import org.junit.Test;

import java.util.List;

public class LinqTest {

    @Test
    public void testFrom() {
        List<String> list = Collections.newArrayList("1", "3", "2", "4", "2");

        System.out.println(Linqs.from(list)
                // .distinct()
                .filter(new FunFilter<String>() {
                    @Override
                    public boolean apply(String s, int index, List<String> list) {
                        return s.equals("2");
                    }
                })
                .forEach(new FunFe<String>() {
                    @Override
                    public void apply(String item, int index, List<String> list) {
                        System.out.println(index);
                    }
                })
                .select(new FunMap<String, Integer>() {
                    @Override
                    public Integer apply(String s, int index, List<String> list) {
                        return Integer.parseInt(s) + 10;
                    }
                })
                .slice(0, -1)
                .join(" , ")
        );
    }

    @Test
    public void testString() {
        List<User> list = Collections.newArrayList(
                new User() {{
                    setId("1");
                    setName("who");
                    setAge(18);
                }},
                new User() {{
                    setId("2");
                    setName("are");
                    setAge(22);
                }},
                new User() {{
                    setId("3");
                    setName("u");
                    setAge(12);
                }}
        );

        System.out.println(Linqs.from(list)
                .orderBy("name", "asc")
                .map("name")
                .toList()
        );
    }

    @Test
    public void testIntersection() {
        List<User> list = Collections.newArrayList(
                new User() {{
                    setId("1");
                    setName("who");
                    setAge(18);
                }},
                new User() {{
                    setId("2");
                    setName("are");
                    setAge(22);
                }},
                new User() {{
                    setId("3");
                    setName("u");
                    setAge(12);
                }}
        );

        List<User> list2 = Collections.newArrayList(
                new User() {{
                    setId("1");
                    setName("who");
                    setAge(18);
                }},
                new User() {{
                    setId("4");
                    setName("?");
                    setAge(32);
                }}
        );

        System.out.println(Linqs.from(list)
                .subtraction(list2, Linqs.<User>mapProp("id"))
                .toList()
        );
    }
}
