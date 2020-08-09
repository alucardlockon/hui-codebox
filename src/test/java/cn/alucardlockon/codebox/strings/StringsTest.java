package cn.alucardlockon.codebox.strings;

import cn.alucardlockon.codebox.string.Strings;
import org.junit.Test;

public class StringsTest {
    @Test
    public void testString() {
        System.out.println(Strings.slice("user", 2, -1));
        System.out.println(Strings.slice("user", -1, 2));
        System.out.println(Strings.upperFirst("user"));

        System.out.println(Strings.camelCase("userName_ssa"));
        System.out.println(Strings.snakeCase("userName_ssa"));
    }
}
