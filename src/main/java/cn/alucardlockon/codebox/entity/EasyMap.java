package cn.alucardlockon.codebox.entity;

import cn.alucardlockon.codebox.core.Langs;
import cn.alucardlockon.codebox.core.Types;
import cn.alucardlockon.codebox.map.Maps;

import java.util.*;

public class EasyMap extends HashMap<String, Object> {

    public EasyMap pute(String key, Object val) {
        this.put(key, val);
        return this;
    }

    public <T> T get(String key, Class<T> clazz) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), clazz), null);
    }

    public <T> T get(String key, Class<T> clazz, T defaultValue) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), clazz), defaultValue);
    }

    public int getInt(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Integer.class), 0);
    }

    public Integer getInteger(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Integer.class), 0);
    }

    public long getLong(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Long.class), 0L);
    }

    public Long getLongBoxing(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Long.class), 0L);
    }

    public double getDouble(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Double.class), 0d);
    }

    public Double getDoubleBoxing(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Double.class), 0d);
    }

    public float getFloat(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Float.class), 0f);
    }

    public Float getFloatBoxing(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Float.class), 0f);
    }

    public boolean getBool(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Boolean.class), false);
    }

    public Boolean getBoolean(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Boolean.class), false);
    }

    public Date getDate(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), Date.class), null);
    }

    public String getString(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), String.class), "");
    }

    public EasyMap getMap(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), EasyMap.class), null);
    }

    public EasyMap getIn(String key) {
        return getMap(key);
    }

    public <T> List<T> getAsList(String key, Class<T> clazz) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), List.class), null);
    }

    public List<EasyMap> getAsList(String key) {
        return Langs.emptyIf(Types.convertTo(Maps.get(this, key), List.class), null);
    }

    public HashMap<String, Object> toHashMap() {
        return this;
    }
}
