package com.javamaster.b2c.cloud.test.pattern.effectivejava.overload;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 2018/8/8.<br/>
 *
 * @author yudong
 */
public class SetList {
    public static void main(String[] args) {
        Set<Integer> set = new LinkedHashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        System.out.println(set + " " + list);
        // 删除0,1,2
        for (int i = 0; i < 3; i++) {
            set.remove(i);
            // 此处选择的重载方法错误
            list.remove(i);
        }
        System.out.println(set + " " + list);

        set.clear();
        list.clear();
        for (int i = -3; i < 3; i++) {
            set.add(i);
            list.add(i);
        }
        System.out.println(set + " " + list);
        // 删除0,1,2
        for (int i = 0; i < 3; i++) {
            set.remove(i);
            list.remove(Integer.valueOf(i));
        }
        System.out.println(set + " " + list);
    }
}
