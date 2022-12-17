package com.javamaster.b2c.cloud.test.boot;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.*;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author yudong
 * @date 2022/11/26
 */
public class ResourceTest {

    @Test
    public void test() throws Exception {
        UrlResource urlResource = new UrlResource("https://www.baidu.com");
        System.out.println(urlResource.getDescription());
        System.out.println(StreamUtils.copyToString(urlResource.getInputStream(), StandardCharsets.UTF_8));
    }

    @Test
    public void test11() throws Exception {
        UrlResource urlResource = new UrlResource("file:///D:\\my_opensource_project\\b2c-master\\maven-parent\\b2c-cloud-test-parent\\b2c-cloud-test-boot\\src\\main\\resources\\bootstrap.properties");
        System.out.println(urlResource.getDescription());
        System.out.println(StreamUtils.copyToString(urlResource.getInputStream(), StandardCharsets.UTF_8));
    }

    @Test
    public void test1() throws Exception {
        ClassPathResource urlResource = new ClassPathResource("bootstrap.properties");
        System.out.println(urlResource.getDescription());
        System.out.println(StreamUtils.copyToString(urlResource.getInputStream(), StandardCharsets.UTF_8));
    }

    @Test
    public void test2() throws Exception {
        FileSystemResource urlResource = new FileSystemResource("D:\\my_opensource_project\\b2c-master\\maven-parent\\b2c-cloud-test-parent\\b2c-cloud-test-boot\\src\\main\\resources\\bootstrap.properties");
        System.out.println(urlResource.getDescription());
        System.out.println(StreamUtils.copyToString(urlResource.getInputStream(), StandardCharsets.UTF_8));
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/b2c-servlet.xml");
        //位置路径没有包含特定的前缀,spring 会根据当前应用上下文来决定返回哪一种类型 Resource
        //例如上下文对象是 ClassPathXmlApplicationContext,则会返回一个 ClassPathResource 对象
        //上下文对象是 FileSystemXmlApplicationContext,则会返回一个 FileSystemResource 对象
        //上下文对象是 WebApplicationContext,则会返回一个 ServletContextResource 对象
        Resource resource = context.getResource("spring/b2c-servlet.xml");
        System.out.println(resource);
        // 指定前缀
        // 得到ClassPathResource
        context.getResource("classpath:spring/b2c-servlet.xml");
        // 得到UrlResource
        context.getResource("file:///spring/b2c-servlet.xml");
    }
}
