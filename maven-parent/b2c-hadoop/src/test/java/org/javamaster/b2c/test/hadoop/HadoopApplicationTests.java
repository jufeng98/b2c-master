package org.javamaster.b2c.test.hadoop;

import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.hadoop.HadoopApplication;
import org.javamaster.b2c.hadoop.utils.HdfsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.util.Arrays;

/**
 * @author yudong
 * @date 2021/2/14
 */
@Slf4j
@SpringBootTest(classes = HadoopApplication.class)
class HadoopApplicationTests {
    public static final File PROJECT_PATH = new File("").getAbsoluteFile();

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoads() {
        log.info("id:{}", context.getId());
    }

    @Test
    public void mkdir() {
        HdfsUtils.mkdir("yudong");
    }

    @Test
    public void upload() {
        File file = new File(PROJECT_PATH, "src/test/resources/hadoop/test2.txt");
        HdfsUtils.copyFileToHDFS(false, true, file.getAbsolutePath(), "yudong");
    }

    @Test
    public void listFile() {
        String[] files = HdfsUtils.listFile("yudong", null);
        log.info("files:{}", Arrays.toString(files));
    }
}
