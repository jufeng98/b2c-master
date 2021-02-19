package com.javamaster.b2c.cloud.test.learn.java.dynamiccompiler;

import com.javamaster.b2c.cloud.test.learn.java.utils.DynamicUtils;
import com.javamaster.b2c.cloud.test.learn.java.utils.FileUtils;
import com.javamaster.b2c.cloud.test.learn.java.utils.OMUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

/**
 * Created on 2019/1/25.<br/>
 *
 * @author yudong
 */
public class DynamicCompilerTest {

    @Test
    public void test() throws Exception {
        File file = ResourceUtils.getFile("classpath:DynamicTestClass.txt");
        Object object = DynamicUtils.compileAndExecuteSourceCode(FileUtils.readAsString(file));
        System.out.println(OMUtils.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
    }

    @Test
    public void test1() throws Exception {
        File file = ResourceUtils.getFile("classpath:DynamicTestClass.class");
        Object object = DynamicUtils.executeByteCode(Files.readAllBytes(file.toPath()));
        System.out.println(OMUtils.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
    }

}
