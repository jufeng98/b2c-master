package com.javamaster.b2c.cloud.test.pattern.effectivejava;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/8/1.</br>
 *
 * @author yudong
 */
public class Favorites {
    private Map<Class<?>, Object> map = new HashMap<>();

    public <T> void put(Class<T> clazz, T t) {
        map.put(clazz, t);
    }

    public <T> T get(Class<T> clazz) {
        return clazz.cast(map.get(clazz));
    }

    public static void main(String[] args) {
        Favorites favorites = new Favorites();
        favorites.put(String.class, "hello");
        favorites.put(Integer.class, 232);
        System.out.println(favorites.get(String.class));
        System.out.println(favorites.get(Integer.class));
    }
}
