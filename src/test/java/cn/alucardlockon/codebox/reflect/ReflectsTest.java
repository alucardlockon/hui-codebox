package cn.alucardlockon.codebox.reflect;

import cn.alucardlockon.codebox.common.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ReflectsTest {

    @Test
    public void testGet(){
        User user = new User(){{
            setId("1");
            setName("Joe");
            setAge(12);
        }};
        Map<String,Object> userMap = new HashMap<String, Object>(){{
            put("id", "2");
            put("name", "John");
            put("age", 123);
        }};
        System.out.println(Reflects.propGet(user,"id"));
        System.out.println(Reflects.propGet(userMap,"id"));
    }

    @Test
    public void testSet(){
        // TODO test no passed
        User user = new User(){{
            setId("1");
            setName("Joe");
            setAge(12);
        }};
        Map<String,Object> userMap = new HashMap<String, Object>(){{
            put("id", "2");
            put("name", "John");
            put("age", 123);
        }};
        Reflects.propSet(user, "name", "Doe");
        Reflects.propSet(userMap, "age", 122);
        System.out.println(Reflects.propGet(user,"name"));
        System.out.println(Reflects.propGet(userMap,"age"));
    }
}
