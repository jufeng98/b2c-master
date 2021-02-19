package com.javamaster.b2c.cloud.test.pattern.effectivejava.staticfactory;

import com.google.common.collect.Lists;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2018/8/3.</br>
 *
 * @author yudong
 */
public class StaticFactory {

    public static void main(String[] args) {
        Map<String, String> map = StaticFactory.newHashMap();
        System.out.println(map);
        List<String> list = Lists.newArrayList();
        System.out.println(list);
        Boolean bool = Boolean.valueOf(true);
        System.out.println(bool);
        Set set = EnumSet.of(RoundingMode.DOWN, RoundingMode.CEILING, RoundingMode.FLOOR);
        System.out.println(set);
        NumberFormat format = NumberFormat.getInstance();
        System.out.println(format);

    }

    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }
}
