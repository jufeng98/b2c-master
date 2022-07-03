package com.javamaster.b2c.cloud.test.learn.java.test;

import org.junit.Test;

import java.io.*;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yudong
 * @date 2022/5/21
 */
public class PerformanceOptimizeTest {
    @Test
    public void test1() {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str1 == str3);
    }

    @Test
    public void test2() {
        String text = "abbc";
        String regex = "ab{1,3}+bc";
        System.out.println(Pattern.matches(regex, text));
    }

    @Test
    public void test3() {
        String text = "<input high=\"20\" weight=\"70\">test</input>";
        String reg = "(<input.*?>)(.*?)(</input>)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println(m.group(0));// 整个匹配到的内容
            System.out.println(m.group(1));//(<input.*?>)
            System.out.println(m.group(2));//(.*?)
            System.out.println(m.group(3));//(</input>)
        }

        reg = "(?:<input.*?>)(.*?)(?:</input>)";
        p = Pattern.compile(reg);
        m = p.matcher(text);
        while (m.find()) {
            System.out.println(m.group(0));// 整个匹配到的内容
            System.out.println(m.group(1));//(.*?)
        }
    }

    @Test
    public void test4() throws IOException {
        Selector selector = Selector.open();
    }
}
