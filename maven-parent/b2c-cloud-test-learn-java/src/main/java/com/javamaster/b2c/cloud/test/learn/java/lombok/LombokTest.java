package com.javamaster.b2c.cloud.test.learn.java.lombok;

import com.javamaster.b2c.cloud.test.learn.java.utils.OMUtils;
import com.google.common.collect.Lists;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        log.info("{}", readFileAsString(LombokTest.class.getClassLoader().getResource("car.json").getPath()));
    }

    @Synchronized
    public static Student get(@NonNull Integer id) {
        Student student = students.get(id);
        return student;
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
        return StreamUtils.copyToString(inputStream, Charset.forName("utf-8"));
    }
}
