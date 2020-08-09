package cn.alucardlockon.codebox.collection;

import cn.alucardlockon.codebox.core.Langs;

import java.util.List;

/**
 * 像是用Linq一样操作List
 */
public class Linq<T> {

    private List<T> list;

    public Linq(){

    }

    public Linq(List<T> list){
        this.list = list;
    }

    public Linq<T> select() {
        return this;
    }

    public Linq<T> from() {
        return this;
    }

    public Linq<T> where() {
        return this;
    }

    public Linq<T> first() {
        return this;
    }

    public Linq<T> last() {
        return this;
    }

    public Linq<T> limit() {
        return this;
    }

    public Linq<T> groupBy() {
        return this;
    }

    public Linq<T> orderBy() {
        return this;
    }

    public Linq<T> distinct() {
        return this;
    }

    public Linq<T> join() {
        return this;
    }

    public Linq<T> map() {
        return this;
    }

    public Linq<T> forEach() {
        return this;
    }

    public List<T> toList() {
        return this.list;
    }

    public T toOne() {
        if(Langs.isEmpty(this.list)) return null;
        return this.list.get(0);
    }

    public T toOneDefault(Class<T> clazz) {
        if(Langs.isEmpty(this.list)) return Langs.defaultVal(clazz);
        return this.list.get(0);
    }
}
