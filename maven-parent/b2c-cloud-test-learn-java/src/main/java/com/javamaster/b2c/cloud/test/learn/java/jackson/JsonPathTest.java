package com.javamaster.b2c.cloud.test.learn.java.jackson;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * @author yudong
 * @date 2018/3/22
 */
public class JsonPathTest {

    @Test
    @SneakyThrows
    public void test() {
        File file = ResourceUtils.getFile("classpath:book.json");
        DocumentContext documentContext = JsonPath.parse(file);
        Object object = documentContext.read("$");
        System.out.println(object);

        object = documentContext.read("$.store.book[*].author");
        System.out.println(object);

        object = documentContext.read("$..author");
        System.out.println(object);

        object = documentContext.read("$.store.*");
        System.out.println(object);

        object = documentContext.read("$.store..price");
        System.out.println(object);

        object = documentContext.read("$..book[2]");
        System.out.println(object);

        object = documentContext.read("$..book[-1:]");
        System.out.println(object);

        object = documentContext.read("$..book[0,1]");
        System.out.println(object);

        object = documentContext.read("$..book[?(@.isbn)]");
        System.out.println(object);

        object = documentContext.read("$..book[?(@.price<10)]");
        System.out.println(object);
    }
}

