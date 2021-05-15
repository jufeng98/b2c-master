package org.javamaster.b2c.test.utils;

import lombok.SneakyThrows;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author yudong
 * @date 2021/5/4
 */
public class ThreadLocalMapUtils {

    @SneakyThrows
    public static void printThreadLocals() {
        Thread thread = Thread.currentThread();
        Object threadLocals = getObj(thread, "threadLocals");
        Object table = getObj(threadLocals, "table");
        int length = Array.getLength(table);
        for (int i = 0; i < length; i++) {
            Object entry = Array.get(table, i);
            if (entry == null) {
                continue;
            }
            Object referent = getObj(entry, "referent");
            Object value = getObj(entry, "value");
            if (value == null) {
                continue;
            }
            System.out.println("key:" + referent);
            System.out.println("value:" + value);
            System.out.println("-------------------");
        }
    }


    @SneakyThrows
    public static Object getObj(Object target, String name) {
        Field field = ReflectionUtils.findField(target.getClass(), name);
        ReflectionUtils.makeAccessible(Objects.requireNonNull(field));
        return field.get(target);
    }
}
