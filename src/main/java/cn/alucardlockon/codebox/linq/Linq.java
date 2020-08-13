package cn.alucardlockon.codebox.linq;

import cn.alucardlockon.codebox.core.Langs;
import cn.alucardlockon.codebox.functional.*;
import cn.alucardlockon.codebox.reflect.Reflects;

import java.util.*;
import java.util.stream.Stream;

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

    public Linq<Object> select(String propName) {
        return map(propName);
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

    public int sum(final FunMap<T, Integer> fun) {
        final Linq<T> self = this;
        return reduce(new FunReduce<T, Integer>() {
            @Override
            public Integer apply(T t, Integer accumulator, int index) {
                return accumulator + fun.apply(t, index, self.list);
            }
        }, 0);
    }

    public int sum() {
        int sum = 0;
        for (T t : this.list) {
            sum += (Integer) t;
        }
        return sum;
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
        return slice(beginIndex + 1, endIndex + 1);
    }

    /**
     * page result
     */
    public Linq<T> page(int page, int pageSize) {
        if (page < 1) page = 1;
        return slice((page - 1) * pageSize, page * pageSize);
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
     * order by result
     *
     * @param order asc/desc
     */
    public Linq<T> orderBy(final String propName, final String order) {
        return orderBy(new FunCp<T>() {
            @Override
            public int apply(T t1, T t2) {
                Object o1 = Reflects.propGetter(t1, propName);
                Object o2 = Reflects.propGetter(t2, propName);
                if (o1 instanceof Number && o2 instanceof Number) {
                    Comparable<Number> co1 = (Comparable<Number>) o1;
                    Comparable<Number> co2 = (Comparable<Number>) o2;
                    if (order.equals("desc")) return co2.compareTo((Number) o1);
                    return co1.compareTo((Number) o2);
                }
                if (order.equals("desc")) return o2.toString().compareTo(o1.toString());
                return o1.toString().compareTo(o2.toString());
            }
        });
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
            index++;
        }

        this.list = new ArrayList<>(map.values());
        return this;
    }

    public Linq<T> distinct(String propName) {
        return distinct(Linqs.<T>mapProp(propName));
    }

    public Linq<T> distinct() {
        return distinct(Linqs.<T>mapSelf());
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

    public Linq<Object> map(final String propName) {
        return map(Linqs.<T>mapProp(propName));
    }

    /**
     * reduce the list
     */
    public <R> R reduce(FunReduce<T, R> fun, R accumulator) {
        if (Langs.isEmpty(accumulator)) return null;
        int index = 0;
        for (T t : this.list) {
            accumulator = fun.apply(t, accumulator, index);
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

    public String join(CharSequence str) {
        if (Langs.isEmpty(this.list)) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (T item : this.list) {
            if (isFirst) {
                isFirst = false;
                sb.append(item.toString());
            } else {
                sb.append(str).append(item.toString());
            }
        }
        return sb.toString();
    }

    /**
     * get intersection result
     */
    public <R> Linq<T> intersection(List<T> list2, FunMap<T, R> fun) {
        if (Langs.isEmpty(this.list) || Langs.isEmpty(list2)) return this;
        int index = 0;
        Map<R, T> map = new LinkedHashMap<>();

        for (T t : list2) {
            R item = fun.apply(t, index, list2);
            map.put(item, t);
            index++;
        }

        index = 0;
        List<T> result = new ArrayList<>();
        for (T t : this.list) {
            R item = fun.apply(t, index, this.list);
            if (map.get(item) != null) {
                result.add(t);
            }
            index++;
        }

        this.list = result;
        return this;
    }

    public Linq<T> intersection(List<T> list2, String propName) {
        return intersection(list2, Linqs.<T>mapProp(propName));
    }

    /**
     * get different result
     */
    public <R> Linq<T> different(List<T> list2, FunMap<T, R> fun) {
        if (Langs.isEmpty(this.list) || Langs.isEmpty(list2)) return this;

        int index = 0;
        Map<R, T> map = new LinkedHashMap<>();

        for (T t : this.list) {
            R item = fun.apply(t, index, this.list);
            map.put(item, t);
            index++;
        }

        index = 0;
        for (T t : list2) {
            R item = fun.apply(t, index, list2);
            if (map.get(item) != null)
                map.remove(item);
            index++;
        }

        this.list = new ArrayList<>(map.values());
        return this;
    }

    public Linq<T> different(List<T> list2, String propName) {
        return different(list2, Linqs.<T>mapProp(propName));
    }

    /**
     * get subtraction result
     */
    public <R> Linq<T> subtraction(List<T> list2, FunMap<T, R> fun) {
        List<T> result = Linqs.from(list2).different(this.list, fun).toList();
        result.addAll(Linqs.from(this.list).different(list2, fun).toList());
        this.list = result;
        return this;
    }

    public Linq<T> subtraction(List<T> list2, String propName) {
        return subtraction(list2, Linqs.<T>mapProp(propName));
    }

    /**
     * union a list and distinct
     */
    public <R> Linq<T> union(List<T> list2, FunMap<T, R> fun) {
        if (Langs.isEmpty(this.list) && Langs.isEmpty(list2)) return this;
        if (Langs.isEmpty(this.list)) return this;
        if (Langs.isEmpty(list2)) {
            this.list = list2;
            return this;
        }

        int index = 0;
        Map<R, T> map = new LinkedHashMap<>();

        for (T t : this.list) {
            R item = fun.apply(t, index, this.list);
            map.put(item, t);
            index++;
        }

        index = 0;
        for (T t : list2) {
            R item = fun.apply(t, index, list2);
            if (map.get(item) != null) {
                this.list.add(t);
            }
            index++;
        }

        return this;
    }

    public Linq<T> union(List<T> list2, String propName) {
        return union(list2, Linqs.<T>mapProp(propName));
    }

    public boolean hasAny(FunFilter<T> fun) {
        int index = 0;

        for (T t : this.list) {
            if (fun.apply(t, index, this.list)) {
                return true;
            }
            index++;
        }
        return false;
    }

    public boolean hasAll(FunFilter<T> fun) {
        int index = 0;
        if (Langs.isEmpty(this.list)) return false;
        for (T t : this.list) {
            if (!fun.apply(t, index, this.list)) {
                return false;
            }
            index++;
        }
        return true;
    }

    public boolean notIn(FunFilter<T> fun) {
        return !hasAny(fun);
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
