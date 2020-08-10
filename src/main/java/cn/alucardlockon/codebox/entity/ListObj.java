package cn.alucardlockon.codebox.entity;

import java.util.List;

/**
 * simple object contains key and a list
 */
public class ListObj<T> {

    private String key;

    private List<T> list;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
