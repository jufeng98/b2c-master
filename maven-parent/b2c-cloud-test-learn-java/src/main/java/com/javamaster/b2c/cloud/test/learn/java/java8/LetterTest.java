package com.javamaster.b2c.cloud.test.learn.java.java8;


import com.javamaster.b2c.cloud.test.learn.java.java8.model.Letter;
import org.junit.Test;

import java.util.function.Function;

/**
 * Created on 2018/12/24.<br/>
 *
 * @author yudong
 */
public class LetterTest {

    @Test
    public void test() {
        Function<String, String> headerFunction = Letter::addHeader;
        Function<String, String> footerFunction = Letter::addFooter;
        Function<String, String> checkFunction = Letter::checkSpelling;
        Function<String, String> function = headerFunction.compose(footerFunction).andThen(checkFunction);
        System.out.println(function.apply("labda lambda content"));
    }
}
