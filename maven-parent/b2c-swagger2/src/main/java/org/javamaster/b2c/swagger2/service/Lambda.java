package org.javamaster.b2c.swagger2.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yudong
 * @date 2022/11/15
 */
@Component
public class Lambda {
    String desc = " java";
    List<String> list = Lists.newArrayList("hello", "world");

    public void printList() {
        list.stream()
                .map(s -> {
                    return s.toUpperCase();
                })
                .forEach(s -> {
                    System.out.println(s + desc);
                });
    }

}
