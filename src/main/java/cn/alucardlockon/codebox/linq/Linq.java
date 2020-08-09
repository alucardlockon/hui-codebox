package cn.alucardlockon.codebox.linq;

import cn.alucardlockon.codebox.core.Langs;
import cn.alucardlockon.codebox.functional.*;
import cn.alucardlockon.codebox.map.Maps;

import java.util.*;

/**
 * operate List just like Linq
 *
 * @since 1.0
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
    public <R> Linq<R> select(FunMap<T, R> fun) {
        return map(fun);
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
    public Linq<T> where(FunFilter<T> fun) {
        return filter(fun);
    }

    /**
     * filter the list
     */
    public Linq<T> filter(FunFilter<T> fun) {
        int index = 0;
        List<T> newList = new ArrayList<>();
        for (T t : this.list) {
            if (fun.apply(t, index, this.list))
                newList.add(t);
            index++;
        }
        this.list = newList;
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
        if (Langs.isEmpty(this.list)) return 0;
        return this.list.size();
    }

    /**
     * slice the result from beginIndex to endIndex
     */
    public Linq<T> slice(int beginIndex, int endIndex) {
        if (Langs.isEmpty(this.list.isEmpty())) {
            return this;
        }

        if (beginIndex < 0)
            beginIndex = 0;
        if (endIndex < 0 || endIndex > this.list.size())
            endIndex = this.list.size();

        final List<T> result = new ArrayList<>();
        for (int i = beginIndex; i < endIndex; i++) {
            result.add(list.get(i));
        }
        this.list = result;
        return this;
    }

    /**
     * slice the result from beginIndex to endIndex , just like slice, but beginIndex is from 1
     */
    public Linq<T> limit(int beginIndex, int endIndex) {
        return slice(beginIndex, endIndex);
    }

    /**
     * group by result with key
     */
    public <K> Map<K, List<T>> groupBy(FunMap<T, K> fun) {
        int index = 0;
        Map<K, List<T>> map = new HashMap<>();

        for (T t : this.list) {
            K key = fun.apply(t, index, this.list);
            List<T> map2 = map.get(key);
            if (map2 == null) {
                map2 = new ArrayList<>();
                map.put(key, map2);
            }
            map2.add(t);
            index++;
        }

        return map;
    }

    /**
     * order by result
     */
    public Linq<T> orderBy(final FunCp<T> fun) {
        java.util.Collections.sort(this.list, new Comparator<T>() {
            @Override
            public int compare(T t1, T t2) {
                return fun.apply(t1, t2);
            }
        });
        return this;
    }

    /**
     * distinct a result
     */
    public <R> Linq<T> distinct(FunMap<T, R> fun) {
        int index = 0;
        Map<R, T> map = new LinkedHashMap<>();

        for (T t : this.list) {
            R item = fun.apply(t, index, this.list);
            map.put(item, t);
        }

        this.list = new ArrayList<>(map.values());
        return this;
    }

    public Linq<T> distinct() {
        return distinct(new FunMap<T, T>() {
            @Override
            public T apply(T t, int index, List<T> list) {
                return t;
            }
        });
    }

    /**
     * append a list
     */
    public Linq<T> append(Linq<T> linq2) {
        this.list.addAll(Langs.emptyIf(linq2.toList(), new ArrayList<T>()));
        return this;
    }

    /**
     * append a list, alias from append
     */
    public Linq<T> unionAll(Linq<T> linq2) {
        return append(linq2);
    }

    /**
     * append a list and distinct
     */
    public Linq<T> union(Linq<T> linq2) {
        return append(linq2).distinct();
    }

    /**
     * map the list
     */
    public <R> Linq<R> map(FunMap<T, R> fun) {
        int index = 0;
        List<R> newList = new ArrayList<>();
        for (T t : this.list) {
            newList.add(fun.apply(t, index, this.list));
            index++;
        }
        return Linqs.from(newList);
    }

    /**
     * reduce the list
     */
    public <R> R reduce(FunReduce<T, R> fun, R accumulator) {
        int index = 0;
        for (T t : this.list) {
            fun.apply(t, accumulator, index);
            index++;
        }
        return accumulator;
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
