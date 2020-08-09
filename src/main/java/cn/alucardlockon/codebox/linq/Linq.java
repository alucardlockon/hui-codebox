package cn.alucardlockon.codebox.linq;

import cn.alucardlockon.codebox.collection.Collections;
import cn.alucardlockon.codebox.core.Langs;
import cn.alucardlockon.codebox.functional.FunFe;
import cn.alucardlockon.codebox.functional.FunMap;

import java.util.ArrayList;
import java.util.List;

/**
 * operate List just like Linq
 */
public class Linq<T> {

    private List<T> list;

    // constructors

    public Linq() {
    }

    public Linq(List<T> list) {
        this.list = list;
    }

    /**
     * alias from map
     */
    public Linq<T> select() {
        return this;
    }

    /**
     * set source list
     *
     * @param list the list
     */
    public Linq<T> from(List<T> list) {
        this.list = list;
        return this;
    }

    /**
     * filter the list, alias from filter
     */
    public Linq<T> where() {
        return filter();
    }

    /**
     * filter the list
     */
    public Linq<T> filter() {
        return this;
    }

    /**
     * get first element
     */
    public T first() {
        if (Langs.isEmpty(this.list)) return null;
        return this.list.get(0);
    }

    /**
     * get the last element
     */
    public T last() {
        if (Langs.isEmpty(this.list)) return null;
        return this.list.get(this.list.size() - 1);
    }

    /**
     * get the size of result
     */
    public int count() {
        return this.list.size();
    }

    /**
     * slice the result from beginIndex to endIndex
     */
    public Linq<T> limit() {
        return this;
    }

    /**
     * group by result with key
     */
    public Linq<T> groupBy() {
        return this;
    }

    /**
     * order by result
     */
    public Linq<T> orderBy() {
        return this;
    }

    /**
     * distinct a result
     */
    public Linq<T> distinct() {
        return this;
    }

    /**
     * append a list
     */
    public Linq<T> append(Linq<T> linq2) {
        this.list.addAll(Langs.emptyIf(linq2.toList(), new ArrayList<T>()));
        return this;
    }

    /**
     * map the list
     */
    public <R> Linq<R> map(FunMap<T,R> fun) {
        int index = 0;
        List<R> newlist = new ArrayList<>();
        for (T t : this.list) {
            newlist.add(fun.apply(t));
            index++;
        }
        return Linqs.from(newlist);
    }

    /**
     * reduce the list
     */
    public Linq<T> reduce() {
        return this;
    }

    /**
     * iterate the list
     */
    public Linq<T> forEach(FunFe<T> fun) {
        int index = 0;
        for (T t : this.list) {
            fun.apply(t, index, this.list);
            index++;
        }
        return this;
    }

    // methods that get result

    /**
     * result result list
     */
    public List<T> toList() {
        return this.list;
    }

    /**
     * get first result, alias from first
     */
    public T toOne() {
        return first();
    }

    /**
     * get first result or defaultValue when list is empty
     */
    public T toOneDefault(Class<T> clazz) {
        if (Langs.isEmpty(this.list)) return Langs.defaultVal(clazz);
        return first();
    }
}
