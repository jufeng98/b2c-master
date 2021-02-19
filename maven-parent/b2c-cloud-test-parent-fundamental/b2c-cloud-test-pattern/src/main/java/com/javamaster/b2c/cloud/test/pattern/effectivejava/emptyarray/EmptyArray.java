package com.javamaster.b2c.cloud.test.pattern.effectivejava.emptyarray;


import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 2018/8/10.<br/>
 *
 * @author yudong
 */
public class EmptyArray {
    public static void main(String[] args) {
        System.out.println(getList());
    }

    public static List<Integer> getList() {
        if (RandomUtils.nextBoolean()) {
            return Collections.emptyList();
        } else {
            return new ArrayList<Integer>() {
                {
                    add(23);
                    add(67);
                }
            };
        }
    }
}
