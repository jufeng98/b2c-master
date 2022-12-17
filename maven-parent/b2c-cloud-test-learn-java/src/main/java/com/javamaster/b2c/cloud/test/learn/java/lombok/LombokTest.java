package com.javamaster.b2c.cloud.test.learn.java.lombok;

import com.google.common.collect.Lists;
import com.javamaster.b2c.cloud.test.learn.java.utils.OMUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@Slf4j
public class LombokTest {

    static List<Student> students = Lists.newArrayList();

    static {
        students.add(new Student(1, "Jack", "1@qq.com", new Date()));
        students.add(new Student(2, "Martin", "2@qq.com", new Date()));
    }

    @Test
    public void test1() {
        Student student = new Student();
        log.info("{}", student);

        Student2 student2 = new Student2();
        log.info("{}", student2);
    }

    @Test
    public void test2() {
        Student3 student3 = Student3.builder().name("liang yudong").build();
        log.info("{}", student3);

        val stu = new Student();
        stu.setName("Rose");
        val list = new ArrayList<Student>();
        list.add(stu);
        log.info("{}", list);
        log.info(OMUtils.writeValueAsString(list));

        log.info("{}", get(1));
        log.info("{}", parseDate("20180225"));
        log.info("{}", readFileAsString(Objects.requireNonNull(LombokTest.class.getClassLoader()
                .getResource("car.json")).getPath()));
    }

    @Synchronized
    public static Student get(@NonNull Integer id) {
        return students.get(id);
    }

    @Synchronized
    public String getName(@NonNull Integer id) {
        return students.get(id).getName();
    }

    @SneakyThrows
    public static Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.parse(dateStr);
    }

    @SneakyThrows
    public static String readFileAsString(String path) {
        File file = new File(path);
        @Cleanup InputStream inputStream = new FileInputStream(file);
        return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    }
}
