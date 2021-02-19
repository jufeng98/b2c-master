package com.javamaster.b2c.cloud.test.learn.java.test;

import com.javamaster.b2c.cloud.test.learn.java.utils.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author yudong
 * @date 2020/6/28csa
 */
public class BluemoonBaseTest {

    @Test
    public void test() throws IOException {

        String content = FileUtils.readAsString(new File("D:\\bluemoon-project\\bluemoon-base\\service-mall-statistical\\src\\main\\scripts\\stop.sh"));

        String basePath = "D:\\bluemoon-project\\bluemoon-base";

        Files.walkFileTree(Paths.get(basePath), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!"stop.sh".equals(file.getFileName().toString())) {
                    return FileVisitResult.CONTINUE;
                }
                if (!file.toAbsolutePath().toString().contains("service-")) {
                    return FileVisitResult.CONTINUE;
                }
                FileWriter fileWriter = new FileWriter(file.toFile());
                fileWriter.write(content);
                fileWriter.close();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });

    }

}
